package com.example.apifxejemplo.controller;

import com.example.apifxejemplo.model.Product;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MainViewController {
    @FXML
    private ListView<Product> listView;
    @FXML
    private Button button;

    @FXML
    protected void update() {
        Runnable task =()->{
        button.setDisable(true);
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://fakestoreapi.com/products")).build();

        /*HttpResponse<String> response = null;
        try {
            response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                createProductList((HttpResponse) response);
                prinStatistics();

            }
            Platform.runLater(()->{button.setDisable(false);});
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);

        }
        //return null;
    };
   // new Thread(task).start();*/


    HttpResponse<String> response = null;
            try {
        response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
            if (response.statusCode() == 200) {
        createProductList(response);
        printStatistics();
    }
            Platform.runLater(() ->
                    button.setDisable(false));


};
        new Thread(task).start();
                }




    private void createProductList(HttpResponse <String> response) {
        //System.out.println(response.body);
        JSONArray dataArray = new JSONArray(response.body());
        dataArray.forEach(data -> listView.getItems().add(new Product(
                ((JSONObject) data).getInt("id"),
                ((JSONObject) data).getString("title"),
                ((JSONObject) data).getString("category"),
                ((JSONObject) data).getString("description"),
                ((JSONObject) data).getString("image"),
                ((JSONObject) data).getDouble("price")

        )));
    }

    private void printStatistics() {
        System.out.println("Productos: " + listView.getItems().stream().count());
        System.out.println("Categorias: " + listView.getItems().stream().map(Product::getCategory).distinct().count());
        System.out.println("Categorias: " + listView.getItems().stream().collect(Collectors.groupingBy(x -> x.getCategory(), Collectors.counting())));

        listView.getItems().stream().map(Product::getCategory).distinct().forEach(category -> System.out.println(category + ": " + listView.getItems().stream().filter(product -> product.getCategory().equals(category)).count()));

        DoubleSummaryStatistics estadisticas = listView.getItems().stream().mapToDouble(Product::getPrice).summaryStatistics();
        System.out.println(estadisticas.getMax());
        System.out.println(estadisticas.getMin());
        System.out.println(estadisticas.getAverage());
        System.out.println(estadisticas.getSum());
    }
}



        /*response.thenAccept(res -> {
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
            button.setDisable(false);
            System.out.println("Productos: " + listView.getItems().stream().count());
            System.out.println("Categorias: " + listView.getItems().stream().map(Product::getCategory).distinct().count());
            System.out.println("Categorias: " + listView.getItems().stream().collect(Collectors.groupingBy(x -> x.getCategory(), Collectors.counting())));

            listView.getItems().stream().map(Product::getCategory).distinct().forEach(category -> System.out.println(category + ": " + listView.getItems().stream().filter(product -> product.getCategory().equals(category)).count()));

            DoubleSummaryStatistics estadisticas = listView.getItems().stream().mapToDouble(Product::getPrice).summaryStatistics();
            System.out.println(estadisticas.getMax());
            System.out.println(estadisticas.getMin());
            System.out.println(estadisticas.getAverage());
            System.out.println(estadisticas.getSum());
        });
    }
}*/

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