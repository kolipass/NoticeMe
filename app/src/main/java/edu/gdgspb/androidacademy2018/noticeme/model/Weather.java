package edu.gdgspb.androidacademy2018.noticeme.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Weather {

    @SerializedName("fact")
    private Fact fact;

    public Fact getFact() {
        return fact;
    }

    @SerializedName("forecasts")
    private List<Forecast> forecasts = null;

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public class Fact {

        @SerializedName("temp")
        private int temp;

        @SerializedName("feels_like")
        private int feelsLike;

        @SerializedName("temp_water")
        private int tempWater;

        @SerializedName("icon")
        private String icon;

        @SerializedName("condition")
        private String condition;

        @SerializedName("wind_speed")
        private int windSpeed;

        @SerializedName("wind_gust")
        private double windGust;

        @SerializedName("wind_dir")
        private String windDir;

        @SerializedName("pressure_mm")
        private int pressureMm;

        @SerializedName("pressure_pa")
        private int pressurePa;

        @SerializedName("humidity")
        private int humidity;

        @SerializedName("uv_index")
        private int uvIndex;

        @SerializedName("soil_temp")
        private int soilTemp;

        @SerializedName("soil_moisture")
        private double soilMoisture;

        @SerializedName("daytime")
        private String daytime;

        @SerializedName("polar")
        private boolean polar;

        @SerializedName("season")
        private String season;

        @SerializedName("obs_time")
        private int obsTime;

        @SerializedName("source")
        private String source;

        public int getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        public int getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(int feelsLike) {
            this.feelsLike = feelsLike;
        }

        public int getTempWater() {
            return tempWater;
        }

        public void setTempWater(int tempWater) {
            this.tempWater = tempWater;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getCondition() {
            return condition;
        }

        public void setCondition(String condition) {
            this.condition = condition;
        }

        public int getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(int windSpeed) {
            this.windSpeed = windSpeed;
        }

        public double getWindGust() {
            return windGust;
        }

        public void setWindGust(double windGust) {
            this.windGust = windGust;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public int getPressureMm() {
            return pressureMm;
        }

        public void setPressureMm(int pressureMm) {
            this.pressureMm = pressureMm;
        }

        public int getPressurePa() {
            return pressurePa;
        }

        public void setPressurePa(int pressurePa) {
            this.pressurePa = pressurePa;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(int uvIndex) {
            this.uvIndex = uvIndex;
        }

        public int getSoilTemp() {
            return soilTemp;
        }

        public void setSoilTemp(int soilTemp) {
            this.soilTemp = soilTemp;
        }

        public double getSoilMoisture() {
            return soilMoisture;
        }

        public void setSoilMoisture(double soilMoisture) {
            this.soilMoisture = soilMoisture;
        }

        public String getDaytime() {
            return daytime;
        }

        public void setDaytime(String daytime) {
            this.daytime = daytime;
        }

        public boolean isPolar() {
            return polar;
        }

        public void setPolar(boolean polar) {
            this.polar = polar;
        }

        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
        }

        public int getObsTime() {
            return obsTime;
        }

        public void setObsTime(int obsTime) {
            this.obsTime = obsTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

    }

    public class Forecast {

        @SerializedName("moon_code")
        private int moonCode;
        @SerializedName("moon_text")
        private String moonText;

        public int getMoonCode() {
            return moonCode;
        }

        public void setMoonCode(int moonCode) {
            this.moonCode = moonCode;
        }

        public String getMoonText() {
            return moonText;
        }

        public void setMoonText(String moonText) {
            this.moonText = moonText;
        }

    }

}
