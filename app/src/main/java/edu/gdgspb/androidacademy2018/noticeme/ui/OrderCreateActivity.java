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

import edu.gdgspb.androidacademy2018.noticeme.CheckLocationService;
import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;

public class OrderCreateActivity extends Activity {
    private EditText title;
    private EditText dateOfCreate;
    private EditText dateOfLife;
    private EditText dateOfRun;
    private EditText longitude;
    private EditText latitude;
    private CheckBox iscanceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        title = findViewById(R.id.editText_Name);
        dateOfCreate = findViewById(R.id.editText_date_create);
        dateOfLife = findViewById(R.id.editText_date_life);
        dateOfRun = findViewById(R.id.editText_date_run);
        longitude = findViewById(R.id.editText_longitude);
        latitude = findViewById(R.id.editText_latitude);
        iscanceled = findViewById(R.id.canceled);
        Stetho.initializeWithDefaults(this);
    }


    public void onClick(View view) {
        //Добавим строку в БД
        onAddNoteBtnClick();
        // выводим сообщение
        Toast.makeText(this, "Добавил элемент в базу данных", Toast.LENGTH_SHORT).show();

        //Запускаем сервис
        startService(new Intent(this, CheckLocationService.class));
    }

    //******************************************************************
    private void onAddNoteBtnClick() {
        Note note = createNote(title.getText().toString(),
                Long.parseLong(dateOfCreate.getText().toString()),
                Long.parseLong(dateOfLife.getText().toString()),
                Long.parseLong(dateOfRun.getText().toString()),
                iscanceled.isChecked(),
                Double.parseDouble(latitude.getText().toString()),
                Double.parseDouble(longitude.getText().toString()));

        AppDatabase.getInstance(this).noteDao().insert(note);

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

    //******************************************************************
}
