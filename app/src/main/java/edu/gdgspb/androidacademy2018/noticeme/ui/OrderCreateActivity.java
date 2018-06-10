package edu.gdgspb.androidacademy2018.noticeme.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import java.util.Calendar;
import java.util.Date;

import edu.gdgspb.androidacademy2018.noticeme.CheckLocationService;
import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.Utils;
import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;
import edu.gdgspb.androidacademy2018.noticeme.ui.map.PointSelectorActivity;
import edu.gdgspb.androidacademy2018.noticeme.ui.orderlist.OrderListActivity;

public class OrderCreateActivity extends Activity {
    private double latitude;
    private double longitude;
    private double radius;
    private EditText title;
    private EditText dateOfCreate;
    private EditText dateOfLife;
    private EditText dateOfRun;
    private EditText longitudeEt;
    private EditText latitudeEt;
    private CheckBox iscanceled;
    private Date currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Stetho.initializeWithDefaults(this);
        getDataFromIntent();
        currentTime = Calendar.getInstance().getTime();
        setUpView();
    }

    private void setUpView() {
        title = findViewById(R.id.editText_Name);
        dateOfCreate = findViewById(R.id.editText_date_create);
        String dateCreate = Utils.getStringByDate(currentTime);
        dateOfCreate.setText(dateCreate);
        dateOfLife = findViewById(R.id.editText_date_life);
        dateOfRun = findViewById(R.id.editText_date_run);
        longitudeEt = findViewById(R.id.editText_longitude);
        longitudeEt.setText(String.valueOf(longitude));
        latitudeEt = findViewById(R.id.editText_latitude);
        latitudeEt.setText(String.valueOf(latitude));

        iscanceled = findViewById(R.id.canceled);
        Stetho.initializeWithDefaults(this);
    }

    private void getDataFromIntent() {
            Intent intent = getIntent();
            latitude = intent.getExtras().getDouble(PointSelectorActivity.LATITUDE);
            longitude = intent.getExtras().getDouble(PointSelectorActivity.LONGITUDE);
            radius = intent.getExtras().getInt(PointSelectorActivity.RADIUS);
    }

    public void onClick(View view) {
        //Добавим строку в БД
        onAddNoteBtnClick();
        // выводим сообщение
        Toast.makeText(this, "Добавил элемент в базу данных", Toast.LENGTH_SHORT).show();

        //Запускаем сервис
        startService(new Intent(this, CheckLocationService.class));
    }

    private void onAddNoteBtnClick() {
        Note note = createNote(title.getText().toString(),
                currentTime.getTime(),
                Long.parseLong(dateOfLife.getText().toString()),
                Long.parseLong(dateOfRun.getText().toString()),
                iscanceled.isChecked(),
                Double.parseDouble(latitudeEt.getText().toString()),
                Double.parseDouble(longitudeEt.getText().toString()));

        AppDatabase.getInstance(this).noteDao().insert(note);
        Intent intent = new Intent(this, OrderListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @NonNull
    private Note createNote(String title, Long date_create, Long date_life,
                            Long date_run, boolean canceled, Double latitude, Double longitude) {
        Note note = new Note();
        note.setTitle(title);
        note.setDateCreate(date_create);
        note.setDateLife(date_life);
        note.setDateRun(date_run);
        note.setCanceled(canceled);
        note.setLongitude(longitude);
        note.setLatitude(latitude);
        Log.d("myTag", "createNote: ");
        return note;
    }
}
