package com.ViewControllers;

import com.Database.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.Objects.Order;
import com.Objects.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

import java.io.IOException;
public class UserListController {
    ArrayList<User> allCust;
    @FXML
    ListView<User> currentCustView;
    @FXML
    TextField searchField;
    @FXML
    Button searchButton;
    @FXML
    void initialize(){
        addData();

    }
    @FXML
    private void addData(){
        allCust = Auth.getCustomers();
        filterData();
    }
    private void filterData(){
        String filter = searchField.getText().trim();
        currentCustView.getItems().clear();
        if (filter.length() == 0){
            currentCustView.getItems().addAll(allCust);
        }
        else{
            for (User user : allCust){
                if (user.getFullName().indexOf(filter) != -1 || 
                        user.getAddress().indexOf(filter) != -1 || 
                        user.getPhoneNumber().indexOf(filter) != -1 || 
                        user.getEmail().indexOf(filter) != -1){
                    currentCustView.getItems().add(user);
                }
            }
        }
    }
    @FXML
    private void searchClicked() throws IOException{
        filterData();
    }
    @FXML
    private void backClicked() throws IOException {
        App.setRoot("managerHome");
    }

}
