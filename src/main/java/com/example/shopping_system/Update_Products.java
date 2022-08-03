package com.example.shopping_system;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Update_Products implements Initializable {


    @FXML
    private TextField new_product_name, new_pizza_toppings, new_product_price, new_product_quantity;

    @FXML
    private TextField update_product_name, update_product_toppings, update_product_price, update_product_quantity,
            update_pizza_toppings;

    @FXML
    private Button add_product_btn, delete_product_btn, exitbtn, update_product_btn ;


    @FXML
    private ChoiceBox<String> delete_product_list, product_list, add_product_list;


    private final DataBaseConnection connectNow = new DataBaseConnection();
    private final Connection connection = connectNow.getConnection();

    public ArrayList<String> productNames = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String[] Product_type = {"pizza", "burgers", "meal deals", "drinks", "sides"};
        search();
        product_list.getItems().addAll(productNames);
        delete_product_list.getItems().addAll(productNames);
        add_product_list.getItems().addAll(Product_type);


        add_product_btn.setOnMouseClicked(event -> {
            String topping = new_pizza_toppings.getText();
            String product_name = new_product_name.getText();
            String listvalue = add_product_list.getValue();
            int quantity = Integer.parseInt(new_product_quantity.getText());
            Float price = Float.valueOf(new_product_price.getText());

            String add_products = "insert into shopping_project.product_catalogue(Product_Name, Pizza_Toppings, Product_Type, Product_Price, Product_Quantity) " +
                    "values('" + product_name + "','" + topping + "',"
                    + "'" + listvalue + "', '" + price + "'," + quantity + ");";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(add_products);
                System.out.println("completed");
            } catch (SQLException e) {

            }
        });

        delete_product_btn.setOnMouseClicked(event -> {
            deleteproduct(String.valueOf(delete_product_list.getValue()));
        });

        update_product_btn.setOnMouseClicked(event -> {

            String topping = update_pizza_toppings.getText();
            String product_name = update_product_name.getText();
            String listvalue = product_list.getValue();
            int quantity = Integer.parseInt(update_product_quantity.getText());
            Float price = Float.valueOf(update_product_price.getText());

            if (product_name.isBlank()) {
                update_product_name.setText(listvalue);
            }
            if (topping.isBlank()) {
                update_pizza_toppings.setText("null");
            }
            String update_products = "update shopping_project.product_catalogue set Product_Name = '" + update_product_name.getText() + "', Pizza_Toppings = '" + update_pizza_toppings.getText() +
                    "', Product_Price = '" + price + "', Product_Quantity = '" + quantity + "' where Product_Name = '" + listvalue + "';";
            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(update_products);
                System.out.println("completed");
            } catch (SQLException e) {

            }


        });

        exitbtn.setOnMouseClicked(event -> {
            try {
                Exit();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });
    }
    public void Exit() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main-screen.fxml"));
        Stage stage = (Stage) exitbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500));
        stage.setMaximized(true);
    }


    public void deleteproduct(String productName) {
        String delete = "delete from shopping_project.product_catalogue where Product_Name = '" + productName + "';";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(delete);
        } catch (Exception e) {
            System.out.println("product not found");
        }
    }

    public void search() {

        String list = "select Product_Name from shopping_project.product_catalogue;";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(list);
            while (resultSet.next()) {
                String name = resultSet.getString("Product_Name");
                productNames.add(name);
            }

        } catch (Exception e) {

        }

    }
}