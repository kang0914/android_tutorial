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

    public interface OnResponseListener {
        public void onResponseComplete(JSONObject response);
    }
    public interface  OnResponseOrderItemListener{
        public void OnResponseOrderItemComplete(JSONObject response);
    }

    OnResponseListener mListener;
    public void setOnResponseListener(OnResponseListener listener){
        mListener = listener;
    }

    OnResponseOrderItemListener mOrderItemListener;
    public void setOnResponseOrderItemListener(OnResponseOrderItemListener listener){
        mOrderItemListener = listener;
    }

    public void getOrderSelect(String workDay){
        String url = Constans.URL_ORDER_SELECT + workDay;
        client.get(url,
                null, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        if(mListener != null)
                            mListener.onResponseComplete(response);

                        Log.d("VOLVIK", "" + response);
                    }
                });
    }

    public void getOrderItemDataSetSelect(String orderNum){
        String url = Constans.URL_ORDER_DATASET_SELECT + orderNum + "|120";
        client.get(url,
                    null,  new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);

                        if(mOrderItemListener != null)
                            mOrderItemListener.OnResponseOrderItemComplete(response);

                        Log.d("OrderItemDataSetSelect", "" + response);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                        super.onSuccess(statusCode, headers, response);
                        Log.d("OrderItemDataSetSelect", "" + response);
                    }
                });
    }
}
