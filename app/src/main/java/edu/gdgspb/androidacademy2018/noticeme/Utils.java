package edu.gdgspb.androidacademy2018.noticeme;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String getStringByDate(Date date) {

        return new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(date);
    }
}
