package com.example.apifxejemplo.controller;

import com.example.apifxejemplo.model.Product;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class MainViewController {
    @FXML
    private ListView<Product> listView;

    @FXML
    protected void update() {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://fakestoreapi.com/products")).build();

        CompletableFuture<HttpResponse<String>> response = cliente.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        response.thenAccept(res -> {
            if (res.statusCode() == 200) {
                JSONArray dataArray = new JSONArray(res.body());


                dataArray.forEach(data -> listView.getItems().add(new Product(
                        ((JSONObject) data).getInt("id"),
                        ((JSONObject) data).getString("title"),
                        ((JSONObject) data).getString("category"),
                        ((JSONObject) data).getString("description"),
                        ((JSONObject) data).getString("image"),
                        ((JSONObject) data).getDouble("price")

                )));
            }
        });
    }}

            /*cliente.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenAccept(response -> {
                        if (response.statusCode() == 200) {
                            JSONArray dataArray = new JSONArray(response.body());
                            for (int i = 0; i < dataArray.length(); i++) {
                                JSONObject data = dataArray.getJSONObject(i);
                                //System.out.println(data.getJSONObject(i));
                                var producto = new Product();
                                producto.setId(data.getInt("id"));
                                producto.setName(data.getString("title"));
                                producto.setCategory(data.getString("category"));
                                producto.setDescription(data.getString("description"));
                                producto.setImage(data.getString("image"));
                                producto.setPrice(data.getDouble("price"));
                                listView.getItems().add(producto);
                            }

                            dataArray.forEach(data -> {
                                System.out.println("data: " + data);
                            });


                            System.out.println(response.body());
                        }
                    })
                    .join();


            listView.getItems().add(new Product());


        }
    }
    */