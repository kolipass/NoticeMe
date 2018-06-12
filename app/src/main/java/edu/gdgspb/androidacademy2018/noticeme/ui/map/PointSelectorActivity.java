package edu.gdgspb.androidacademy2018.noticeme.ui.map;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

import edu.gdgspb.androidacademy2018.noticeme.OrderType;
import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.ui.order_create.OrderCreateActivity;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderListActivity;

public class PointSelectorActivity extends FragmentActivity implements OnMapReadyCallback {
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String RADIUS = "radius";
    private OrderType orderType;
    private GoogleMap mMap;
    public static final int REQUEST_LOCATION_PERMISSION = 1;
    public int radius = 0;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_selector);
        orderType = (OrderType) getIntent().getSerializableExtra(OrderListActivity.CHOOSEN_ORDER_TYPE);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        orderType = (OrderType) getIntent().getSerializableExtra(OrderListActivity.CHOOSEN_ORDER_TYPE);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng spb = new LatLng(59.92978, 30.32240);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(spb, 10f));
        setMapLongClick(mMap);
        enableMyLocation();

        mMap.setOnCircleClickListener(new GoogleMap.OnCircleClickListener() {
            @Override
            public void onCircleClick(Circle circle) {
                openOrderCreateActivity();
            }
        });
    }

    private void setMapLongClick(final GoogleMap map) {
        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                String snippet = String.format(Locale.getDefault(),
                        "Lat: %1$.5f, Long: %2$.5f",
                        latitude,
                        longitude);

                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(getString(R.string.dropped_pin))
                        .snippet(snippet));
                showDialog();
            }
        });
    }

    private void drawCircle(double latitude, double longitude, GoogleMap map) {
        Circle circle = map.addCircle(new CircleOptions()
                .center(new LatLng(latitude, longitude))
                .clickable(true)
                .radius(radius)
                .strokeWidth(3)
                .strokeColor(Color.RED)
                .fillColor(0x550000FF));
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        && grantResults[0]
                        == PackageManager.PERMISSION_GRANTED) {
                    enableMyLocation();
                    break;
                }
        }
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Radius");
        dialog.setContentView(R.layout.dialog);
        Button set = dialog.findViewById(R.id.set);
        Button cancel = dialog.findViewById(R.id.cancel);
        final NumberPicker numberPicker = dialog.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(50); // max value
        numberPicker.setMinValue(1);   // min value
        numberPicker.setWrapSelectorWheel(false);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberPicker.clearFocus();
                radius = numberPicker.getValue() * 1000;
                dialog.dismiss();
                drawCircle(latitude,longitude, mMap);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void openOrderCreateActivity() {
        Intent intent = new Intent(this, OrderCreateActivity.class);
        intent.putExtra(LATITUDE, latitude);
        intent.putExtra(LONGITUDE, longitude);
        intent.putExtra(RADIUS, radius);
        intent.putExtra(OrderListActivity.CHOOSEN_ORDER_TYPE, orderType);
        startActivity(intent);
    }
}
