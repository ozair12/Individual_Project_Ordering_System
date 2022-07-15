package com.example.shopping_system;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;


public class DataBaseConnection {

    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "shopping_project";
        String databaseUser = "root";
        String databasePassword = "Madmax123@#";
        String url = "jdbc:mysql://localhost:/" + databaseName + "?serverTimezone=UTC";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;

    }
}
