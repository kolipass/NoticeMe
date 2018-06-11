package edu.gdgspb.androidacademy2018.noticeme.ui.order_create;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.Calendar;

import edu.gdgspb.androidacademy2018.noticeme.CheckLocationService;
import edu.gdgspb.androidacademy2018.noticeme.OrderType;
import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.Utils;
import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;
import edu.gdgspb.androidacademy2018.noticeme.ui.map.PointSelectorActivity;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderListActivity;

public class OrderCreateActivity extends AppCompatActivity implements CreateOrderFragmentCallback {
    private double latitude;
    private double longitude;
    private String orderName = null;
    private boolean isCanceled;
    private double radius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Stetho.initializeWithDefaults(this);

        OrderType orderType = (OrderType) getIntent().getSerializableExtra(OrderListActivity.CHOOSEN_ORDER_TYPE);
        Integer id = getIntent().getIntExtra(OrderListActivity.NOTE_ID, -1);
        if (id != -1) {
            Note note = AppDatabase.getInstance(this).noteDao().getNoteById(id);
            isCanceled = note.isCanceled();
            orderName = note.getTitle();
            longitude = note.getLongitude();
            latitude = note.getLatitude();
            if (orderType == null) {
                //TODO get type rom db
                //  orderType = note.isWeather();
                orderType = OrderType.LOCATION;
            }
        }
        getDataFromIntent();
        switch (orderType) {
            case WEATHER:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, WeatherFragment.newInstance(orderName, longitude, latitude, isCanceled)).commit();
                break;
            case LOCATION:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, LocationFragment.newInstance(orderName, longitude, latitude, isCanceled)).commit();
                break;
        }
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        latitude = intent.getExtras().getDouble(PointSelectorActivity.LATITUDE);
        longitude = intent.getExtras().getDouble(PointSelectorActivity.LONGITUDE);
        radius = intent.getExtras().getInt(PointSelectorActivity.RADIUS);
    }

    private void onAddNoteBtnClick(Note note) {
        long idTask = AppDatabase.getInstance(this).noteDao().insert(note);
        Intent myIntent = new Intent(this, CheckLocationService.class);
        myIntent.putExtra("latitude", latitude);
        myIntent.putExtra("longitude", longitude);
        myIntent.putExtra("radius", radius);
        myIntent.putExtra("idTask", idTask);
        this.startService(myIntent);

        Intent intent = new Intent(this, OrderListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onSaveButtonClick(String name, Boolean isChecked, Double longitude, double latitude,
                                  boolean isWeather, int windSpeed, String moonPhase) {
        Toast.makeText(this, "Добавил элемент в базу данных", Toast.LENGTH_SHORT).show();
        Note note = Utils.createNote(
                name,
                Calendar.getInstance().getTimeInMillis(),
                0L,
                0L,
                isChecked,
                longitude,
                latitude,
                isWeather,
                windSpeed,
                moonPhase
        );
        onAddNoteBtnClick(note);
    }
}
