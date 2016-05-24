package com.example.a.t09_asynctask;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    class MyTask extends AsyncTask<Integer, Integer, String>
    {
        ProgressDialog dlg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dlg = new ProgressDialog(MainActivity.this);
            dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dlg.setTitle("Loading...");
            dlg.setButton(DialogInterface.BUTTON_POSITIVE, "CANCEL", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dlg.dismiss();
                }
            });

            dlg.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
            dlg.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int val = values[0];

            textView.setText("count : " + val);
            dlg.setProgress(val);
        }

        @Override
        protected String doInBackground(Integer... params) {
            int value= params[0];
            int i = 0;
            while(i<value) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d("AsyncTask", "value : " + i);
                i++;
                publishProgress(i);
            }

            return "Done : doInBackground";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        MyTask task = new MyTask();
        task.execute(100);
    }
}
