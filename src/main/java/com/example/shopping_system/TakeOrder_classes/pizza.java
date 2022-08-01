package com.example.shopping_system.TakeOrder_classes;


public class pizza{
private Float Pizza_price;
private String Pizza_order;


public void setPizza_price(Float pizza_price) {
        Pizza_price = pizza_price;
        }

public Float getPizza_price() {
        return Pizza_price;
        }

public void setPizza_order(String pizza_order) {
        Pizza_order = pizza_order;
        }

public String getPizza_order() {
        return Pizza_order;
        }


public pizza(String pizza_order, Float pizza_price) {
        Pizza_price = pizza_price;
        Pizza_order = pizza_order;
        }

public pizza() {

        }
        }
