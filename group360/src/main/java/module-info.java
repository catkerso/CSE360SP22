module com.ordering_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires jbcrypt;

    opens com.ViewControllers to javafx.fxml;
    exports com.ViewControllers;
}
