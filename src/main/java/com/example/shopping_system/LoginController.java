package com.example.shopping_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.sql.*;
import java.sql.Statement;

public class LoginController {

    String SceneName = "";
    @FXML
    private Label ErrorMessage;

    @FXML
    private ImageView logo;
    @FXML
    private TextField UsernameTextfield, PasswordTextfield;

    @FXML
    private Button UpdateProductsbtn, loginbtn,
            Orderbtn, CheckSalesbtn, LoginScreenbtn;

    public void LoginbtnOnAction(ActionEvent actionEvent) {

        if (!UsernameTextfield.getText().isBlank() && !PasswordTextfield.getText().isBlank()) {
            validateLogin();

        } else {
            ErrorMessage.setText("Please enter username and password");
        }
    }

    public void validateLogin() {
        DataBaseConnection connectNow = new DataBaseConnection();
        Connection connection = connectNow.getConnection();

        //   String verifyLogin = "SELECT * FROM shopping_project.user_account where Username = '" + UsernameTextfield.getText() + "' AND Password = '" + PasswordTextfield.getText() + "'";
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
                    nextpage(SceneName);
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

    public void nextpage(String screenName) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(screenName));
        Stage stage = (Stage) loginbtn.getScene().getWindow();
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
}