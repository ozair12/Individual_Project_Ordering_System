package com.example.shopping_system;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TakeOrders implements Initializable {

   private String mylist;
    @FXML
    private ListView<String> BurgerList;

    @FXML
    private ListView<String> MealDeals;

    @FXML
    private ListView<String> PizzaList;

    @FXML
    private ListView<String> DrinksList;

@FXML
private ListView<String> OrderList;




    @FXML
    private Button display;



    public void validateLogin(String product_name, ListView<String> listView) {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();


        String verifyLogin = "select * from shopping_project.product_catalogue where Product_Type =  '" + product_name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);


            while (queryResult.next()) {

                String productName = queryResult.getString("Product_Name");
                String Price = queryResult.getString("Product_Price");
                String output = productName  + " " + ":" + " " + "£" + Price ;
                listView.getItems().add(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
validateLogin("burgers", BurgerList);
        validateLogin("drinks", DrinksList);
        validateLogin("meal deals", MealDeals);
        validateLogin("pizza", PizzaList);


        DrinksList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                mylist = DrinksList.getSelectionModel().getSelectedItem();


                OrderList.getItems().add(mylist);

            }
        });

    }

}
