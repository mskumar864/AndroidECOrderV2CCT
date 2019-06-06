package com.herokuapp.apmanative.androidecorderv2cct;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button ppButton=(Button)findViewById(R.id.PPButton);
        final Activity mActivity = MainActivity.this;
        Log.i("Suresh******  before  ","before onClick");
        Log.i("Suresh******  before  ","before onClick");
        ppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    Log.i("Suresh******  enter  ","onClick");
                String url = "";

                    AsyncHttpClient client = new AsyncHttpClient();
                    client.setTimeout(7000);
                    client.setConnectTimeout(7000);
                    client.setResponseTimeout(7000);
                    RequestParams params = new RequestParams();
     /*   params.setForceMultipartEntityContentType(true);
        params.setContentEncoding("application/x-www-form-urlencoded");*/
                    //
                    //params.put("firstName", ((TextView)findViewById(R.id.firstname)).getText().toString());
                    // params.put("lastName", ((TextView)findViewById(R.id.lastnametext)).getText().toString());
                    //    params.put("email", ((TextView)findViewById(R.id.emailtext)).getText().toString());
                    // Log.i("Suresh******  params  ",params.toString());
//                Log.i("Suresh******  email  ",((TextView)findViewById(R.id.emailtext)).getText().toString());
                    client.post("https://pcp-orderv2.herokuapp.com/orderv2/create", params,
                            new AsyncHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                                    String body = new String(responseBody);
                                    Log.d(" CreateOrder Response  ",body);
                                    String url="";
                                    // String url="https://www.sandbox.paypal.com/IN/merchantsignup/partner/onboardingentry?token=NWFlMWRlMTUtZTRhOC00M2E0LTkyMDMtMmFlZjk0OWY5ZTU2OEpXbUtZM3QyMzR2SHR6aWZhMDRlYU9SQ3dkRGtnWGFheXVFK3c2dzdFOD0=&context_token=710968388633626624";
                                    try {
                                        JSONObject jsonObject=new JSONObject(body);
                                        Log.d("Suresh******Order data ",jsonObject.toString());
                                        JSONArray array=jsonObject.getJSONArray("links");
                                        url=array.getJSONObject(1).get("href").toString();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                                    CustomTabsIntent customTabsIntent = builder.build();
                                    customTabsIntent.launchUrl(mActivity, Uri.parse(url));
                                }

                                @Override
                                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                                    Log.e("checkout  ",error.getMessage());
                                }
                                // Your implementation here
                            }
                    );
                    Log.d("Suresh******  exit  ","created order");


                }


        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
