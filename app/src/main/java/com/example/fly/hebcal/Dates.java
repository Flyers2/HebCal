package com.example.fly.hebcal;

/**
 * Created by Fly on 7/28/2014.
 */
public class Dates {
    String date;
    String title;
    String category;
    private Dates havdalah;
    private Dates CandleLighting;

    public Dates getHavdalah() {
        return havdalah;
    }

    public void setHavdalah(Dates havdalah) {
        this.havdalah = havdalah;
    }

    public Dates getCandleLighting() {
        return CandleLighting;
    }

    public void setCandleLighting(Dates candleLighting) {
        CandleLighting = candleLighting;
    }

    public Dates(String date, String title,String category){
        this.date=date;
        this.title=title;

        this.category=category;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
