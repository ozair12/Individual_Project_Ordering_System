package com.example.shopping_system;

import com.example.shopping_system.TakeOrder_classes.burger;
import com.example.shopping_system.TakeOrder_classes.drinks;
import com.example.shopping_system.TakeOrder_classes.meal_deals;
import com.example.shopping_system.TakeOrder_classes.pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class TakeOrders implements Initializable {

private  String mylist;
    @FXML
    private TableView<burger> burger_table;
    @FXML
    private TableView<drinks> drinks_table;
    @FXML
    private TableView<com.example.shopping_system.TakeOrder_classes.meal_deals> meal_deals;

    @FXML
    private TableView<pizza> pizza_table;

    // burger table columns
    @FXML
    private TableColumn<burger, String> burger_order;
    @FXML
    private TableColumn<burger, Float> burger_price;


    // pizza table columns
    @FXML
    private TableColumn<pizza, String> pizza_order;
    @FXML
    private TableColumn<pizza, Float> pizza_price;

    @FXML
    private TableColumn<meal_deals, String> mealdeals_order;

    @FXML
    private TableColumn<meal_deals, Float> mealdeals_price;


    @FXML
    private TableColumn<drinks, String> drinks_order;

    @FXML
    private TableColumn<drinks, Float> drinks_price;


    // lists for retrieved records to be added to
    private ObservableList<pizza> observableListpizza = FXCollections.observableArrayList();
    private ObservableList<burger> observableListburger = FXCollections.observableArrayList();
    private ObservableList<meal_deals> observableListmeal_deals = FXCollections.observableArrayList();
    private ObservableList<drinks> observableListdrinks = FXCollections.observableArrayList();


    @FXML
    private ListView<String> OrderList;


    @FXML
    private Button display;

    private DataBaseConnection connectNow = new DataBaseConnection();
    private Connection connection = connectNow.getConnection();

    public void ProductRecords() {


        burger_order.setCellValueFactory(new PropertyValueFactory<burger, String>("burger_order"));
        burger_price.setCellValueFactory(new PropertyValueFactory<burger, Float>("burger_price"));
        pizza_order.setCellValueFactory(new PropertyValueFactory<pizza, String>("Pizza_order"));
        pizza_price.setCellValueFactory(new PropertyValueFactory<pizza, Float>("Pizza_price"));
        mealdeals_order.setCellValueFactory(new PropertyValueFactory<meal_deals, String>("mealdeal_order"));
        mealdeals_price.setCellValueFactory(new PropertyValueFactory<meal_deals, Float>("mealdeal_price"));
        drinks_order.setCellValueFactory(new PropertyValueFactory<drinks, String>("drinks_order"));
        drinks_price.setCellValueFactory(new PropertyValueFactory<drinks, Float>("drinks_price"));


        String product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "burgers" + "'";

        try {

            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(product);


            while (queryResult.next()) {

                //should add the clicked items to the table view

                observableListburger.add(new burger(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                burger_table.setItems(observableListburger);

            }
            product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "pizza" + "'";

            statement = connection.createStatement();
            queryResult = statement.executeQuery(product);


            while (queryResult.next()) {

                //should add the clicked items to the table view

                observableListpizza.add(new pizza(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                pizza_table.setItems(observableListpizza);
            }

            product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "meal deals" + "'";

            statement = connection.createStatement();
            queryResult = statement.executeQuery(product);


            while (queryResult.next()) {

                //should add the clicked items to the table view

                observableListmeal_deals.add(new meal_deals(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                meal_deals.setItems(observableListmeal_deals);
            }
            product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "drinks" + "'";

            statement = connection.createStatement();
            queryResult = statement.executeQuery(product);


            while (queryResult.next()) {

                //should add the clicked items to the table view

                observableListdrinks.add(new drinks(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                drinks_table.setItems(observableListdrinks);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProductRecords();


        pizza_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = String.valueOf(pizza_table.getSelectionModel().getSelectedItem());
                OrderList.getItems().add(mylist);
            }
        });


        burger_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = String.valueOf(burger_table.getSelectionModel().getSelectedItem());
                OrderList.getItems().add(mylist);

                // after button is clicked the item should be added to the table view



            }
        });
        drinks_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = String.valueOf(drinks_table.getSelectionModel().getSelectedItem());
                OrderList.getItems().add(mylist);
            }
        });
        meal_deals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = String.valueOf(meal_deals.getSelectionModel().getSelectedItem());
                OrderList.getItems().add(mylist);
            }
        });

        OrderList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                OrderList.getItems().remove(OrderList.getSelectionModel().getSelectedIndex());

            }
        });
    }


    public void calculate(ActionEvent event) {
        String[] split;
        for (int i = 0; i < OrderList.getItems().toArray().length; i++) {
            System.out.println(OrderList.getItems().get(i));

        }


    }


}
