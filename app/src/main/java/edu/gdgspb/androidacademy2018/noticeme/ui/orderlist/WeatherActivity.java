package edu.gdgspb.androidacademy2018.noticeme.ui.orderlist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.model.Weather;
import edu.gdgspb.androidacademy2018.noticeme.network.ApiFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {
    private TextView windSpeed;
    private TextView windDirection;
    private TextView city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        windSpeed = findViewById(R.id.wind_speed);
        windDirection = findViewById(R.id.wind_direction);
        city = findViewById(R.id.city);

        Call<Weather> call = ApiFactory.getWeatherService().getWeatherForecast(59.974320, 30.336800);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    windSpeed.setText(String.valueOf(response.body().getFact().getWindSpeed()));
                    windDirection.setText(String.valueOf(response.body().getForecasts().get(0).getMoonText()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {

            }
        });
    }
}
