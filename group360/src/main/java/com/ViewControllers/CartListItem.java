package com.ViewControllers;
import java.net.URL;
import java.util.ResourceBundle;

import com.Objects.MenuItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CartListItem extends HBox {

    private MenuItem item;
    private int quantity;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text itemNameLabel;

    @FXML
    private HBox itemOneHbox;

    @FXML
    private Text itemOnePText;

    @FXML
    private Text itemOneQText;

    cartController controller;

    @FXML
    void QtyDown(ActionEvent event) {
        if((!menuController.cart.removeItem(item)) || quantity == 1) {
            if(quantity == 1) {
                controller.setPrice();
            }
            controller.refreshList();
            return;
        }
        this.quantity--;
        this.itemOneQText.setText(Integer.toString(this.quantity));
        this.itemOnePText.setText(Float.toString(this.item.getPrice() * this.quantity));
        controller.setPrice();
    }

    @FXML
    void QtyUp(ActionEvent event) {
        this.quantity++;
        this.itemOneQText.setText(Integer.toString(this.quantity));
        this.itemOnePText.setText(Float.toString(this.item.getPrice() * this.quantity));
        menuController.cart.addItem(item);
        controller.setPrice();
    }

    CartListItem(MenuItem item, int qty, cartController controller) {
        this.item = item;
        this.quantity = qty;
        this.controller = controller;
    }

    @FXML
    void initialize() {
        itemNameLabel.setText(item.getName());
        itemOnePText.setText(String.valueOf(item.getPrice()));
        itemOneQText.setText(String.valueOf(quantity));
    }
}

