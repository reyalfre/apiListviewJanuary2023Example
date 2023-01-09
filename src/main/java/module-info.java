module com.example.apifxejemplo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires org.json;


    opens com.example.apifxejemplo to javafx.fxml;
    exports com.example.apifxejemplo;
}