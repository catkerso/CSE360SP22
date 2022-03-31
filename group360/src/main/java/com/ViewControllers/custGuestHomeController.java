package com.ViewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public void initialize() {
        userText.setText("Welcome " + Auth.getCurrentUser().getFirstName()) + "!");
        mainPane.widthProperty().addListener((s, old, n) -> {
            if(menuPane.getWidth() !=0 ) menuPane.setLayoutX(((Double) n / 2) - (menuPane.getWidth() / 2));
        });
         mainPane.heightProperty().addListener((s, old, n) -> {
            if(menuPane.getWidth() !=0 ) menuPane.setLayoutY(((Double) n / 2) - (menuPane.getHeight() / 2));
        });
    }

    @FXML
    void currentOrdersClicked(ActionEvent event) {

    }

    @FXML
    void viewCustomersClicked(ActionEvent event) {

    }

}
