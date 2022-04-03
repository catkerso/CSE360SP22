package com.ViewControllers;

import com.Database.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.Objects.Order;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
public class OrderListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML 
    ArrayList<Order> ordersView;

    @FXML
    ListView<Order> listView;

    @FXML
    void initialize() {
        addData();
        listView.getItems().addAll(ordersView);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void addData(){
        ordersView = Orders.getOrders();
        System.out.println("Added Data: " + ordersView.size());
    }
    
    @FXML
    private void backClicked() throws IOException {
        App.setRoot("managerHome");
        return;
    }
}
