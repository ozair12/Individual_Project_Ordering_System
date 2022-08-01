package com.example.shopping_system.TakeOrder_classes;

public class meal_deals {


    private String mealdeal_order;
    private Float mealdeal_price;

    public void setMealdeal_order(String mealdeal_order) {
        this.mealdeal_order = mealdeal_order;
    }

    public String getMealdeal_order() {
        return mealdeal_order;
    }

    public void setMealdeal_price(Float mealdeal_price) {
        this.mealdeal_price = mealdeal_price;
    }


    public Float getMealdeal_price() {
        return mealdeal_price;
    }


    public meal_deals(String mealdeal_order, Float mealdeal_price) {
        this.mealdeal_order = mealdeal_order;
        this.mealdeal_price = mealdeal_price;
    }

    public meal_deals() {

    }
}
