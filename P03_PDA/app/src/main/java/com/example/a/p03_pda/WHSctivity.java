package com.example.a.p03_pda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class WHSctivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whsctivity);

        ArrayList<String> list = new ArrayList<String>();

        Intent intent = getIntent();
        String strData = intent.getStringExtra("OrderList");
        try {
            JSONObject response = new JSONObject(strData);
            JSONObject data = response.getJSONObject("DATA");
            JSONArray array = data.getJSONArray("Table");

            for(int i = 0; i < array.length(); i++){
               JSONObject obj = (JSONObject) array.get(i);
                String str = obj.getString("ORDNUM")+
                             obj.getString("WHSIN");
                list.add(str);

                Log.d("WHSActivity", str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView = (ListView) findViewById(R.id.WHSlistView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String number = textView.getText().toString().substring(0, 1);
                Intent intent1 = new Intent();
                intent1.putExtra("orderNumber", number);
                setResult(RESULT_OK, intent1);
                finish();
            }
        });
    }
}
