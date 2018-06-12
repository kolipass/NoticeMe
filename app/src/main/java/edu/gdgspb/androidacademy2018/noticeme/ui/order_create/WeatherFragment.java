package edu.gdgspb.androidacademy2018.noticeme.ui.order_create;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gdgspb.androidacademy2018.noticeme.R;


public class WeatherFragment extends Fragment {
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";
    private static final String ORDER_NAME = "order_name";
    private static final String IS_ACTIVE = "is_active";

    private CreateOrderFragmentCallback createOrderFragmentCallback;
    private double longitude;
    private double latitude;
    private String orderName;
    private boolean isCanceled;
    private EditText nameEt;
    private EditText longitudeEt;
    private EditText latitudeEt;
    private CheckBox stopTracking;
    private Button saveOrderBtn;
    private Spinner weatherEvent;
    private Spinner particularEvent;
    private EditText windSpeedEt;
    private TextInputLayout windSpeedContainer;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance(String name, double longitude, double latitude, boolean isActive) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putDouble(LONGITUDE, longitude);
        args.putDouble(LATITUDE, latitude);
        args.putString(ORDER_NAME, name);
        args.putBoolean(IS_ACTIVE, isActive);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        createOrderFragmentCallback = (CreateOrderFragmentCallback) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            longitude = getArguments().getDouble(LONGITUDE);
            latitude = getArguments().getDouble(LATITUDE);
            orderName = getArguments().getString(ORDER_NAME);
            isCanceled = getArguments().getBoolean(IS_ACTIVE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        nameEt = view.findViewById(R.id.editText_Name);
        longitudeEt = view.findViewById(R.id.et_longitude);
        latitudeEt = view.findViewById(R.id.et_latitude);
        stopTracking = view.findViewById(R.id.stop_tracking_checkbox);
        saveOrderBtn = view.findViewById(R.id.save_order_btn);
        weatherEvent = view.findViewById(R.id.weather_event_type_spinner);
        particularEvent = view.findViewById(R.id.weather_particular_spinner);
        windSpeedEt = view.findViewById(R.id.wind_speedEt);
        windSpeedContainer = view.findViewById(R.id.wind_speed_container);

        populateViews();
        return view;
    }

    private void populateViews() {
        if (orderName != null) {
            nameEt.setText(orderName);
            stopTracking.setChecked(isCanceled);
        }
        longitudeEt.setText(String.valueOf(longitude));
        latitudeEt.setText(String.valueOf(latitude));
        saveOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(nameEt.getText())) {
                    Toast.makeText(getActivity(), "Please input order name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (weatherEvent.getSelectedItem().toString().equals("Wind speed") && TextUtils.isEmpty(windSpeedEt.getText())) {
                    Toast.makeText(getActivity(), "Please input wind speed", Toast.LENGTH_SHORT).show();
                    return;
                }
                int windSpeed;
                String moonPhase;
                if (!TextUtils.isEmpty(windSpeedEt.getText())) {
                    windSpeed = Integer.parseInt(windSpeedEt.getText().toString());
                    moonPhase = null;
                } else {
                    windSpeed = 0;
                    moonPhase = particularEvent.getSelectedItem().toString();
                }


                createOrderFragmentCallback.onSaveButtonClick(
                        nameEt.getText().toString(),
                        stopTracking.isChecked(),
                        Double.parseDouble(latitudeEt.getText().toString()),
                        Double.parseDouble(longitudeEt.getText().toString()),
                        true,
                        windSpeed,
                        moonPhase);
            }
        });

        weatherEvent.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if (selectedItem.equals("Wind speed")) {
                    showWind(true);
                } else {
                    showWind(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showWind(boolean b) {
        if (b) {
            windSpeedContainer.setVisibility(View.VISIBLE);
            particularEvent.setVisibility(View.INVISIBLE);
        } else {
            windSpeedContainer.setVisibility(View.INVISIBLE);
            particularEvent.setVisibility(View.VISIBLE);
        }
    }

}
