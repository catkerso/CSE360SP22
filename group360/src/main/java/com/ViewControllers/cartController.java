package com.ViewControllers;
import java.util.ArrayList;
import java.util.HashSet;
import com.ViewControllers.menuController;
import com.Objects.MenuItem;
import com.Objects.Order;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import com.Database.Auth;
import java.io.IOException;

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
    Text subToTFloatText;
    @FXML
    Text emptyCart;

// item 1
    @FXML
    private HBox itemOneHBox;
    @FXML
    private Text itemOneNameText;
    @FXML
    private Text itemOneQText;
    @FXML
    private Text itemOnePText;
    @FXML
    private Button itemOneQMinus;
    @FXML
    private Button itemOneQPlus;

// item 2
    @FXML
    private HBox itemTwoHBox;
    @FXML
    private Text itemTwoNameText;
    @FXML
    private Text itemTwoQText;
    @FXML
    private Text itemTwoPText;
    @FXML
    private Button itemTwoQMinus;
    @FXML
    private Button itemTwoQPlus;

    // item 3
    @FXML
    private HBox itemThreeHBox;
    @FXML
    private Text itemThreeNameText;
    @FXML
    private Text itemThreeQText;
    @FXML
    private Text itemThreePText;
    @FXML
    private Button itemThreeQMinus;
    @FXML
    private Button itemThreeQPlus;

    // item 4
    @FXML
    private HBox itemFourHBox;
    @FXML
    private Text itemFourNameText;
    @FXML
    private Text itemFourQText;
    @FXML
    private Text itemFourPText;
    @FXML
    private Button itemFourQMinus;
    @FXML
    private Button itemFourQPlus;

    // item 5
    @FXML
    private HBox itemFiveHBox;
    @FXML
    private Text itemFiveNameText;
    @FXML
    private Text itemFiveQText;
    @FXML
    private Text itemFivePText;
    @FXML
    private Button itemFiveQMinus;
    @FXML
    private Button itemFiveQPlus;

    Order cart = menuController.grabCart();
    ArrayList<MenuItem> orderItems = cart.getItems();
    HashSet<MenuItem> uniqueFinder = new HashSet<MenuItem>(orderItems);
    ArrayList<MenuItem> uniqueItems = new ArrayList<MenuItem>(uniqueFinder);
    float subtotal = 0;
    int orderSize = 0;
    int itemOneCount = 0;
    int itemTwoCount = 0;
    int itemThreeCount = 0;
    int itemFourCount = 0;
    int itemFiveCount = 0;
    @FXML
    void initialize() {
    // figure out what is actually in the cart
        
        if (orderItems != null){
            emptyCart.setVisible(false);
            orderSize = orderItems.size();
        }

// causes a NULL Pointer exception if all instances of the item are removed, this should just prevent the Hbox from generating
// but instead it causes an error. Don't understand it. This same problem exists throghout, and is commented out in the other
// items
        
// Hbox 1 - all of the items follow the same pattern of code, functionally all identical
        if(uniqueItems.size() == 0){
            itemOneHBox.setManaged(false);
        }

// action watchers for quantity buttons
        else {
            itemOneQMinus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for (int i=0; i<orderSize; i++){
                        if(orderItems.get(i) == uniqueItems.get(0)){
                            orderItems.remove(i);
// here is where the handling probably needs to be for a check for removing index from uniqueItems if last instance of that order item has been removed
                            break;
                        }
                    }
                    initialize();
                }
            });
            itemOneQPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    cart.addItem(uniqueItems.get(0));;
                    initialize();
                }
            });
            
            itemOneNameText.setText(uniqueItems.get(0).getName());
// count instances of same order item
            itemOneCount = 0; 
            for (int i=0; i<orderSize; i++){
                if(orderItems.get(i) == uniqueItems.get(0)){
                    itemOneCount++;
                }
            }
// this might not be useful or necessary
            if (itemOneCount == 0){
                itemOneHBox.setVisible(false);
            }
            itemOneQText.setText(String.valueOf(itemOneCount));
            float itemOneTotal = itemOneCount * uniqueItems.get(0).getPrice();
            itemOnePText.setText(String.valueOf(itemOneTotal));
