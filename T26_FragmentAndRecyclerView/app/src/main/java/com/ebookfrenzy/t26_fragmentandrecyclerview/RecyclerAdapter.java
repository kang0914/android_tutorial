package com.ebookfrenzy.t26_fragmentandrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jaewon on 2017-01-20.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView itemTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

    private ArrayList<String> listString;

    public RecyclerAdapter(ArrayList<String> listString) {
        this.listString = listString;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(listString.get(position));
    }

    @Override
    public int getItemCount() {
        return listString.size();
    }
}
