package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.Date;

public class OrderListData {
    private String orderName;
    private Date orderDate;
    private boolean isActual;
    private int noteId;
    private boolean isWeather;
    private int windSpeed;
    private String moonPhase;

    public OrderListData(String orderName, Date orderDate, boolean isActual, int noteId, boolean isWeather,
                         int windSpeed, String moonPhase) {
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.isActual = isActual;
        this.noteId = noteId;
        this.isWeather = isWeather;
        this.windSpeed = windSpeed;
        this.moonPhase = moonPhase;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
    }

    public int getNoteId() {
        return noteId;
    }

    public boolean isWeather() {
        return isWeather;
    }

    public void setWeather(boolean weather) {
        isWeather = weather;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }
}