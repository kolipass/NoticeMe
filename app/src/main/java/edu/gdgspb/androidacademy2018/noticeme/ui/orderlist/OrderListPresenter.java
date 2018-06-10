package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.OrdersRepository;

public class OrderListPresenter implements OrderFetchListener {

    private OrdersRepository repository;
    private OrderListActivityCallback view;


    public OrderListPresenter(OrdersRepository repository, OrderListActivityCallback view) {
        this.repository = repository;
        this.view = view;
    }

    public void getData() {
        repository.getOrders(this);
    }

    @Override
    public void ordersDownloaded(List<OrderListData> orders) {
        if (view != null) {
            view.showOrders(orders);
        }
    }
}
