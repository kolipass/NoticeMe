package edu.gdgspb.androidacademy2018.noticeme.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    /**
     * дата создания заявки пользователем
     */
    @ColumnInfo(name = "date_create")
    private Long dateCreate;
    /**
     * дата последнего просмотра заявки second сервисом. По этому параметру main сервис смотрит жив second сервис или нет
     */
    @ColumnInfo(name = "date_life")
    private Long dateLife;
    /**
     * Дата выполнения заявки сервисом
     */
    //
    @ColumnInfo(name = "date_run")
    private Long dateRun;
    /**
     * флаг отмены заявки пользователем
     */
    //
    private boolean canceled;
    private Double longitude;
    private Double latitude;

    private Boolean isWeather;
    private int wintSpeed;
    private String moonPhase;

    @ColumnInfo(name = "id_work_Manager")
    private int idWorkManager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getDateLife() {
        return dateLife;
    }

    public void setDateLife(Long dateLife) {
        this.dateLife = dateLife;
    }

    public Long getDateRun() {
        return dateRun;
    }

    public void setDateRun(Long dateRun) {
        this.dateRun = dateRun;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public int getIdWorkManager() {
        return idWorkManager;
    }

    public void setIdWorkManager(int idWorkManager) {
        this.idWorkManager = idWorkManager;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Long getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public boolean equals(Object obj) {
        final Note note = (Note) obj;
        return this.id == note.id;
    }

    public Boolean getWeather() {
        return isWeather;
    }

    public int getWintSpeed() {
        return wintSpeed;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setWeather(Boolean weather) {
        isWeather = weather;
    }

    public void setWintSpeed(int wintSpeed) {
        this.wintSpeed = wintSpeed;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }
}
