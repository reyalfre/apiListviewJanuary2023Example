module com.example.apifxejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens com.example.apifxejemplo to javafx.fxml;
    exports com.example.apifxejemplo;
    exports com.example.apifxejemplo.model;
    opens com.example.apifxejemplo.model to javafx.fxml;
    exports com.example.apifxejemplo.controller;
    opens com.example.apifxejemplo.controller to javafx.fxml;
}