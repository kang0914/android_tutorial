package com.example.a.t06_customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData {
        int image;
        String title;
        String content;
    }

    ArrayList<MyData> list = new ArrayList<MyData>();

    private void initData(){
        //android.R.drawable

        for(int i =0; i < 30; i++) {
            MyData data = new MyData();
            data.title   = "title " + i;
            data.content = "content " + i;

            switch (i%4)
            {
                case 0:
                    data.image = android.R.drawable.alert_dark_frame;
                    break;
                case 1:
                    data.image = android.R.drawable.alert_light_frame;
                    break;
                case 2:
                    data.image = android.R.drawable.arrow_down_float;
                    break;
                case 3:
                    data.image = android.R.drawable.arrow_up_float;
                    break;
            }

            list.add(data);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        ListView listView = (ListView)findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, list);
        listView.setAdapter(adapter);
    }


}
