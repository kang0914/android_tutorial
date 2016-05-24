package com.example.a.t07_mediaplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onPlayClick(View view){
        mp = new MediaPlayer();
        //String path = Environment.getExternalStorageDirectory() + "/download/Kalimba.mp3";
        String path = "/mnt/storage/sdcard1/download/Kalimba.mp3";

        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onStopClick(View view){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
