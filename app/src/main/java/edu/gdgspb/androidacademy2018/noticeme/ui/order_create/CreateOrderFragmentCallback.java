package edu.gdgspb.androidacademy2018.noticeme.ui.order_create;

interface CreateOrderFragmentCallback {
    void onSaveButtonClick(String name, Boolean isChecked, Double longitude, double latitude, boolean isWeather, int windSpeed, String moon_phase);
}
