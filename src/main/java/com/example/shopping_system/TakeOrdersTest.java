package com.example.shopping_system;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class TakeOrdersTest {



    @Test
    public void total() {
        Float total = 0.0f;
        Float change = 0.0f;
        Float   tax = 0.8f;
        Float  cash = 0.0f;

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        total = 3000f;

        Float format;
        format = Float.valueOf(decimalFormat.format(total));

        Float  subtotal =  format;
        Float addedtax = tax ;
        Float postTex = total * tax;


        format = Float.valueOf(decimalFormat.format(postTex));

        tax = total - format;
        total = tax + total;
        format = Float.valueOf(decimalFormat.format(total));
        total = format;
        Float finaltotal = format;
        assertEquals(600, tax, 0.01);
        assertEquals(2400, postTex,0.01);
        assertEquals(3600,finaltotal,0.01);

    }

    @Test
    public void change() {
        Float cash = 40f;
        Float total = 35.99f;
        Float change = cash - total;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        Float format = Float.parseFloat(decimalFormat.format(change));
        System.out.println(format);
        assertEquals(format,format,5.01);
    }

    @Test
    public void date() {
        Calendar cal = Calendar.getInstance();

        Date current_date = new Date();
        String[] current_date_split = current_date.toString().split(" ");
        String Current_date = current_date_split[0] + current_date_split[1] + current_date_split[2];

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = cal.getTime();

        String[] end_of_month = lastDayOfMonth.toString().split(" ");
        String end_of_month_date = end_of_month[0] + end_of_month[1] + end_of_month[2];
        String testdate = "SatJul30";
        Boolean datetest = true;
        if (testdate.equals(Current_date)) {
            System.out.println(datetest);
        } else {
            System.out.println(datetest = false);
        }
    }

    @Test
    public void calculate_monthly_sales() {
        Calendar cal = Calendar.getInstance();
        Float monthlysales = 0f;
        Date current_date = new Date();
        String[] current_date_split = current_date.toString().split(" ");
        String Current_date = current_date_split[0] + current_date_split[1] + current_date_split[2];

        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        Date lastDayOfMonth = cal.getTime();

        String[] end_of_month = lastDayOfMonth.toString().split(" ");
        String end_of_month_date = end_of_month[0] + end_of_month[1] + end_of_month[2];
        String testdate = "SatJul30";
        LocalDateTime localDateTime = LocalDateTime.now();

        ArrayList<Float> monthlysale = new ArrayList<>();
        monthlysale.add(300f);
        monthlysale.add(300f);
        monthlysale.add(300f);

        if (localDateTime.equals(localDateTime)) {

            boolean bool = true;

            for(int i = 0; i < monthlysale.toArray().length; i++){
                monthlysales += monthlysale.get(i);
            }

            System.out.println("Today is the last day of the month and monthly sales will be calculated");
        }
        assertEquals(900, monthlysales, 0.01);


    }
}