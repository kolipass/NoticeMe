package edu.gdgspb.androidacademy2018.noticeme.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import edu.gdgspb.androidacademy2018.noticeme.BuildConfig;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder()
                .header("X-Yandex-API-Key", BuildConfig.YANDEX_WEATHER_KEY);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}

