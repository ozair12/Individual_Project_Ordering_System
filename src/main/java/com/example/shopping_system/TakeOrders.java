package com.example.shopping_system;

import com.example.shopping_system.TakeOrder_classes.burger;
import com.example.shopping_system.TakeOrder_classes.drinks;
import com.example.shopping_system.TakeOrder_classes.meal_deals;
import com.example.shopping_system.TakeOrder_classes.pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

public class TakeOrders implements Initializable {


    private Float total = 0.0f, change = 0.0f, cash = 0.0f;

    private String chosen_payment = " ";

    public ArrayList<Float> arrayList = new ArrayList<>();

    @FXML
    private TextField cashtxt, changetxt, taxtxt, subtotaltxt, totaltxt;

    @FXML
    private TableView<pizza> pizza_table;
    @FXML
    private TableColumn<pizza, String> pizza_order;
    @FXML
    private TableColumn<pizza, Float> pizza_price;

    //Table view for burger
    @FXML
    private TableView<burger> burger_table;
    @FXML
    private TableColumn<burger, String> burger_order;
    @FXML
    private TableColumn<burger, Float> burger_price;


    @FXML
    private TableView<drinks> drinks_table;

    @FXML
    private TableColumn<drinks, String> drinks_order;

    @FXML
    private TableColumn<drinks, Float> drinks_price;


    @FXML
    private TableView<meal_deals> meal_deals;
    @FXML
    private TableColumn<meal_deals, String> mealdeals_order;

    @FXML
    private TableColumn<meal_deals, Float> mealdeals_price;

    @FXML
    private TableView<Products> checkout;
    @FXML
    private TableColumn<Products, String> checkout_order;
    @FXML
    private TableColumn<Products, Float> checkout_price;


    @FXML
    private ChoiceBox<String> payment_method;


    // lists for retrieved records to be added to
    private final ObservableList<pizza> observableListpizza = FXCollections.observableArrayList();
    private final ObservableList<burger> observableListburger = FXCollections.observableArrayList();
    private final ObservableList<meal_deals> observableListmeal_deals = FXCollections.observableArrayList();
    private final ObservableList<drinks> observableListdrinks = FXCollections.observableArrayList();
    private final ObservableList<Products> observableList = FXCollections.observableArrayList();


    private final DataBaseConnection connectNow = new DataBaseConnection();
    private final Connection connection = connectNow.getConnection();

    public int itemcount = 0, pizza_count = 0, burger_count = 0, drinks_count = 0;

    public ArrayList<String> checkout_arraylist = new ArrayList<>();

    public ArrayList<Integer> drinksmonthly = new ArrayList<>();
    public ArrayList<Integer> burgersmonthly = new ArrayList<>();
    public ArrayList<Integer> pizzamonthly = new ArrayList<>();

