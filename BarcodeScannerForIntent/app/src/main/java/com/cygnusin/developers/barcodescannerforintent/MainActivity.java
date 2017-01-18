package com.cygnusin.developers.barcodescannerforintent;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PRODUCT_CODE_TYPES = "UPC_A, UPC_E, EAN_8, EAN_13";
    public static final String ONE_D_CODE_TYPES = PRODUCT_CODE_TYPES + ",CODE_39, CODE_93, CODE_128";
    public static final String QR_CODE_TYPES = "QR_CODE";
    public static final String ALL_CODE_TYPES = null;

    private static final int REQUEST_CODE_SCAN = 100;

    private EditText contentsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnScan = (Button) findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan(ALL_CODE_TYPES);
            }
        });

        contentsText = (EditText) findViewById(R.id.editText);
    }

    private void scan(String formats) {
        Intent intentScan = new Intent("com.google.zxing.client.android.SCAN");
        intentScan.addCategory(Intent.CATEGORY_DEFAULT);

        if(formats != null) {
            intentScan.putExtra("SCAN_FORMATS", formats);
        }

        try{
            startActivityForResult(intentScan, REQUEST_CODE_SCAN);
        } catch (ActivityNotFoundException e) {
            showDownloadDialog();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == REQUEST_CODE_SCAN) {
            Toast toast = Toast.makeText(getBaseContext(), "onActivityResult called with code : " + resultCode, Toast.LENGTH_LONG);
            toast.show();

            if(resultCode == Activity.RESULT_OK) {
                String contents = intent.getStringExtra("SCAN_RESULT");
                String formatName = intent.getStringExtra("SCAN_RESULT_FORMAT");

                contentsText.append("\nSCAN RESULT FORMAT : " + formatName);
                contentsText.append("\nSCAN RESULT : " + contents);
            }
            else {
                contentsText.append("\nSCAN FAILED");

            }
        }
    }

    protected void showDownloadDialog()
    {
        AlertDialog.Builder builder = null;
        builder = new AlertDialog.Builder(this);
        builder.setTitle("바코드 스캐너 앱 설치");
        builder.setMessage("바코드 스캐너 앱이 필요합니다. 자동 설치할까요?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://details?id=com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.show();
    }
}
