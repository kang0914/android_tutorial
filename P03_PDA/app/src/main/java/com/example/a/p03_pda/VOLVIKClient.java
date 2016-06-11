package com.example.a.p03_pda;

import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by a on 2016-06-11.
 */
public class VOLVIKClient {
    AsyncHttpClient client = new AsyncHttpClient();

    public void getOrderItemDataSetSelect(){
        client.get("http://59.150.255.121:8080/ExecuteDataSetForStoredProcedureForJson?connectionString=Data Source=59.150.255.121;Initial Catalog=IF_VOLVIK;Persist Security Info=True;User ID=sa;Password=cit1600!;&storedProcedureName=CIT_ORDERITEM_DATASET_SELECT&parameterValues=1|1|120",
                    null,  new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.d("httpClient", "" + response);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.d("httpClient", "" + response);
                    }
                });
    }
}
