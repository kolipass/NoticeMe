package edu.gdgspb.androidacademy2018.noticeme.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.util.Log;

@Database(entities = {Note.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes.db";

    private static AppDatabase INSTANCE;

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            String task = "CREATE TABLE `Note` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `date_create` INTEGER, `date_life` INTEGER, `date_run` INTEGER, `canceled` INTEGER NOT NULL, `longitude` REAL, `latitude` REAL, `id_work_manager` INTEGER NOT NULL)";
            String task1 = "Drop TABLE `Note`";
            database.execSQL(task1);
            database.execSQL(task);
            Log.d("myTag", "migrate: ");
        }
    };

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract NoteDao noteDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    .addMigrations(AppDatabase.MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
