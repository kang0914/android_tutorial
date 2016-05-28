package com.example.a.t11_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String str = "[{'name':'kim',  'tel':'111-1111', 'age':'11'}," +
                   "{'name':'park', 'tel':'222-2222', 'age':'22'}," +
                   "{'name':'kang', 'tel':'333-3333', 'age':'33'}]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            JSONArray array = new JSONArray(str);
            for(int i =0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("name");
                String tel = obj.getString("tel");
                int age = obj.getInt("age");

                Log.d("T11_json", "name : " + name + ",tel : "+ tel + ",age : " + age);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
