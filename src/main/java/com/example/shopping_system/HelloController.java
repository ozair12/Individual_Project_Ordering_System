package com.example.shopping_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.action.Action;

import java.sql.*;
import java.util.Map;
import java.sql.Statement;

public class LoginConcole {


    @FXML
    private Label ErrorMessage;


    @FXML
    private TextField UsernameTextfield;

    @FXML
    private TextField PasswordTextfield;


    @FXML
    private Button loginbtn;

    @FXML
    private Button cancelbtn;

    public void LoginbtnOnAction(ActionEvent actionEvent) {

        if (UsernameTextfield.getText().isBlank() == false && PasswordTextfield.getText().isBlank() == false) {
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
                } else {
                    ErrorMessage.setText("try again");
                }
            }
        } catch (Exception e) {
            System.out.println("Incorrect details entered");
            e.printStackTrace();
        }

    }

    public void cancel(ActionEvent event) {
        UsernameTextfield.setText("");
        PasswordTextfield.setText("");
    }

}