package com.example.a.t12_sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("setting", MODE_PRIVATE);
        String name = pref.getString("name", "");
        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences pref = getSharedPreferences("setting", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        String str = ((EditText)findViewById(R.id.editText)).getText().toString();
        editor.putString("name", str);
        editor.commit();
    }
}
