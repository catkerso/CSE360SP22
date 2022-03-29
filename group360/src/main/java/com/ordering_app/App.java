package com.ordering_app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

import java.io.IOException;

import com.Helpers.Auth;
import com.Helpers.DB;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static DB db;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("signin"), 600, 400);
        stage.setScene(scene);
        stage.getScene().getRoot().prefHeight(400);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        App.db = new DB("test.json");
        launch();
    }

    public static void errorDialog(String title, String message) {
        Dialog<String> d = new Dialog<>();
        d.setTitle(title);
        d.setContentText(message);
        ButtonType btn = new ButtonType("Ok", ButtonData.OK_DONE);
        d.getDialogPane().getButtonTypes().add(btn);
        d.showAndWait();

    }

}