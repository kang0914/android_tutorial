package com.example.a.p03_pda;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    VOLVIKClient client = new VOLVIKClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private  static final int WHSActivity = 100;
    public void onBtnOrderSelectClick(View v){
        client.setOnResponseListener(new VOLVIKClient.OnResponseListener() {
            @Override
            public void onResponseComplete(JSONObject response) {
                //Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject data = response.getJSONObject("DATA");
                    JSONArray array = data.getJSONArray("Table");

                    if(array.length()==0){
                        Date today = new Date();
                        new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String strDate = String.format("%04d%02d%02d", year, monthOfYear + 1, dayOfMonth);
                                client.getOrderSelect(strDate);
                            }
                        }, today.getYear() + 1900, today.getMonth(), today.getDate() ).show();
                    }else{
                        //Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, WHSctivity.class);
                        intent.putExtra("OrderList", ""+response);
                        startActivityForResult(intent, WHSActivity);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 20160611
        String strDate = sdf.format(today);

        client.getOrderSelect(strDate);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == WHSActivity){
            String whsOrderNumber = data.getStringExtra("orderNumber");
            client.setOnResponseListener(new VOLVIKClient.OnResponseListener() {
                @Override
                public void onResponseComplete(JSONObject response) {
                    Log.d("onResponseComplete2", ""+response);
                }
            });
            client.getOrderItemDataSetSelect("" + whsOrderNumber);
        }else if (requestCode == 0){
            if(resultCode == RESULT_OK){
                String contents = data.getStringExtra("SCAN_RESULT");
                Toast.makeText(MainActivity.this, contents, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onBtnScanClick(View v){
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "ALL");
        startActivityForResult(intent, 0);
    }
}
