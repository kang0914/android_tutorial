package com.example.a.p02_location;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

/**
 * Created by a on 2016-06-04.
 */
public class TestSqlHandler {

    TestSqliteOpenHelper helper;

    public TestSqlHandler(Context context){
        helper = new TestSqliteOpenHelper(context, "people", null, 1);
    }

    public void insert(String lat, String lon, String date){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("lat", lat);
        values.put("lon", lon);
        values.put("insert_time", date);

        db.insert("t_location", null, values);
    }

    public String selectAll() {
        String str = "";
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("t_location", null, null, null, null, null, null);
        while(c.moveToNext()){
            String lat = c.getString(c.getColumnIndex("lat"));
            String lon = c.getString(c.getColumnIndex("lon"));
            String insert_time = c.getString(c.getColumnIndex("insert_time"));

            //Log.d("T13_sqlite", "name : " + name + ",address : " + address + ",age : " + age);
            str += "lat : " + lat + ", lon : " + lon + ",time : " + insert_time + "\n";
        }
        return str;
    }
}
