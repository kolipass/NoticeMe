package edu.gdgspb.androidacademy2018.noticeme;

import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import edu.gdgspb.androidacademy2018.noticeme.db.Note;

public class Utils {
    public static String getStringByDate(Date date) {

        return new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(date);
    }

    @NonNull
    public static Note createNote(String title, Long date_create, Long date_life,
                                  Long date_run, boolean canceled, Double latitude, Double longitude,
                                  boolean isWeather, int windSpeed, String moonPhase) {
        Note note = new Note();
        note.setTitle(title);
        note.setDateCreate(date_create);
        note.setDateLife(date_life);
        note.setDateRun(date_run);
        note.setCanceled(canceled);
        note.setLongitude(longitude);
        note.setLatitude(latitude);
        note.setWeather(isWeather);
        note.setWintSpeed(windSpeed);
        note.setMoonPhase(moonPhase);
        Log.d("myTag", "createNote: ");
        return note;
    }
}
