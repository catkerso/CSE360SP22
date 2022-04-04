package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;
import com.Objects.User;
import com.Objects.Order;
import com.Database.Orders;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class orderConfirmedController {
    @FXML private TextArea queue;
    @FXML private TextArea time;
    @FXML private Button cancel;
    
    @FXML
    public void initialize() { 
        int id = menuController.cart.getId(); // save id as int
        int i = 0;
        int totTime = 0;
        for(Order order : Orders.getOrders()) { // check for matching order and track iteration
            totTime = totTime + order.getTime();
            if(order.getId() == id) {
              break;
            }
            i++;
        }
        queue.setText(i + " people ahead of you"); // print messages in text fields
        time.setText(totTime + " minutes until order ready");
    }

    @FXML
    private void onCancel() throws IOException {
        Orders.deleteOrder(menuController.cart.getId()); // delete order and return to customer home
        App.setRoot("custGuestHome");
    }
}

