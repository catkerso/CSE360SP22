package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;
import com.Objects.User;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CustomerProfileController {

    
    @FXML Text userName, userRewards, userEmail,userNumber, userAddress;
    @FXML AnchorPane mainPane;
    @FXML VBox vBox;
    @FXML TextField phoneInput, addressInput;

    
    @FXML 
    public void initialize() {
            userName.setText("Name: " + Auth.getCurrentUser().getFullName());
            userEmail.setText("Email: " + Auth.getCurrentUser().getEmail());
            userRewards.setText("Rewards: $" + Auth.getCurrentUser().getRewards());
            userNumber.setText("Phone Number: " + Auth.getCurrentUser().getPhoneNumber());
            userAddress.setText("Address: " + Auth.getCurrentUser().getAddress());
    }

    //update phone number and address
    @FXML
    private void updateInfo() throws IOException{
        String phoneNumber = phoneInput.getText();
        String address = addressInput.getText();


        if(phoneNumber.length() == 0 && address.length() == 0) {
            App.errorDialog("Nothing to update", "Please fill one of the fields and try again.");
            return;
        }

        if(!Auth.isValidPhoneNumber(phoneNumber)) {
            App.errorDialog("Invalid Phone Number", "Please enter a 10-digit phone number");
            return;
        }

        if(phoneNumber.length() != 0){
            Auth.getCurrentUser().setPhoneNumber(phoneNumber);
        }
        if(address.length() != 0) {
            Auth.getCurrentUser().setAddress(address);
        }

        Auth.editUser(Auth.getCurrentUser().getId(), Auth.getCurrentUser());
        App.errorDialog("Info updated", "Your information has been updated");
        App.setRoot("customerProfile");
    }

    //back button
    @FXML
    private void back() throws IOException {
        App.setRoot("custGuestHome");
    }
}


