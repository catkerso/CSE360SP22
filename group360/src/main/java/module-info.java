module com.ordering_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jbcrypt;

    opens com.ordering_app to javafx.fxml;
    exports com.ordering_app;
}
