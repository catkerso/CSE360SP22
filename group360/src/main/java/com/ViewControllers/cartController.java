package com.ViewControllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.ViewControllers.menuController;
import com.Objects.MenuItem;
import com.Objects.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import com.Database.Auth;
import com.Database.Menu;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.fxml.FXML;

public class cartController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane basePane;

    @FXML
    private Button menu;

    @FXML
    Text subTotFloatText;
    @FXML
    Text emptyCart;

    @FXML
    public ListView<HBox> listView;

    
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();   //<MenuItem, Quantity>

    public float subtotal = 0f;
 
    @FXML
    void initialize() {
    // figure out what is actually in the cart
        
        if (menuController.grabCart().getItems() != null){
            emptyCart.setVisible(false);
        }

        populateMap();
        populateList();


        //price string with 2 decimal places
        String priceString = String.format("%.2f", subtotal);
        subTotFloatText.setText("$" + priceString);
    }

    @FXML
    private void onMenu() throws IOException {
        App.setRoot("menu");
    }
    @FXML
    private void onCheckout(ActionEvent event) throws IOException {
        App.setRoot("Payment");
    }

    private void populateMap() {
        for (MenuItem item : menuController.grabCart().getItems()) {
            if (map.containsKey(item.getId())) {
                map.put(item.getId(), map.get(item.getId()) + 1);
            } else {
                map.put(item.getId(), 1);
            }
        }
    }

    private void populateList() {
        for (Map.Entry<Integer, Integer> item : map.entrySet()) {
            MenuItem menuItem = Menu.getMenuItem(item.getKey());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cartListItem.fxml"));
            loader.setController(new CartListItem(menuItem, item.getValue(), this));
            this.subtotal += menuItem.getPrice() * item.getValue();
            try {
                listView.getItems().add(loader.load());
            } catch (IOException e) {
                App.errorDialog("Error", e.getMessage());
            }
        }
    }

    public void refreshList() {
        listView.getItems().clear();
        map.clear();
        populateMap();
        populateList();
    }

    public void setPrice() {
        this.subtotal = menuController.grabCart().getPrice();
        String priceString = String.format("%.2f", subtotal);
        subTotFloatText.setText("$" + priceString);
    }
   
}
