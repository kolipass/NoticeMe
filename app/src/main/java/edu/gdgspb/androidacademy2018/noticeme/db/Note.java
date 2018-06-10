package edu.gdgspb.androidacademy2018.noticeme.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    //дата создания заявки
    private Long date_create;
    //дата жизни. По этому параметру main сервис смотрит жив сервис заявки или нет
    private Long date_life;
    //дата когда заявка выполнилась
    private Long date_run;
    //флаг отмены пользователем
    private boolean canceled;
    //долгота
    private Long longitude;
    //широта
    private Long latitude;
    private int id_work_manager;

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

    public Long getDate_life() {
        return date_life;
    }

    public void setDate_life(Long date_life) {
        this.date_life = date_life;
    }

    public Long getDate_run() {
        return date_run;
    }

    public void setDate_run(Long date_run) {
        this.date_run = date_run;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public int getId_work_manager() {
        return id_work_manager;
    }

    public void setId_work_manager(int id_work_manager) {
        this.id_work_manager = id_work_manager;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getDate_create() {
        return date_create;
    }

    public void setDate_create(Long date_create) {
        this.date_create = date_create;
    }
}
