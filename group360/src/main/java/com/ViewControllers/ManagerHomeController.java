package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ManagerHomeController {
    
    @FXML Text userText;
    @FXML AnchorPane menuPane;
    @FXML Pane mainPane;

    @FXML 
    public void initialize() {
        userText.setText("Logged in as: " + Auth.getCurrentUser().getFullName());
        mainPane.widthProperty().addListener((s, old, n) -> {
            if(menuPane.getWidth() !=0 ) menuPane.setLayoutX(((Double) n / 2) - (menuPane.getWidth() / 2));
        });
         mainPane.heightProperty().addListener((s, old, n) -> {
            if(menuPane.getWidth() !=0 ) menuPane.setLayoutY(((Double) n / 2) - (menuPane.getHeight() / 2));
        });
    }


    @FXML
    private void viewCustomersClicked() throws IOException {
        App.setRoot("viewCustomers");
    }

    @FXML
    private void editMenuClicked() throws IOException  {
        App.setRoot("editMenu");

    }

    @FXML
    private void currentOrdersClicked() throws IOException {
        App.setRoot("OrderList");
        return;
    }
    
}
