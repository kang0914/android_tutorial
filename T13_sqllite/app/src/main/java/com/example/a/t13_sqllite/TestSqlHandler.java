package com.example.a.t13_sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.util.Log;

/**
 * Created by a on 2016-05-28.
 */
public class TestSqlHandler {

    TestSqliteOpenHelper helper;

    public TestSqlHandler(Context context) {
        helper = new TestSqliteOpenHelper(context, "people", null, 1);
    }

    public void insert(String name, int age, String address){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("age", age);
        values.put("address", address);

        db.insert("student", null, values);
    }

    public void delete(String name){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete("student", "name = ?", new String[] {name});
    }

    public void update(String name, int age){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", age);
        db.update("student", values, "name = ?", new String[]{name});
    }

    public void showAll(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.query("student", null, null, null, null, null, null);
        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            String address = c.getString(c.getColumnIndex("address"));
            int age = c.getInt(c.getColumnIndex("age"));

            Log.d("T13_sqlite", "name : " + name + ",address : " + address + ",age : " + age);
        }
    }
}
