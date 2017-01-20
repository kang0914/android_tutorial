package com.ebookfrenzy.t26_fragmentandrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Jaewon on 2017-01-20.
 */

public class RecyclerViewFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;

    ArrayList<String> listString = new ArrayList<String>();

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.recyclerview_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerAdapter(listString);

        listString.add("A");
        listString.add("B");
        listString.add("C");

        recyclerView.setAdapter(adapter);

        return view;
    }

    public void Add(String s) {
        listString.add(s);
        adapter.notifyDataSetChanged();
    }

    public void Remove(String s) {
        listString.remove(s);
        adapter.notifyDataSetChanged();
    }
}