// subtotal for that item
            subtotal = itemOneCount * uniqueItems.get(0).getPrice();
        }


        // Hbox 2
        /*
        if(uniqueItems.size() < 2){
            itemOneHBox.setManaged(false);
        }
        */
        if (uniqueItems.size() < 2){

        }
        else {
            itemTwoQMinus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for (int i=0; i<orderSize; i++){
                        if(orderItems.get(i) == uniqueItems.get(1)){
                            orderItems.remove(i);
                            break;
                        }
                    }
                    initialize();
                }
            });
            itemTwoQPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    cart.addItem(uniqueItems.get(1));;
                    initialize();
                }
            });
            
            itemTwoNameText.setText(uniqueItems.get(1).getName());
            itemTwoCount = 0; 
            for (int i=0; i<orderSize; i++){
                if(orderItems.get(i) == uniqueItems.get(1)){
                    itemTwoCount++;
                }
            }
            if (itemTwoCount == 0){
                itemTwoHBox.setVisible(false);
            }
            itemTwoQText.setText(String.valueOf(itemTwoCount));
            float itemTwoTotal = itemTwoCount * uniqueItems.get(1).getPrice();
            itemTwoPText.setText(String.valueOf(itemTwoTotal));
            subtotal = subtotal + itemTwoCount * uniqueItems.get(1).getPrice();
        }


        // Hbox 3
        /*
        if(uniqueItems.size() < 2){
            itemOneHBox.setManaged(false);
        }
        */
        if (uniqueItems.size() < 3){
            
        }
        else {
            itemThreeQMinus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for (int i=0; i<orderSize; i++){
                        if(orderItems.get(i) == uniqueItems.get(2)){
                            orderItems.remove(i);
                            break;
                        }
                    }
                    initialize();
                }
            });
            itemThreeQPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    cart.addItem(uniqueItems.get(2));;
                    initialize();
                }
            });
            
            itemThreeNameText.setText(uniqueItems.get(2).getName());
            itemThreeCount = 0; 
            for (int i=0; i<orderSize; i++){
                if(orderItems.get(i) == uniqueItems.get(2)){
                    itemThreeCount++;
                }
            }
            if (itemThreeCount == 0){
                itemThreeHBox.setVisible(false);
            }
            itemThreeQText.setText(String.valueOf(itemThreeCount));
            float itemThreeTotal = itemThreeCount * uniqueItems.get(2).getPrice();
            itemThreePText.setText(String.valueOf(itemThreeTotal));
            subtotal = subtotal + itemThreeCount * uniqueItems.get(2).getPrice();
        }

                // Hbox 3
        /*
        if(uniqueItems.size() < 2){
            itemOneHBox.setManaged(false);
        }
        */
        if (uniqueItems.size() < 4){
            
        }
        else {
            itemFourQMinus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for (int i=0; i<orderSize; i++){
                        if(orderItems.get(i) == uniqueItems.get(3)){
                            orderItems.remove(i);
                            break;
                        }
                    }
                    initialize();
                }
            });
            itemFourQPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    cart.addItem(uniqueItems.get(3));;
                    initialize();
                }
            });
            
            itemFourNameText.setText(uniqueItems.get(3).getName());
            itemFourCount = 0; 
            for (int i=0; i<orderSize; i++){
                if(orderItems.get(i) == uniqueItems.get(3)){
                    itemFourCount++;
                }
            }
            if (itemFourCount == 0){
                itemFourHBox.setVisible(false);
            }
            itemFourQText.setText(String.valueOf(itemFourCount));
            float itemFourTotal = itemFourCount * uniqueItems.get(3).getPrice();
            itemFourPText.setText(String.valueOf(itemFourTotal));
            subtotal = subtotal + itemFourCount * uniqueItems.get(3).getPrice();
        }

                // Hbox 4
        /*
        if(uniqueItems.size() < 4){
            itemOneHBox.setManaged(false);
        }
        */
        if (uniqueItems.size() < 5){
            
        }
        else {
            itemFiveQMinus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    for (int i=0; i<orderSize; i++){
                        if(orderItems.get(i) == uniqueItems.get(4)){
                            orderItems.remove(i);
                            break;
                        }
                    }
                    initialize();
                }
            });
            itemFiveQPlus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    cart.addItem(uniqueItems.get(4));;
                    initialize();
                }
            });
            
            itemFiveNameText.setText(uniqueItems.get(4).getName());
            itemFiveCount = 0; 
            for (int i=0; i<orderSize; i++){
                if(orderItems.get(i) == uniqueItems.get(4)){
                    itemFiveCount++;
                }
            }
            if (itemFiveCount == 0){
                itemFiveHBox.setVisible(false);
            }
            itemFiveQText.setText(String.valueOf(itemFiveCount));
            float itemFiveTotal = itemFiveCount * uniqueItems.get(4).getPrice();
            itemFivePText.setText(String.valueOf(itemFiveTotal));
            subtotal = subtotal + itemFiveCount * uniqueItems.get(4).getPrice();
        }




    // another null pointer exception I don't understand...
  //      subToTFloatText.setText("$" + String.valueOf(subtotal));
    }

    @FXML
    private void onMenu() throws IOException {
        App.setRoot("menu");
    }
    @FXML
    private void onCheckout(ActionEvent event) throws IOException {
        App.setRoot("Payment");
    }


   
}