    @FXML
    private Button resetbtn, exitbtn;
    public void ProductRecords() {
        burger_order.setCellValueFactory(new PropertyValueFactory<>("burger_order"));
        burger_price.setCellValueFactory(new PropertyValueFactory<>("burger_price"));
        pizza_order.setCellValueFactory(new PropertyValueFactory<>("Pizza_order"));
        pizza_price.setCellValueFactory(new PropertyValueFactory<>("Pizza_price"));
        mealdeals_order.setCellValueFactory(new PropertyValueFactory<>("mealdeal_order"));
        mealdeals_price.setCellValueFactory(new PropertyValueFactory<>("mealdeal_price"));
        drinks_order.setCellValueFactory(new PropertyValueFactory<>("drinks_order"));
        drinks_price.setCellValueFactory(new PropertyValueFactory<>("drinks_price"));
        checkout_order.setCellValueFactory(new PropertyValueFactory<>("Order"));
        checkout_price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        String product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "burgers" + "'";

        try {

            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(product);

            while (queryResult.next()) {

                observableListburger.add(new burger(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                burger_table.setItems(observableListburger);
            }
            product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "pizza" + "'";

            statement = connection.createStatement();
            queryResult = statement.executeQuery(product);


            while (queryResult.next()) {


                observableListpizza.add(new pizza(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                pizza_table.setItems(observableListpizza);
            }

            product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "meal deals" + "'";

            statement = connection.createStatement();
            queryResult = statement.executeQuery(product);


            while (queryResult.next()) {

                observableListmeal_deals.add(new meal_deals(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                meal_deals.setItems(observableListmeal_deals);

            }
            product = "select * from shopping_project.product_catalogue where Product_Type =  '" + "drinks" + "'";

            statement = connection.createStatement();
            queryResult = statement.executeQuery(product);


            while (queryResult.next()) {

                observableListdrinks.add(new drinks(queryResult.getString("Product_Name"), queryResult.getFloat("Product_Price")));
                drinks_table.setItems(observableListdrinks);

            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        calculate_monthly_sales();
        ProductRecords();

        //payment method
        String[] paymentmethod = {"Card", "Cash"};
        payment_method.getItems().addAll(paymentmethod);


        pizza_table.setOnMouseClicked(event -> {

            pizza pizza;

            pizza = pizza_table.getItems().get(pizza_table.getSelectionModel().getSelectedIndex());
            String order = pizza.getPizza_order();
            Products products = new Products();
            Float price = pizza.getPizza_price();
            products.setOrder(order);
            products.setPrice(price);

            observableList.add(new Products(order, price));
            checkout.setItems(observableList);

            arrayList.add(products.getPrice());
            itemcount += 1;
            checkout_arraylist.add(order);
            pizza_count += 1;
        });

        burger_table.setOnMouseClicked(event -> {

            burger burger;

            burger = burger_table.getItems().get(burger_table.getSelectionModel().getSelectedIndex());
            String order = burger.getBurger_order();
            Products products = new Products();
            Float price = burger.getBurger_price();
            products.setOrder(order);
            products.setPrice(price);

            observableList.add(new Products(order, price));
            checkout.setItems(observableList);

            arrayList.add(products.getPrice());
            itemcount += 1;
            burger_count += 1;
            checkout_arraylist.add(order);

        });


        meal_deals.setOnMouseClicked(event -> {

            meal_deals meal_deals1;

            meal_deals1 = meal_deals.getItems().get(meal_deals.getSelectionModel().getSelectedIndex());
            String order = meal_deals1.getMealdeal_order();
            Products products = new Products();
            Float price = meal_deals1.getMealdeal_price();
            products.setOrder(order);
            products.setPrice(price);

            observableList.add(new Products(order, price));
            checkout.setItems(observableList);

            arrayList.add(products.getPrice());
            itemcount += 1;


        });

        drinks_table.setOnMouseClicked(event -> {

            drinks drinks;

            drinks = drinks_table.getItems().get(drinks_table.getSelectionModel().getSelectedIndex());
            String order = drinks.getDrinks_order();
            Products products = new Products();
            Float price = drinks.getDrinks_price();
            products.setOrder(order);
            products.setPrice(price);

            observableList.add(new Products(order, price));
            checkout.setItems(observableList);

            arrayList.add(products.getPrice());
            itemcount += 1;
            checkout_arraylist.add(order);

            drinks_count += 1;

        });

        // currently working here
        checkout.setOnMouseClicked(event -> {
            Products productsAdded = checkout.getItems().get(checkout.getSelectionModel().getSelectedIndex());

            checkout.getItems().remove(checkout.getSelectionModel().getSelectedIndex());
            itemcount -= 1;

            for (int i = 0; i < checkout_arraylist.toArray().length; i++) {
                if (checkout_arraylist.get(i).equals(productsAdded.getOrder())) {
                    pizza_count -= 1;
                }
                break;
            }

            for (int i = 0; i < checkout_arraylist.toArray().length; i++) {
                if (checkout_arraylist.get(i).equals(productsAdded.getOrder())) {
                    burger_count -= 1;
                }
                break;
            }
            for (int i = 0; i < checkout_arraylist.toArray().length; i++) {
                if (checkout_arraylist.get(i).equals(productsAdded.getOrder())) {
                    drinks_count -= 1;
                }
                break;
            }


        });

        resetbtn.setOnMouseClicked(event -> {

            checkout_arraylist.clear();
            changetxt.clear();
            checkout.getItems().clear();
            taxtxt.clear();
            totaltxt.clear();
            subtotaltxt.clear();
            cashtxt.clear();

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

    public void total() {
        total = 0.0f;
        change = 0.0f;
        Float tax = 0.8f;
        cash = 0.0f;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        chosen_payment = payment_method.getValue();

        for (int i = 0; i < arrayList.toArray().length; i++) {
            total += arrayList.get(i);
        }
        Float format;
        format = Float.valueOf(decimalFormat.format(total));

        subtotaltxt.setText("£" + format);


        taxtxt.setText(tax + "%");


        Float postTex = total * tax;
        format = Float.valueOf(decimalFormat.format(postTex));

        tax = total - format;
        total = tax + total;
        format = Float.valueOf(decimalFormat.format(total));
        total = format;
        totaltxt.setText("£" + format);

    }


    public void change() {


        if (cashtxt.getText().isBlank() && chosen_payment.equals("Cash")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("cash required");
            alert.showAndWait();
        } else {
            cash = Float.valueOf(cashtxt.getText());
            change = cash - total;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            float format = Float.parseFloat(decimalFormat.format(change));

            changetxt.setText("£" + format);
            date();

        }

    }

    public void date() {

        LocalDateTime localDateTime = LocalDateTime.now();
        String date = String.valueOf(localDateTime.toLocalDate());


        String newdate = "insert into shopping_project.record_sales(date_of_sale, sale_total, items_bought, pizza_count, burger_count, drinks_count)" + " values('" + date + "', '" + total + "' , '" + itemcount + "','"
                + pizza_count + "','" + burger_count + "','" + drinks_count + "' );";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(newdate);
        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public void monthSales() {
        String sales = "select * from shopping_project.record_sales";

        LocalDate localDate = LocalDate.now();

        String[] split = localDate.toString().split("-");
        String finaltime = split[0] + split[1];

        Float monthlysales = 0.0f;
        int monthlydrinks = 0;
        int monthlyburgers = 0;
        int monthlypizza = 0;
        int total_items_sold = 0;
        ArrayList<Float> monthlySales = new ArrayList<>();
        ArrayList<Integer> total_bought_items = new ArrayList<>();
        try {

            Statement statement = connection.createStatement();

            ResultSet query = statement.executeQuery(sales);
            while (query.next()) {

                String date = query.getString("date_of_sale");
                Float sale = query.getFloat("sale_total");
                int drinks = query.getInt("drinks_count");
                int burgers = query.getInt("burger_count");
                int pizza = query.getInt("pizza_count");
                int total_items = query.getInt("items_bought");
                split = date.split("-");
                String datelist = split[0] + split[1];


                if (datelist.equals(finaltime)) {
                    monthlySales.add(sale);
                    drinksmonthly.add(drinks);
                    burgersmonthly.add(burgers);
                    pizzamonthly.add(pizza);
                    total_bought_items.add(total_items);

                }
            }
//outside query
            for (int i = 0; i < monthlySales.toArray().length; i++) {
                monthlysales += monthlySales.get(i);
            }
            for (int i = 0; i < drinksmonthly.toArray().length; i++) {
                monthlydrinks += drinksmonthly.get(i);
            }
            for (int i = 0; i < pizzamonthly.toArray().length; i++) {
                monthlypizza += pizzamonthly.get(i);
            }
            for (int i = 0; i < burgersmonthly.toArray().length; i++) {
                monthlyburgers += burgersmonthly.get(i);
            }

            for (int i = 0; i < total_bought_items.toArray().length; i++) {
                total_items_sold += total_bought_items.get(i);
            }
            System.out.println(monthlysales);

        } catch (Exception e) {
            e.printStackTrace();

        }
        LocalDate localDate1 = LocalDate.now();
        String date = String.valueOf(localDate1);

        // second query inserting monthly sales into table
        // check to see if monthly sales has already been added

        String update = "insert into shopping_project.total_sales(monthly_sales, date_of_record,  monthly_drinks_sold, monthly_burgers_sold, monthly_pizza_sold, total_items_bought)values ('" + monthlysales + "','" + date + "','" + monthlydrinks +
                "','" + monthlyburgers + "','" + monthlypizza + "','" + total_items_sold + "');";
        try {

            Statement statement = connection.createStatement();
            statement.executeUpdate(update);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }


    public void calculate_monthly_sales() {

        //monthSales();

        Calendar cal = Calendar.getInstance();

        Date current_date = new Date();
        String[] current_date_split = current_date.toString().split(" ");
        String Current_date = current_date_split[0] + current_date_split[1] + current_date_split[2];

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = cal.getTime();

        String[] end_of_month = lastDayOfMonth.toString().split(" ");
        String end_of_month_date = end_of_month[0] + end_of_month[1] + end_of_month[2];
        String testdate = "SatJul30";

        if (testdate.equals(Current_date)) {

            String date_of_record = "select * from shopping_project.total_sales";

            try {
                LocalDate localDate = LocalDate.now();
                String Current_Date = String.valueOf(localDate);
                Statement statement = connection.createStatement();
                ResultSet query = statement.executeQuery(date_of_record);
                ArrayList<String> dates_added = new ArrayList<>();

                while (query.next()) {

                    String testDate = query.getString("date_of_record");
                    dates_added.add(testDate);

                }
                boolean bool = true;
                for (int i = 0; i < dates_added.toArray().length; i++) {
                    bool = !dates_added.get(i).equals(Current_Date);
                }
                if (bool) {
                    monthSales();
                }
            } catch (SQLException ignored) {
            }

            System.out.println("Today is the last day of the month and monthly sales will be calculated");
        }

    }



}

