package com.example.apifxejemplo;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.json.JSONArray;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainViewController {
    @FXML
    private ListView<Product> listView;

    @FXML
    protected void update() {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://fakestoreapi.com/products")).build();
        cliente.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
                    if (response.statusCode() == 200) {
                        JSONArray dataArray = new JSONArray(response.body());
                        System.out.println(response.body());
                    }
                })
                .join();


        listView.getItems().add(new Product());


    }
}