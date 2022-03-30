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

public class SignInController {
    
    @FXML AnchorPane loginPane;
    @FXML Pane mainPane;
    @FXML TextField emailInput;
    @FXML PasswordField passInput;
    
    @FXML
    public void initialize() {
        mainPane.widthProperty().addListener((s, old, n) -> {
            if(loginPane.getWidth() !=0 ) loginPane.setLayoutX(((Double) n / 2) - (loginPane.getWidth() / 2));
        });
         mainPane.heightProperty().addListener((s, old, n) -> {
            if(loginPane.getWidth() !=0 ) loginPane.setLayoutY(((Double) n / 2) - (loginPane.getHeight() / 2));
        });


        emailInput.requestFocus();
    }

    @FXML
    private void onSignin() throws IOException {
        String email = emailInput.getText();
        String pass = passInput.getText();
        if(Auth.isSignedIn()) {
            System.out.println("user is already signed in, move to next page.");
            return;
        }
        User user;
        if((user = Auth.signIn(email, pass)) != null) {
            App.setRoot(user.isManager() ? "managerHome" : "menu");    
            return;
        }
        
        App.errorDialog("Login failed", "Email and password combination not found");
        System.out.println("Email and password combo not found");
    }
    @FXML
    private void onSignup() throws IOException {
        App.setRoot("signup");

    }

    

}
