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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.PropertySheet;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.*;

public class TakeOrders implements Initializable {


   private   Float total = 0.0f, change = 0.0f  ,tax = 0.8f   , cash = 0.0f   ;

private  String mylist,  chosen_payment = " " ;

public ArrayList<Float> arrayList = new ArrayList<>();

@FXML
private TextField cashtxt,  changetxt, taxtxt, subtotaltxt, totaltxt ;



@FXML
private ChoiceBox<String > payment_method;
    @FXML
    private TableView<burger> burger_table;
    @FXML
    private TableView<drinks> drinks_table;
    @FXML
    private TableView<com.example.shopping_system.TakeOrder_classes.meal_deals> meal_deals;

    @FXML
    private TableView checkout;
    @FXML
    private TableView<pizza> pizza_table;

    @FXML
    private TableColumn <Products, String> checkout_order;
    @FXML
    private TableColumn<Products, Float> checkout_price;


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
    private ObservableList<Products> observableList = FXCollections.observableArrayList();


    @FXML
    private ListView<Products> OrderList;


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
        checkout_order.setCellValueFactory(new PropertyValueFactory<Products, String>("Order"));
        checkout_price.setCellValueFactory(new PropertyValueFactory<Products, Float>("Price"));
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

        //payment method
        String[] paymentmethod = {"Card","Cash"};
payment_method.getItems().addAll(paymentmethod);

        pizza_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                pizza pizza = new pizza();

                pizza = pizza_table.getItems().get(pizza_table.getSelectionModel().getSelectedIndex());
                String order = pizza.getPizza_order();
                Products products = new Products();
                Float price = pizza.getPizza_price();
                products.setOrder(order);
                products.setPrice(price);

                observableList.add(new Products(order, price));
                checkout.setItems(observableList);

                arrayList.add(products.getPrice());

            }
        });

        burger_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                burger burger = new burger();

                burger = burger_table.getItems().get(burger_table.getSelectionModel().getSelectedIndex());
                String order = burger.getBurger_order();
                Products products = new Products();
                Float price = burger.getBurger_price();
                products.setOrder(order);
                products.setPrice(price);

                observableList.add(new Products(order, price));
                checkout.setItems(observableList);

                arrayList.add(products.getPrice());

            }
        });


        meal_deals.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                meal_deals meal_deals1 = new meal_deals();

                meal_deals1 = meal_deals.getItems().get(meal_deals.getSelectionModel().getSelectedIndex());
                String order = meal_deals1.getMealdeal_order();
                Products products = new Products();
                Float price = meal_deals1.getMealdeal_price();
                products.setOrder(order);
                products.setPrice(price);

                observableList.add(new Products(order, price));
                checkout.setItems(observableList);

                arrayList.add(products.getPrice());

            }
        });

        drinks_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                drinks drinks = new drinks();

                drinks = drinks_table.getItems().get(drinks_table.getSelectionModel().getSelectedIndex());
                String order = drinks.getDrinks_order();
                Products products = new Products();
                Float price = drinks.getDrinks_price();
                products.setOrder(order);
                products.setPrice(price);

                observableList.add(new Products(order, price));
                checkout.setItems(observableList);

                arrayList.add(products.getPrice());

            }
        });
        checkout.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                checkout.getItems().remove(checkout.getSelectionModel().getSelectedIndex());

            }
        });
    }

    public void total(ActionEvent event) {
 total = 0.0f;
 change = 0.0f;
 tax = 0.8f;
 cash = 0.0f;

 DecimalFormat decimalFormat = new DecimalFormat("#.##");



 chosen_payment = payment_method.getValue();




        for(int i = 0; i < arrayList.toArray().length; i++){
            total+=arrayList.get(i);
        }
        Float format = Float.valueOf(decimalFormat.format(tax));
        format = Float.valueOf(decimalFormat.format(total));

        subtotaltxt.setText(String.valueOf(total));


        taxtxt.setText(String.valueOf(tax));



        Float postTex = total * tax;
        format = Float.valueOf(decimalFormat.format(postTex));

        tax = total - format;
        total = tax + total;
         format = Float.valueOf(decimalFormat.format(total));
total =format;
        totaltxt.setText(String.valueOf( format ));


    }


    public void change(ActionEvent event){

        if( cashtxt.getText().isBlank() && chosen_payment.equals("Cash")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("cash required");
            alert.showAndWait();
        }else{
            cash = Float.valueOf(cashtxt.getText());
            change = cash - total;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            Float format = Float.valueOf(decimalFormat.format(change));

            changetxt.setText(String.valueOf(format));
        }

    }
    }

