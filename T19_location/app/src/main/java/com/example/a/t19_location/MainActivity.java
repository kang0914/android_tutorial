package com.example.a.t19_location;

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
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    LocationManager manager;
    Geocoder geocoder;

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
            str += "provider : " + providers.get(i) + "state : " + manager.isProviderEnabled(providers.get(i)) + "\n";
        }
        textView.setText(str);

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String str = "lat : " + location.getLatitude() +
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

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);

        try {
            List<Address> list = geocoder.getFromLocationName("서울", 10);
            Address address = list.get(0);
            Log.d("geocoding", address.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
