package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import edu.gdgspb.androidacademy2018.noticeme.OrdersRepository;
import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.ui.AboutActivity;
import edu.gdgspb.androidacademy2018.noticeme.ui.OrderCreateActivity;


public class OrderListActivity extends AppCompatActivity implements OrderListActivityCallback {
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private AppDatabase db;
    private OrderListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "notes.db").build();

        makeToolbar();
        makeFab();

        presenter = new OrderListPresenter(new OrdersRepository(db), this);
        adapter = new OrderListAdapter();
        recyclerView.setClipToPadding(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getData();
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
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                //        .setAction("Action", null).show();
                Intent intent = new Intent(OrderListActivity.this, OrderCreateActivity.class);
                startActivity(new Intent(intent));
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
                startActivity(new Intent(OrderListActivity.this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showOrders(List<OrderListData> orders) {
        adapter.update(orders);
    }
}
