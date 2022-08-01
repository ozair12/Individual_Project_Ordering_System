package com.example.shopping_system;

public class Products {


    private Float Price;
    private int Quantity;
    private String Order;
    private String Product_Type;
    private String date_of_sale;
    private int items_bought, pizza_count, burger_count, drinks_count;
    private Float product_price;


    public void setDate_of_sale(String date_of_sale) {
        this.date_of_sale = date_of_sale;
    }

    public String getDate_of_sale() {
        return date_of_sale;
    }

    public void setItems_bought(int items_bought) {
        this.items_bought = items_bought;
    }

    public int getItems_bought() {
        return items_bought;
    }

    public void setPizza_count(int pizza_count) {
        this.pizza_count = pizza_count;
    }

    public int getPizza_count() {
        return pizza_count;
    }


    public void setBurger_count(int burger_count) {
        this.burger_count = burger_count;
    }

    public int getBurger_count() {
        return burger_count;
    }


    public void setDrinks_count(int drinks_count) {
        this.drinks_count = drinks_count;
    }

    public int getDrinks_count() {
        return drinks_count;
    }


    public void setProduct_price(Float product_price) {
        this.product_price = product_price;
    }

    public Float getProduct_price() {
        return product_price;
    }


    public void setPrice(Float price) {
        Price = price;
    }

    public Float getPrice() {
        return Price;
    }


    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getQuantity() {
        return Quantity;
    }


    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }


    public void setProduct_Type(String product_Type) {
        Product_Type = product_Type;
    }

    public String getProduct_Type() {
        return Product_Type;
    }


    public Products() {

    }

    public Products(String order, Float price) {
        Price = price;
        Order = order;
    }

    public Products(String order, Float price, int quantity, String product_Type) {
        Price = price;
        Quantity = quantity;
        Order = order;
        Product_Type = product_Type;
    }

    public Products(String Date_of_sale, int Items_bought, Float Product_price, int Pizza_count, int Burger_count, int Drinks_count) {
        date_of_sale = Date_of_sale;
        items_bought = Items_bought;
        product_price = Product_price;
        pizza_count = Pizza_count;
        burger_count = Burger_count;
        drinks_count = Drinks_count;

    }
}
