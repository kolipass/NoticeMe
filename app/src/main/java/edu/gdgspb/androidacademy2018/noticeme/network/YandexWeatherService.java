package edu.gdgspb.androidacademy2018.noticeme.network;

import edu.gdgspb.androidacademy2018.noticeme.model.Weather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YandexWeatherService {

    @GET("forecast")
    Call<Weather> getWeatherForecast(@Query("lat") double latitude, @Query("lon") double longitude);
}
