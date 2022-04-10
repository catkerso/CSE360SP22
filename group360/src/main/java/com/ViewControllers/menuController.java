package com.ViewControllers;

import com.Database.Auth;
import com.Database.Menu;
import com.Objects.MenuItem;
import java.io.*;
import java.util.*;
import com.Objects.Order;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class menuController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox outerBoxVBox;
    @FXML
    private HBox outerBoxHBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ListView<HBox> menuListView;
   
    ArrayList<MenuItem> menu = new ArrayList<>(Menu.getMenu());

    // Buttons
    @FXML private Button backButton;
    @FXML private Button cartButton;
    @FXML private Button byNameButton;
    @FXML private Button byPriceButton;
    @FXML private Button byVeganButton;

    //Cart Setup - not sure about guest user handling for orders

    
    public static Order cart = new Order(Auth.isSignedIn() ? Auth.getCurrentUser() : null);
    

    public void initialize() {
        try {
            loadMenuItems();
        } catch (IOException e) {
            App.errorDialog("Exception", e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onBack() throws IOException {
        App.setRoot("custGuestHome");
    }

    private void loadMenuItems() throws IOException {
        menuListView.getItems().clear();
        for (MenuItem item : menu) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menuListItem.fxml")); 
            fxmlLoader.setController(new MenuListItem(item));
            menuListView.getItems().add(fxmlLoader.load());
        }
        menuListView.refresh();

    }

    @FXML
    private void onCart() throws IOException {
        if(cart.getItems().size() == 0){
            App.errorDialog("Empty Cart", "Please add an item to your cart before proceeding.");
        }
        else{
        App.setRoot("cart");
        }
    }

    @FXML
    private void onByName() throws IOException {
        int i, j;
        for (i = 1; i < 5; i++) {
            MenuItem tmp = menu.get(i);
            j = i;
            while ((j > 0) && (menu.get(j - 1).getName().compareTo(tmp.getName()) > 0)) {
                menu.set(j, menu.get(j - 1));
                j--;
            }
            menu.set(j, tmp);
        }
        loadMenuItems();
    }

    @FXML
    private void onByPrice() throws IOException {
        int i, j;
        for (i = 1; i < 5; i++) {
            MenuItem tmp = menu.get(i);
            j = i;
            while ((j > 0) && (menu.get(j - 1).getPrice() > tmp.getPrice())) {
                menu.set(j, menu.get(j - 1));
                j--;
            }
            menu.set(j, tmp);
        }
        loadMenuItems();
    }

    @FXML
    private void onByVegan() throws IOException {
        int i, j;
        for (i = 1; i < 5; i++) {
            MenuItem tmp = menu.get(i);
            j = i;
            while ((j > 0) && (menu.get(j - 1).isVegan() != tmp.isVegan())) {
                menu.set(j, menu.get(j - 1));
                j--;
            }
            menu.set(j, tmp);
        }
        loadMenuItems();
    }

    public static Order grabCart() {
        return cart;
    }
}
