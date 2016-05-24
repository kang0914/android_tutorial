package com.example.a.t04_activity;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activityTest = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button)findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ct = getApplicationContext();
                Context ct2 = MainActivity.this;

                Log.i("T04_activity", ct == ct2 ? "TURE" : "FALSE");

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("id", "abcd");
                //startActivity(intent);
                startActivityForResult(intent, activityTest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == activityTest){
            if(resultCode == RESULT_OK){
                String res = data.getStringExtra("myRes");
                Toast.makeText(MainActivity.this, "myRes : " + res, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
