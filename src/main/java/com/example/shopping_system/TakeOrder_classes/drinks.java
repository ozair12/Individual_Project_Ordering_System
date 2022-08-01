package com.example.shopping_system.TakeOrder_classes;

public class drinks {


    private String drinks_order;
    private Float drinks_price;

    public void setDrinks_order(String drinks_order) {
        this.drinks_order = drinks_order;
    }

    public String getDrinks_order() {
        return drinks_order;
    }

    public void setDrinks_price(Float drinks_price) {
        this.drinks_price = drinks_price;
    }

    public Float getDrinks_price() {
        return drinks_price;
    }


    public drinks(String drinks_order, Float drinks_price) {
        this.drinks_order = drinks_order;
        this.drinks_price = drinks_price;
    }

    public drinks() {

    }
}
