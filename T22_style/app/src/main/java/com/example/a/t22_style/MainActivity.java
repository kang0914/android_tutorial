package com.example.a.t22_style;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView;
    TextView workingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);

        //Button zeroButton = (Button) findViewById(R.id.zeroButton);
        //Button oneButton = (Button) findViewById(R.id.oneButton);
        //Button enterButton = (Button)findViewById(R.id.enterButton);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                String working = workingTextView.getText().toString();
                String text = btn.getText().toString();
                if(working.equals("0")){
                    workingTextView.setText(text);
                }else{
                    workingTextView.setText(working + text);
                }
            }
        };
        //zeroButton.setOnClickListener(numberListener);
        //oneButton.setOnClickListener(numberListener);

        //enterButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        String working = workingTextView.getText().toString();
        //        selectedTextView.setText(working);
        //        workingTextView.setText("0");
        //    }
        //});

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        int number = 1;
        for(int i = 2; i < tableLayout.getChildCount() - 1; i++){
            TableRow row = (TableRow) tableLayout.getChildAt(i);

            for(int k = 0; k < row.getChildCount(); k++){
                Button btn = (Button) row.getChildAt(k);
                btn.setText("" + number);
                number++;

                btn.setOnClickListener(numberListener);
            }
        }
        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() - 1);
        Button zeroButton = (Button) bottomRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberListener);

        Button enterButton = (Button) bottomRow.getChildAt(2);
        enterButton.setText("ENTER");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String working = workingTextView.getText().toString();
                selectedTextView.setText(working);
                workingTextView.setText("0");
            }
        });

        Button deleteButton = (Button) bottomRow.getChildAt(0);
        deleteButton.setText("DELETE");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });
    }
}
