package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import java.util.List;
import edu.gdgspb.androidacademy2018.noticeme.OrderType;
import edu.gdgspb.androidacademy2018.noticeme.OrdersRepository;
import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.ui.AboutActivity;
import edu.gdgspb.androidacademy2018.noticeme.ui.OrderCreateActivity;
import edu.gdgspb.androidacademy2018.noticeme.ui.map.PointSelectorActivity;


public class OrderListActivity extends AppCompatActivity implements OrderListActivityCallback, OrderListAdapter.AdapterItemClickListener {
    public static final String CHOOSEN_ORDER_TYPE = "choosen_order_type";
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private Toolbar toolbar;
    private FloatingActionsMenu menuMultipleActions;
    private FloatingActionButton weatherFab, locationFab;
    private AppDatabase db;
    private OrderListPresenter presenter;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        recyclerView = findViewById(R.id.recycler_view);
        toolbar = findViewById(R.id.toolbar);
        menuMultipleActions = findViewById(R.id.multiple_actions);
        weatherFab = findViewById(R.id.action_weather);
        locationFab = findViewById(R.id.action_location);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "notes.db").build();
        makeToolbar();
        makeFab();
        presenter = new OrderListPresenter(new OrdersRepository(db), this);
        adapter = new OrderListAdapter(this);
        recyclerView.setClipToPadding(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                presenter.delete(adapter.getOrderItem(viewHolder.getAdapterPosition()).getNoteId());
                adapter.removeAt(viewHolder.getAdapterPosition());
                showDeletedSnackBar();
                presenter.getData();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // view the background view
            }
        };
        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
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
                if (dy > 0 || dy < 0 && menuMultipleActions.isShown()) {
                    menuMultipleActions.setVisibility(View.GONE);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    menuMultipleActions.setVisibility(View.VISIBLE);
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        final Intent intent = new Intent(OrderListActivity.this, PointSelectorActivity.class);
        locationFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(CHOOSEN_ORDER_TYPE, OrderType.LOCATION);
                startActivity(new Intent(intent));
            }
        });
        weatherFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra(CHOOSEN_ORDER_TYPE, OrderType.WEATHER);
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

    @Override
    public void showDeletedSnackBar() {
        String message = "Deleted note";
        showSnackBar(message);
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }

    public void openOrderActivity() {
        Intent intent = getIntent();
        latitude = intent.getExtras().getDouble(PointSelectorActivity.LATITUDE);
        longitude = intent.getExtras().getDouble(PointSelectorActivity.LONGITUDE);
        Intent newIntent = new Intent(this, OrderCreateActivity.class);
        newIntent.putExtra(PointSelectorActivity.LATITUDE, latitude);
        newIntent.putExtra(PointSelectorActivity.LONGITUDE, longitude);
        startActivity(newIntent);
    }

    @Override
    public void onViewHolderClick(int idNote) {
        openOrderActivity();
    }
}
