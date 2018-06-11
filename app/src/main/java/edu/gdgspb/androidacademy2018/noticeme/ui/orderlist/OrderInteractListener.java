package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.List;

public interface OrderInteractListener {
    void ordersDownloaded(List<OrderListData> orders);
    void noteDeleted();
}

