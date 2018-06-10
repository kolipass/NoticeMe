package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.Date;

public class OrderListData {
    private String orderName;
    private Date orderDate;
    private Boolean isActual;

    OrderListData(String orderName, Date orderDate, Boolean isActual) {
        this.orderName = orderName;
        this.orderDate = orderDate;
        this.isActual = isActual;
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

    public Boolean isActual() {
        return isActual;
    }

    public void setActual(Boolean actual) {
        isActual = actual;
    }
}