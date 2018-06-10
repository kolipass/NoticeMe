package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.R;


public class OrderListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        makeToolbar();
        makeFab();

        adapter = new OrderListAdapter();
        recyclerView.setClipToPadding(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.update(getData());
    }

    private List<OrderListData> getData() {
        List<OrderListData> orders = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Boolean isActual = i % 2 == 0;
            String orderName = "Заявка " + i;
            OrderListData order = new OrderListData(
                    orderName,
                    new Date(),
                    isActual
            );
            orders.add(order);
        }
        return orders;
    }

    private void makeFab() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 || dy < 0 && fab.isShown())
                    fab.hide();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    fab.show();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
    }

    private void makeToolbar() {
        toolbar.setTitle(R.string.orders);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white));
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_order_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
