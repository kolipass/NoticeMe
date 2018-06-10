package edu.gdgspb.androidacademy2018.noticeme;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CheckLocationService extends Service {
    public CheckLocationService() {
    }

    @Override
    public void onCreate() {
        Log.d("myTag", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myTag", "Служба запущена");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d("myTag", "onDestroy");
        super.onDestroy();
    }
}
