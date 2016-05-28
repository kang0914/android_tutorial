package com.example.a.t13_sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

/**
 * Created by a on 2016-05-28.
 */
public class TestSqliteOpenHelper extends SQLiteOpenHelper {
    public TestSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 최초 설치시에만 호출
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE student ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "name TEXT, age INTEGER, address TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS student";
        db.execSQL(sql);

        onCreate(db);
    }
}
