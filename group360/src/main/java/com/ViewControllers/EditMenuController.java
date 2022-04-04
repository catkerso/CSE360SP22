package com.ViewControllers;

import java.io.IOException;

import com.Database.Auth;
import com.Database.Menu;
import com.Objects.MenuItem;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EditMenuController {
    
    @FXML
    ListView<MenuItem> menuList;

    @FXML
    Pane form;

    enum State {
        NONE,
        ADD,
        EDIT
    } State state;

    @FXML
    Label editLabel;


    @FXML 
    TextField nameField, priceField, urlField;

    @FXML
    TextArea descriptionField, ingredientsField;

    @FXML
    Spinner<Integer> timeField;
    SpinnerValueFactory<Integer> timeFactory;

    @FXML
    CheckBox veganBox;

    @FXML
    Button deleteButton;

    @FXML
    Label userLabel;

    MenuItem selectedItem;




    @FXML
    public void initialize() {
        state = State.NONE;
        userLabel.setText(Auth.getCurrentUser().getFullName());
        form.setVisible(false);
        menuList.getItems().addAll(Menu.getMenu());
        System.out.print(Menu.getMenu());
        System.out.println(menuList.getItems().size());

        timeFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 60, 0);
        timeField.setValueFactory(timeFactory);



        menuList.setOnMouseClicked(event -> {
                MenuItem item = menuList.getSelectionModel().getSelectedItem();
                if(item == null) return;
                form.visibleProperty().set(true);                
                System.out.println(item.getName());
                state = State.EDIT;
                editLabel.setText("Edit Menu Item");
                item.print();
                deleteButton.setVisible(true);

                //populate fields
                nameField.setText(item.getName());
                descriptionField.setText(item.getDescription());
                ingredientsField.setText(item.getIngredients());
                priceField.setText(Float.toString(item.getPrice()));
                veganBox.setSelected(item.isVegan());
                urlField.setText(item.getImageURI());
                timeField.getValueFactory().setValue(item.getTime());
                selectedItem = item;

        });
        

    }

    @FXML
    public void deletePressed() {
        if(state != State.EDIT) return;
        Menu.deleteMenuItem(selectedItem.getId());
        menuList.getItems().remove(selectedItem);
        form.visibleProperty().set(false);
        state = State.NONE;
        App.errorDialog("Menu Item Deleted", selectedItem.getName() + " has successfully been deleted");
        selectedItem = null;
    }


    //back to main menu
    @FXML
    public void backPressed() throws IOException {
        App.setRoot("managerHome");
    }

    //add item pressed
    @FXML
    public void addItemPressed() throws IOException {

        form.setVisible(true);
        state = State.ADD;
        selectedItem = null;
        menuList.getSelectionModel().clearSelection();
        editLabel.setText("Add Menu Item");

        deleteButton.setVisible(false);

        //clear fields
        nameField.setText("");
        descriptionField.setText("");
        ingredientsField.setText("");
        priceField.setText("");
        veganBox.setSelected(false);
        urlField.setText("");
        timeField.getValueFactory().setValue(0);

    }


    @FXML
    public void submitPressed() {
        if(state == State.NONE) return;

        //check if all fields are filled
        if(nameField.getText().isEmpty() || priceField.getText().isEmpty() || urlField.getText().isEmpty() || descriptionField.getText().isEmpty() || ingredientsField.getText().isEmpty()) {
            App.errorDialog("Error", "Please fill out all fields");
            return;
        }

        //check if price is a number
        try {
            Double.parseDouble(priceField.getText());
        } catch(NumberFormatException e) {
            App.errorDialog("Error", "Price must be a number");
            return;
        }

        MenuItem item = state == State.ADD ? new MenuItem() : menuList.getSelectionModel().getSelectedItem();
        item.setName(nameField.getText());
        item.setPrice(Float.parseFloat(priceField.getText()));
        item.setImageURI(urlField.getText());
        item.setDescription(descriptionField.getText());
        item.setIngredients(ingredientsField.getText());
        item.setTime(timeField.getValue());
        item.setVegan(veganBox.isSelected());
        if(state == State.ADD) {
            Menu.addMenuItem(item);
            menuList.getItems().add(item);
        } else if(state == State.EDIT) {
            Menu.editMenuItem(selectedItem.getId(), item);
        }


        App.errorDialog("Success", "Item successfully " + (state == State.ADD ? "added" : "edited"));
        state = State.NONE;
        form.setVisible(false);
        menuList.getSelectionModel().clearSelection();
        //reload menuList to update changes
        menuList.getItems().clear();
        menuList.getItems().addAll(Menu.getMenu());

    }

}
