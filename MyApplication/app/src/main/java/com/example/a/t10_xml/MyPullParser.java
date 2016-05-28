package com.example.a.t10_xml;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by a on 2016-05-28.
 */
public class MyPullParser extends AsyncTask<String, Void, String> {

    TextView textView;

    public MyPullParser(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... params) {
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
