package com.example.shopping_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class TakeOrders implements Initializable {

    private String mylist;
    @FXML
    private ListView<String> MealDeals, BurgerList, PizzaList,
            DrinksList, OrderList;


    @FXML
    private Button display;


    public void ProductRecords(String product_name, ListView<String> listView) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();


        String verifyLogin = "select * from shopping_project.product_catalogue where Product_Type =  '" + product_name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while (queryResult.next()) {

                String productName = queryResult.getString("Product_Name");
                String Price = queryResult.getString("Product_Price");
                String output = productName + " " + ":" + " " + "£" + Price;
                listView.getItems().add(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ProductRecords("burgers", BurgerList);
        ProductRecords("drinks", DrinksList);
        ProductRecords("meal deals", MealDeals);
        ProductRecords("pizza", PizzaList);


        PizzaList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = PizzaList.getSelectionModel().getSelectedItem();
                System.out.println("clicked on " + OrderList.getSelectionModel().getSelectedItem());
                OrderList.getItems().add(mylist);
            }
        });


        BurgerList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = BurgerList.getSelectionModel().getSelectedItem();
                OrderList.getItems().add(mylist);
            }
        });
        DrinksList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = DrinksList.getSelectionModel().getSelectedItem();
                OrderList.getItems().add(mylist);
            }
        });
        MealDeals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                mylist = MealDeals.getSelectionModel().getSelectedItem();
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


public void calculate(ActionEvent event){

        for(int i = 0; i < OrderList.getItems().toArray().length; i++){
         System.out.println( OrderList.getItems().get(i)) ;

        }

}


}
