package com.example.a.t19_location;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LocationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        String str = "";
        List<String> providers = manager.getAllProviders();
        for(int i=0; i<providers.size(); i++) {
            str += "provider : " + providers.get(i) + "state : " + manager.isProviderEnabled(providers.get(i)) + "\n";
        }
        textView.setText(str);
    }
}
