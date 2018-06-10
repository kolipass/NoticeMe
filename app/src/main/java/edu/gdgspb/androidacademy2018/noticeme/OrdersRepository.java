package edu.gdgspb.androidacademy2018.noticeme;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderFetchListener;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderListData;

public class OrdersRepository {
    private AppDatabase db;

    public OrdersRepository(AppDatabase db) {
        this.db = db;
    }

    public void getOrders(OrderFetchListener listener) {
        new FetchOrderAsyncTask(listener, db).execute();
    }

    static private class FetchOrderAsyncTask extends AsyncTask<Void, Void, List<Note>> {
        private OrderFetchListener listener;
        private AppDatabase db;

        public FetchOrderAsyncTask(OrderFetchListener orderFetchListener, AppDatabase db) {
            this.listener = orderFetchListener;
            this.db = db;
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            return db.noteDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            super.onPostExecute(notes);
            List<OrderListData> orders = new ArrayList<>();
            for (Note note : notes) {
                OrderListData data = new OrderListData(
                        note.getTitle(),
                        new Date(note.getDateCreate()),
                        !note.isCanceled()
                );
                orders.add(data);
            }

            listener.ordersDownloaded(orders);
        }
    }
}
