package com.cygnusin.developers.t23_fragmenttest4;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Jaewon on 2017-01-18.
 */

public class WordsFragment extends ListFragment {
    OnWordSelectedListener mCallback;

    public interface OnWordSelectedListener {
        public void onWordSelected(int position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                ? android.R.layout.simple_list_item_activated_1
                : android.R.layout.simple_list_item_1;

        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Data.words));
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getFragmentManager().findFragmentById(R.id.definition_fragment) != null)
        {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            mCallback = (OnWordSelectedListener)context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnWordSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mCallback.onWordSelected(position);
        getListView().setItemChecked(position, true);
    }
}
