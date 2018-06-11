package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.Date;

public class OrderListData {
    private String orderName;
    private Date orderDate;
    private boolean isActual;
    private int noteId;

    public OrderListData(String orderName, Date orderDate, boolean isActual, int noteId) {
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.isActual = isActual;
        this.noteId = noteId;
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
}