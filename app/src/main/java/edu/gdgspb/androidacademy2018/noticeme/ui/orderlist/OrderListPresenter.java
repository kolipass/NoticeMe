package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.OrdersRepository;
import edu.gdgspb.androidacademy2018.noticeme.Utils;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;

public class OrderListPresenter implements OrderInteractListener {

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

    @Override
    public void noteDeleted() {
        if (view != null) {
            view.showDeletedSnackBar();
        }
    }
        public void delete (int noteId) {
            Note note = new Note();
            note.setId(noteId);
            repository.delete(note);
        }
}
