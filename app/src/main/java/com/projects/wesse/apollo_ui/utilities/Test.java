package com.projects.wesse.apollo_ui.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.projects.wesse.apollo_ui.Attributes.Customer;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activities.LoginActivity;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class Test extends AppCompatActivity {

    private static final String BASE_URL = "https://apollo.lomejo.co.za/api/";
        EditText etResponse;
        TextView tvIsConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etResponse = (EditText) findViewById(R.id.etResponse);
        tvIsConnected = (TextView) findViewById(R.id.tvIsConnected);

        if(isConnected()){
            tvIsConnected.setBackgroundColor(0xFF00CC00);
            tvIsConnected.setText("You are conncted");
        }
        else{
            tvIsConnected.setText("You are NOT conncted");
        }
        new HttpAsyncTask().execute(BASE_URL + "product");

        Customer customer = new Customer(666, "test_name", "test_email", "test_tel", "test_address",
                "test_address2", "test_city", "test_province", "test_country");

        /*Gson gson = new Gson();
        String json = gson.toJson(customer);*/

        etResponse.setText(LoginActivity.getUser().toString() + " :::: " + customer.convertToJSONString());

        Intent previousActivity = getIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    public static JSONObject convertInputStreamToJSONObject(InputStream inputStream) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        return (JSONObject)jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
    }

    public static String parseJSONObject(String in) throws IOException, ParseException {
        try
        {
            JSONObject json = (JSONObject) new JSONTokener(in).nextValue();
            String jsonString = json.toJSONString();
            return jsonString;
        }catch(JSONException e) {return  null;}
    }


    public static String GET(String url){
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("email", "johndoe@paradox.com"));
        nvps.add(new BasicNameValuePair("password", "secret"));
        InputStream inputStream = null;
        try {
            inputStream = NewRESTClient.post(nvps, "authenticate", null).getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        try {

            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
            }
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
            e.printStackTrace();
        }
        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);

        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(parseJSONObject(result));
        }
    }
}
