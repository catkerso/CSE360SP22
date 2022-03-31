package com.ViewControllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import java.io.IOException;

public class OrderListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void initialize() {

    }
    @FXML
    private void backClicked() throws IOException {
        App.setRoot("managerHome");
        return;
    }
}
