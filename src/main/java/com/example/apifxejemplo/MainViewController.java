package com.example.apifxejemplo;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

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
                        for (int i=0 ;i<dataArray.length();i++){
                            JSONObject data = dataArray.getJSONObject(i);
                            //System.out.println(data.getJSONObject(i));
                            var producto= new Product();
                            producto.setId(data.getInt("id"));
                            producto.setName(data.getString("title"));
                            producto.setCategory(data.getString("category"));
                            producto.setDescription(data.getString("description"));
                            producto.setImage(data.getString("image"));
                            producto.setPrice(data.getDouble("price"));
                            listView.getItems().add(producto);
                        }

                        dataArray.forEach(data-> {
                            System.out.println("data: "+data);
                        });


                        System.out.println(response.body());
                    }
                })
                .join();


        listView.getItems().add(new Product());


    }
}