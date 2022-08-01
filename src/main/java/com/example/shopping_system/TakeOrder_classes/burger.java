package com.example.shopping_system.TakeOrder_classes;

public class burger {

    private String burger_order;
    private Float burger_price;


    public void setBurger_order(String burger_order) {
        this.burger_order = burger_order;
    }

    public String getBurger_order() {
        return burger_order;
    }

    public void setBurger_price(Float burger_price) {
        this.burger_price = burger_price;
    }

    public Float getBurger_price() {
        return burger_price;
    }


    public burger() {

    }

    public burger(String burger_order, Float burger_price) {
        this.burger_order = burger_order;
        this.burger_price = burger_price;
    }
}
