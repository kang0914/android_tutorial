package com.ebookfrenzy.t26_fragmentandrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editTextAdd;
    Button buttonAdd;
    Button buttonDelete;

    RecyclerViewFragment recyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewFragment = (RecyclerViewFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentRecyclerView);

        editTextAdd = (EditText) findViewById(R.id.edittextAdd);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewFragment.Add(editTextAdd.getText().toString());
            }
        });

        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerViewFragment.Remove(editTextAdd.getText().toString());
            }
        });
    }
}
