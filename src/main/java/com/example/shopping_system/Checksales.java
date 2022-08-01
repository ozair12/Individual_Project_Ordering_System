package com.example.shopping_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Checksales implements Initializable {


    @FXML
    private TableView weekly_sales;

    @FXML
    private TableColumn<Products, Float> total_sale;

    @FXML
    private TableColumn<Products, Integer> total_items_bought;

    @FXML
    private TableColumn<Products, String> date_of_sale;

    @FXML
    private TableColumn<Products, Integer> No_drinks, No_burgers, No_pizza;


    @FXML
    private TableView Monthly_Sales;

    @FXML
    private TableColumn<Products, Float> Total_sale;

    @FXML
    private TableColumn<Products, Integer> Total_items_bought;

    @FXML
    private TableColumn<Products, String> Date_of_sale;

    @FXML
    private TableColumn<Products, Integer> No_Drinks, No_Burgers, No_Pizza;
    // lists for retrieved records to be added to

    private final DataBaseConnection connectNow = new DataBaseConnection();
    private final Connection connection = connectNow.getConnection();

    private final ObservableList<Products> Products_List = FXCollections.observableArrayList();


    public void ProductRecords() {

        date_of_sale.setCellValueFactory(new PropertyValueFactory<>("date_of_sale"));
        total_sale.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        total_items_bought.setCellValueFactory(new PropertyValueFactory<>("items_bought"));
        No_burgers.setCellValueFactory(new PropertyValueFactory<>("burger_count"));
        No_drinks.setCellValueFactory(new PropertyValueFactory<>("drinks_count"));
        No_pizza.setCellValueFactory(new PropertyValueFactory<>("pizza_count"));

        String product = "select * from shopping_project.record_sales";

        try {

            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(product);

            while (queryResult.next()) {
                Products_List.add(new Products(queryResult.getString("date_of_sale"), queryResult.getInt("items_bought"), queryResult.getFloat("sale_total"), queryResult.getInt("pizza_Count"),
                        queryResult.getInt("burger_count"), queryResult.getInt("drinks_count")));
                weekly_sales.setItems(Products_List);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        product = "select * from shopping_project.total_sales";

        Date_of_sale.setCellValueFactory(new PropertyValueFactory<>("date_of_sale"));
        Total_sale.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        Total_items_bought.setCellValueFactory(new PropertyValueFactory<>("items_bought"));
        No_Burgers.setCellValueFactory(new PropertyValueFactory<>("burger_count"));
        No_Drinks.setCellValueFactory(new PropertyValueFactory<>("drinks_count"));
        No_Pizza.setCellValueFactory(new PropertyValueFactory<>("pizza_count"));

        try {

            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(product);

            while (queryResult.next()) {
                Products_List.add(new Products(queryResult.getString("date_of_record"), queryResult.getInt("total_items_bought"), queryResult.getFloat("monthly_sales"), queryResult.getInt("monthly_pizza_sold"),
                        queryResult.getInt("monthly_burgers_sold"), queryResult.getInt("monthly_drinks_sold")));
                Monthly_Sales.setItems(Products_List);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProductRecords();


    }


}