package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;
import com.Objects.User;
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
        queue.setText(Orders.getOrders().indexOf(menuController.cart) + "people ahead of you");
        time.setText(menuController.cart.getTime() + "minutes until order ready");
    }

    @FXML
    private void onCancel() throws IOException {
        Orders.deleteOrder(menuController.cart.getId());
        App.setRoot("custGuestHome");
    }
}

