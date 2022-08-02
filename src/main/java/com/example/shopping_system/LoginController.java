package com.example.shopping_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class LoginController {

    String SceneName = "";
    @FXML
    private Label ErrorMessage, register_error;

    @FXML
    private TextField UsernameTextfield, PasswordTextfield, username_label,
            name_label, password_label, surname_label;


    @FXML
    private Button UpdateProductsbtn, loginbtn,
            Orderbtn, CheckSalesbtn, LoginScreenbtn, registerbtn, clearbtn;

    private final DataBaseConnection connectNow = new DataBaseConnection();
    private final Connection connection = connectNow.getConnection();

    public void LoginbtnOnAction(ActionEvent actionEvent) {

        if (!UsernameTextfield.getText().isBlank() && !PasswordTextfield.getText().isBlank()) {
            validateLogin();
        } else {
            ErrorMessage.setText("Please enter username and password");
        }
    }

    public void validateLogin() {

        String verifyLogin = "SELECT * FROM shopping_project.user_account ";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while (queryResult.next()) {
                String username = queryResult.getString("UserName");
                String password = queryResult.getString("Password");
                if (UsernameTextfield.getText().equals(username) && PasswordTextfield.getText().equals(password)) {
                    ErrorMessage.setText("successful login");
                    SceneName = "Main-screen.fxml";
                    nextpage(SceneName, loginbtn);
                } else {
                    ErrorMessage.setText("try again");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cancel(ActionEvent event) {
        UsernameTextfield.setText("");
        PasswordTextfield.setText("");
    }

    public void nextpage(String screenName, Button button) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(screenName));
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500));
        stage.setMaximized(true);

    }

    public void TakeOrders(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TakeOrders.fxml"));
        Stage stage = (Stage) Orderbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 1160, 690));
        stage.setMaximized(true);
    }


    public void ReturnToLogin(ActionEvent event) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        Stage stage = (Stage) LoginScreenbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 500, 500));
        stage.setMaximized(true);
    }

    public void CheckSales(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("CheckSales.fxml"));
        Stage stage = (Stage) CheckSalesbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 900, 550));
        stage.setMaximized(true);
    }

    public void UpdateProducts(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UpdateProducts.fxml"));
        Stage stage = (Stage) UpdateProductsbtn.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 550));
        stage.setMaximized(true);
    }


    public void register(ActionEvent event) {
        if (username_label.getText().isEmpty() || password_label.getText().isEmpty()
                || name_label.getText().isEmpty() || surname_label.getText().isEmpty()) {
            register_error.setText("All fields required");
        } else {
            String verifyLogin = "SELECT * FROM shopping_project.user_account ";
            try {
                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);
                while (queryResult.next()) {
                    String name = queryResult.getString("FirstName");
                    ArrayList<String> namelist = new ArrayList<>();
                    namelist.add(name);
                    Boolean exists = true;
                    for (int i = 0; i < namelist.toArray().length; i++) {
                        if (namelist.get(i).equals(name_label.getText())) {
                            exists = false;
                            register_error.setText("User already exists");
                            System.out.println(namelist.get(i));

                        } else {
                            exists = true;
                            break;

                        }

                    }

                    if (exists == true) {
                        registerUser();
                        register_error.setText("Successful Registration");

                    }


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public void registerUser() {

        String register_user = "insert into shopping_project.user_account(FirstName, LastName, UserName, Password) " +
                "values('" + name_label.getText() + "','" + surname_label.getText() + "',"
                + "'" + username_label.getText() + "', '" + password_label.getText() + "');";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(register_user);
            System.out.println("completed");
            SceneName = "Main-screen.fxml";
            nextpage(SceneName, registerbtn);
        } catch (SQLException e) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

public void clear(ActionEvent event){

    username_label.clear();
    password_label.clear();
    name_label.clear();
    surname_label.clear();

}






}