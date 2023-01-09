module com.example.apifxejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;


    opens com.example.apifxejemplo to javafx.fxml;
    exports com.example.apifxejemplo;
}