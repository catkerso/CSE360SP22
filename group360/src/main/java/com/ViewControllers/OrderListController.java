package com.ViewControllers;

import com.Database.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.Objects.Order;
import javafx.fxml.FXML;
import java.io.IOException;
public class OrderListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML 
    ArrayList<Order> ordersView;

    @FXML
    void initialize() {
        //addData();
    }

    //@FXML
    /*public void addData(){
        Orders orders = new Orders();
        orders.addOrder(new Order());
        orders.addOrder();
        ordersView = orders.getOrders();
    }
    */
    @FXML
    private void backClicked() throws IOException {
        App.setRoot("managerHome");
        return;
    }
}
