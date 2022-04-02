package com.ViewControllers;

import com.Database.Auth;
import com.Database.DB;
import com.Database.Menu;
import com.Objects.MenuItem;
import java.io.*;
import java.util.*;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;

public class menuController {

    @FXML
    private AnchorPane mainPane;
    @FXML
    private VBox outerBoxVBox;
    @FXML
    private HBox outerBoxHBox;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox menuVBox;
    // First Item
    @FXML
    private HBox itemZeroHBox;
    @FXML
    private ImageView itemZeroImageView;
    // Name and Description
    @FXML
    private VBox itemZeroNameDescVBox;
    @FXML
    private Text itemZeroNameText;
    @FXML
    private Text itemZeroDescText;
    // Price and Ingredients
    @FXML
    private VBox itemZeroPriceIngVBox;
    @FXML
    private Text itemZeroPriceText;
    @FXML
    private Text itemZeroIngText;
    @FXML
    private Text itemZeroVeganText;
    // Add
    @FXML
    private Button itemZeroButton;

    // Second Item
    @FXML
    private HBox itemOneHBox;
    @FXML
    private ImageView itemOneImageView;
    // Name and Description
    @FXML
    private VBox itemOneNameDescVBox;
    @FXML
    private Text itemOneNameText;
    @FXML
    private Text itemOneDescText;
    // Price and Ingredients
    @FXML
    private VBox itemOnePriceIngVBox;
    @FXML
    private Text itemOnePriceText;
    @FXML
    private Text itemOneIngText;
    @FXML
    private Text itemOneVeganText;
    // Add
    @FXML
    private Button itemOneButton;

    // Third Item
    @FXML
    private HBox itemTwoHBox;
    @FXML
    private ImageView itemTwoImageView;
    // Name and Description
    @FXML
    private VBox itemTwoNameDescVBox;
    @FXML
    private Text itemTwoNameText;
    @FXML
    private Text itemTwoDescText;
    // Price and Ingredients
    @FXML
    private VBox itemTwoPriceIngVBox;
    @FXML
    private Text itemTwoPriceText;
    @FXML
    private Text itemTwoIngText;
    @FXML
    private Text itemTwoVeganText;
    // Add
    @FXML
    private Button itemTwoButton;

    // Fourth Item
    @FXML
    private HBox itemThreeHBox;
    @FXML
    private ImageView itemThreeImageView;
    // Name and Description
    @FXML
    private VBox itemThreeNameDescVBox;
    @FXML
    private Text itemThreeNameText;
    @FXML
    private Text itemThreeDescText;
    // Price and Ingredients
    @FXML
    private VBox itemThreePriceIngVBox;
    @FXML
    private Text itemThreePriceText;
    @FXML
    private Text itemThreeIngText;
    @FXML
    private Text itemThreeVeganText;
    // Add
    @FXML
    private Button itemThreeButton;

    // Fifth Item
    @FXML
    private HBox itemFourHBox;
    @FXML
    private ImageView itemFourImageView;
    // Name and Description
    @FXML
    private VBox itemFourNameDescVBox;
    @FXML
    private Text itemFourNameText;
    @FXML
    private Text itemFourDescText;
    // Price and Ingredients
    @FXML
    private VBox itemFourPriceIngVBox;
    @FXML
    private Text itemFourPriceText;
    @FXML
    private Text itemFourIngText;
    @FXML
    private Text itemFourVeganText;
    // Add
    @FXML
    private Button itemFourButton;

    // Back Button
    private Button backButton;

    boolean gotMenu = false;
    ArrayList<MenuItem> menu = new ArrayList<MenuItem>(10);
    String strPrice;
    String strVegan;

    public void initialize() {

        if (gotMenu == false) {

            menu = Menu.getMenu();
            gotMenu = true;
        }
        
        Menu.populateMenu();

        itemZeroImageView.setImage(menu.get(0).getImage());
        itemZeroNameText.setText(menu.get(0).getName());
        itemZeroDescText.setText(menu.get(0).getDescription());
        itemZeroPriceText.setText("$" + String.valueOf(menu.get(0).getPrice()));
        itemZeroIngText.setText("Ingredients: " + menu.get(0).getIngredients());
        if (menu.get(0).isVegan() == true)
            itemZeroVeganText.setText("Vegan");
        else
            itemZeroVeganText.setText("");

        itemOneImageView.setImage(menu.get(1).getImage());
        itemOneNameText.setText(menu.get(1).getName());
        itemOneDescText.setText(menu.get(1).getDescription());
        itemOnePriceText.setText("$" + String.valueOf(menu.get(1).getPrice()));
        itemOneIngText.setText("Ingredients: " + menu.get(1).getIngredients());
        if (menu.get(1).isVegan() == true)
            itemOneVeganText.setText("Vegan");
        else
            itemOneVeganText.setText("");

        itemTwoImageView.setImage(menu.get(2).getImage());
        itemTwoNameText.setText(menu.get(2).getName());
        itemTwoDescText.setText(menu.get(2).getDescription());
        itemTwoPriceText.setText("$" + String.valueOf(menu.get(2).getPrice()));
        itemTwoIngText.setText("Ingredients: " + menu.get(2).getIngredients());
        if (menu.get(2).isVegan() == true)
            itemTwoVeganText.setText("Vegan");
        else
            itemTwoVeganText.setText("");

        itemThreeImageView.setImage(menu.get(3).getImage());
        itemThreeNameText.setText(menu.get(3).getName());
        itemThreeDescText.setText(menu.get(3).getDescription());
        itemThreePriceText.setText("$" + String.valueOf(menu.get(3).getPrice()));
        itemThreeIngText.setText("Ingredients: " + menu.get(3).getIngredients());
        if (menu.get(3).isVegan() == true)
            itemThreeVeganText.setText("Vegan");
        else
            itemThreeVeganText.setText("");

        itemFourImageView.setImage(menu.get(4).getImage());
        itemFourNameText.setText(menu.get(4).getName());
        itemFourDescText.setText(menu.get(4).getDescription());
        itemFourPriceText.setText("$" + String.valueOf(menu.get(4).getPrice()));
        itemFourIngText.setText("Ingredients: " + menu.get(4).getIngredients());
        if (menu.get(4).isVegan() == true)
            itemFourVeganText.setText("Vegan");
        else
            itemFourVeganText.setText("");

    }

    @FXML
    private void onBack() throws IOException {
        App.setRoot("custGuestHome");
    }

}
