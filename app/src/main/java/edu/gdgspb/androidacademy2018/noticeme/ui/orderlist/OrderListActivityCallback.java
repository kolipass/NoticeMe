package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.List;

interface OrderListActivityCallback {
    void showOrders(List<OrderListData> orders);
    void showSnackBar(String string);
    void showDeletedSnackBar();
}
