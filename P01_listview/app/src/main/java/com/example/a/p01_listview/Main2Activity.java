package com.example.a.p01_listview;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String filename = intent.getStringExtra("filename");

        TextView textView = (TextView)findViewById(R.id.textViewFileName);
        textView.setText(filename);
    }
    public void onPlayClick(View view){
        mp = new MediaPlayer();

        TextView textView = (TextView)findViewById(R.id.textViewFileName);
        //String path = Environment.getExternalStorageDirectory() + "/download/Kalimba.mp3";
        String path = textView.getText().toString();

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
