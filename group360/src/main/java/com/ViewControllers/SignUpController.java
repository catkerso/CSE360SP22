package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;
import com.Objects.User;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SignUpController {

    @FXML AnchorPane loginPane;
    @FXML Pane mainPane;
    @FXML TextField emailInput;
    @FXML TextField fnameInput;
    @FXML TextField lnameInput;
    @FXML PasswordField passInput;

    @FXML
    private void onSignup() throws IOException {
        String email = emailInput.getText();
        String pass = passInput.getText();
        String fname = fnameInput.getText();
        String lname = lnameInput.getText();
        if(email.length() == 0 || pass.length() == 0 || fname.length() == 0 || lname.length() == 0) {
            App.errorDialog("Fields not populated", "Some input field is empty.");
            return;
        }

        if(!Auth.isValidEmailAddress(email)) {
            App.errorDialog("Invalid email.", email + " is not a valid email address.");
            return;
        }

        if(Auth.doesUserExist(email)) {
            App.errorDialog("User found", "A user with this email already exists");
            return;
        }

        Auth.signUp(email, pass, fname, lname);
        App.errorDialog("Account created", "Account created. You may now login");
        App.setRoot("signin");


    }
    @FXML
    private void onSignin() throws IOException {
        App.setRoot("signin");

    }

    

}