package edu.gdgspb.androidacademy2018.noticeme.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import edu.gdgspb.androidacademy2018.noticeme.R;
import edu.gdgspb.androidacademy2018.noticeme.db.AppDatabase;
import edu.gdgspb.androidacademy2018.noticeme.db.Note;

public class OrderCreateActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Stetho.initializeWithDefaults(this);
    }


    public void onClick(View view) {
        //Добавим строку в БД
        onAddNoteBtnClick();
        // выводим сообщение
        Toast.makeText(this, "Добавил элемент в базу данных", Toast.LENGTH_SHORT).show();
    }

    //******************************************************************
    private void onAddNoteBtnClick() {
        Note note = createNote("Первая заявка", (long) 1528634836, (long) 1528634936, (long) 1528634936, false,
                59.974320, 30.336800);
        AppDatabase.getInstance(this).noteDao().insert(note);
    }

    @NonNull
    private Note createNote(String title, Long date_create, Long date_life,
                            Long date_run, boolean canceled, Double longitude, Double latitude) {
        Note note = new Note();
        EditText mytitle = findViewById(R.id.editText_Name);
        EditText editText_date_create = findViewById(R.id.editText_date_create);
        EditText editText_date_life = findViewById(R.id.editText_date_life);
        EditText editText_date_run = findViewById(R.id.editText_date_run);
        EditText editText_longitude = findViewById(R.id.editText_longitude);
        EditText editText_latitude = findViewById(R.id.editText_latitude);
        CheckBox iscanceled = findViewById(R.id.canceled);

        note.setTitle(mytitle.getText().toString());
        note.setDate_create(Long.getLong(editText_date_create.getText().toString()));
        note.setDate_life(Long.getLong(editText_date_life.getText().toString()));
        note.setDate_run(Long.getLong(editText_date_run.getText().toString()));
        note.setCanceled(iscanceled.isChecked());
        note.setLatitude(Long.getLong(editText_latitude.getText().toString()));
        note.setLongitude(Long.getLong(editText_longitude.getText().toString()));

        Log.d("myTag", "createNote: ");
        return note;
    }

    //******************************************************************
}
