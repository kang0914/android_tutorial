package com.example.a.p02_location;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2016-06-04.
 */
public class TestSqliteOpenHelper extends SQLiteOpenHelper {
    public TestSqliteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE t_location ( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                "lat text, lon text, insert_time text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
