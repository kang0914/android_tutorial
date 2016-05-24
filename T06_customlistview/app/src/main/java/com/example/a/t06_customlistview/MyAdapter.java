package com.example.a.t06_customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by a on 2016-05-21.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<MainActivity.MyData> list = new ArrayList<MainActivity.MyData>();

    public MyAdapter(Context context, ArrayList<MainActivity.MyData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_view, null);
        }

        MainActivity.MyData data = list.get(position);
        TextView textTitle = (TextView) convertView.findViewById(R.id.textTitle);
        TextView textContent = (TextView) convertView.findViewById(R.id.textContent);
        textTitle.setText(data.title);
        textContent.setText(data.content);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        imageView.setImageResource(data.image);

        return convertView;
    }
}
