package com.herokuapp.apmanative.androidecorderv2cct;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class OrderConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        // ATTENTION: This was auto-generated to handle app links.
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();

   List<String> params = getIntent().getData().getPathSegments();
        Log.d("Suresh", params.toString());
        for (String param : params) {
            Log.d("Suresh", param);
        }

      Log.d("suresh",appLinkData.getQueryParameter("token"));
      /*  Log.d("suresh",appLinkData.getQueryParameter("PayerID"));*/
        TextView textView=(TextView)findViewById(R.id.OrderId);
        textView.setText(appLinkData.getQueryParameter("token"));

        TextView textView2=(TextView)findViewById(R.id.status);
       // if( appLinkData.getQueryParameter("PayerID")!=null && appLinkData.getQueryParameter("PayerID")!="")
        textView2.setText("Sucess");


      Log.d("Suresh","Your order is confirmed");
    }

}
