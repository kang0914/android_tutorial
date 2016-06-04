package com.example.a.p02_location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LocationManager manager;
    Geocoder geocoder;

    LocationListener listener;

    TestSqlHandler dbHandler = new TestSqlHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        geocoder = new Geocoder(this);

        String str = "";
        List<String> providers = manager.getAllProviders();
        for (int i = 0; i < providers.size(); i++) {
            str += "provider : " + providers.get(i) + "state : " +
                    manager.isProviderEnabled(providers.get(i)) + "\n";
        }
        textView.setText(str);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String str = " lat : " + location.getLatitude() +
                        " lon : " + location.getLongitude() +
                        " alt : " + location.getAltitude() + "\n";
                textView.append(str);
                Log.d("location", str);

                try {
                    List<Address> list = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 10);
                    Address address = list.get(0);
                    Log.d("location", address.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

                dbHandler.insert("" + location.getLatitude(),
                                  "" + location.getLongitude(),  timeStamp);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        List<Address> list = null;
        try {
            list = geocoder.getFromLocationName("서울", 10);

            Address address = list.get(0);
            Log.d("geocoding", address.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onBtnStartClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

    }

    public void onBtnStopClick(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        manager.removeUpdates(listener);
    }

    public void onBtnViewClick(View view){
        textView.setText(dbHandler.selectAll());
    }
}
