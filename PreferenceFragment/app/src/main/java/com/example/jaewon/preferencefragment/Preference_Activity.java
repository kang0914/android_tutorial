package com.example.jaewon.preferencefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Preference_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        // 툴바의 '<-' 버튼 만들기
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //this.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close);
    }

    // 툴바의 '<-' 버튼 이벤트 처리
    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return false;
    }
}
