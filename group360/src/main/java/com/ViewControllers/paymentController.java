package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;
import com.Objects.User;
import com.Database.Orders;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class paymentController {
    @FXML private TextField fName;
    @FXML private TextField lName;
    @FXML private TextField cardNumber;
    @FXML private TextField expDate;
    @FXML private TextField ccv;
    @FXML private TextField address;
    @FXML private TextField phoneNumber;
    @FXML private Button placeOrder;

    @FXML
    public void initialize() { // initialize all fields with info from customer profile if it exists
        if (Auth.getCurrentUser() != null) {
        fName.setText(Auth.getCurrentUser().getFirstName());
        lName.setText(Auth.getCurrentUser().getLastName());
        if (Auth.getCurrentUser().getCreditCard() != null) {
            cardNumber.setText(Auth.getCurrentUser().getCreditCard().getCardNumber());
            expDate.setText(Auth.getCurrentUser().getCreditCard().getExpDate());
            ccv.setText(Auth.getCurrentUser().getCreditCard().getCcv());
        }
        address.setText(Auth.getCurrentUser().getAddress());
        phoneNumber.setText(Auth.getCurrentUser().getPhoneNumber());
        }
    }

    @FXML
    private void onPlaceOrder() throws IOException {
        String fNameInput = fName.getText();
        String lNameInput = lName.getText();
        String cardInput = cardNumber.getText();
        String expInput = expDate.getText();
        String ccvInput = ccv.getText();
        String addressInput = address.getText();
        String phoneInput = phoneNumber.getText();

        if (fNameInput.length() == 0 || lNameInput.length() == 0 || // check if all fields have inputs
        cardInput.length() == 0 || expInput.length() == 0 || ccvInput.length() == 0 ||
        addressInput.length() == 0 || phoneInput.length() == 0) {
            App.errorDialog("Fields not populated", "Some input field is empty."); // if not print error message
            return;
        }

        int id = Orders.addOrder(menuController.cart); // add order in cart to order list
        menuController.cart.setId(id); // save id to cart object 
        App.setRoot("orderConfirmed");
    }
}
