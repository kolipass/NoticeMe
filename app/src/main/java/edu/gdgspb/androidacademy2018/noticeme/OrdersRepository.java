package edu.gdgspb.androidacademy2018.noticeme;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderInteractListener;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderListData;

public class OrdersRepository {
    private AppDatabase db;
    private Context context;

    public OrdersRepository(AppDatabase db) {
        this.db = db;
    }

    public void getOrders(OrderInteractListener listener) {
        new InteractOrderAsyncTask(listener, db).execute();
    }

    public void delete(final Note note) {
        new Thread(){
            @Override
            public void run() {
                db.noteDao().delete(note); //удаление из бд
            }
        }.start();
    }

    static private class InteractOrderAsyncTask extends AsyncTask<Void, Void, List<Note>> {
        private OrderInteractListener listener;
        private AppDatabase db;

        public InteractOrderAsyncTask(OrderInteractListener orderInteractListener, AppDatabase db) {
            this.listener = orderInteractListener;
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
                        !note.isCanceled(),
                        note.getId(),
                        note.getWeather(),
                        note.getWintSpeed(),
                        note.getMoonPhase()
                );
                orders.add(data);
            }
            listener.ordersDownloaded(orders);
        }
    }
}
