package com.ViewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import com.Database.Auth;
import java.io.IOException;

public class custGuestHomeController {

    @FXML
    private Pane mainPane;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private Text userText;
    @FXML
    private Button editButton;
    @FXML
    private Button signout;

    @FXML 
    public void initialize() {
        userText.setText("Welcome " + 
        (Auth.isSignedIn() ? Auth.getCurrentUser().getFirstName() : "Guest") + 
        "!");
        mainPane.widthProperty().addListener((s, old, n) -> {
            if(menuPane.getWidth() !=0 ) menuPane.setLayoutX(((Double) n / 2) - (menuPane.getWidth() / 2));
        });
         mainPane.heightProperty().addListener((s, old, n) -> {
            if(menuPane.getWidth() !=0 ) menuPane.setLayoutY(((Double) n / 2) - (menuPane.getHeight() / 2));
        });

        if(!Auth.isSignedIn()) {
            editButton.setVisible(false);
            signout.setText("Sign In");
        }
    }

    @FXML
    private void onNewOrder() throws IOException {
        App.setRoot("menu");
    }

    @FXML
    private void onEditProfile() throws IOException {
        App.setRoot("customerProfile");
    }


    @FXML
    private void onSignout() throws IOException {
        if(Auth.isSignedIn()) Auth.signOut();
        App.setRoot("signin");
    }

}
