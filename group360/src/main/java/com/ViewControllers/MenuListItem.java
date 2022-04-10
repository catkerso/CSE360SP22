package com.ViewControllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.Objects.MenuItem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MenuListItem extends HBox {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text ItemIngLabel;

    @FXML
    private Button cartButton;

    @FXML
    private Text itemDescLabel;

    @FXML
    private Text itemNameLabel;

    @FXML
    private Text itemPriceLabel;

    @FXML
    private Text itemVeganLabel;


    @FXML
    private ImageView imageView;


    private MenuItem item;

    MenuListItem(MenuItem item) {
        this.item = item;
        
    }

    @FXML
    void initialize() {
        
        this.itemNameLabel.setText(item.getName());
        this.itemDescLabel.setText(item.getDescription());
        this.itemPriceLabel.setText(String.format("$%.2f", item.getPrice()));
        this.ItemIngLabel.setText(item.getIngredients());
        this.itemVeganLabel.setText(item.isVegan() ? "Vegan" : "");
        this.imageView.setImage(item.getImage());
    }


    @FXML
    void onCart(ActionEvent event) {
        menuController.grabCart().addItem(item);
    }

}
