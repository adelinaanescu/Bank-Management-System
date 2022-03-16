module com.example.finalprojectjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.finalprojectjava to javafx.fxml;
    exports com.example.finalprojectjava;
}