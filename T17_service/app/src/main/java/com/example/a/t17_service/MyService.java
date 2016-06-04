package com.example.a.t17_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("T17_service", "onCreate");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("T17_service", "onDestory");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("T17_service", "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
