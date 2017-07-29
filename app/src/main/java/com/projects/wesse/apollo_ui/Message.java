package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.loopj.android.http.JsonHttpResponseHandler;
import com.projects.wesse.apollo_ui.utilities.BaseActivity;
import com.projects.wesse.apollo_ui.utilities.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.entity.mime.Header;

/**
 * Created by Xander on 7/17/2017.
 */

public class Message extends BaseActivity
{

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    String api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        super.onCreateDrawer();

        //list of messages
        ListView lv = (ListView)findViewById(R.id.msgview);
        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        lv.setAdapter(myarrayAdapter);
        lv.setTextFilterEnabled(true);



        listItems.add("Peter");
        listItems.add("Sarah");



        myarrayAdapter.notifyDataSetChanged();

        //API


//        try {
//            URL apolloEndpoint = new URL("https://api.github.com/");
//            HttpsURLConnection myConnection =
//                    (HttpsURLConnection) apolloEndpoint.openConnection();
//            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
//            if (myConnection.getResponseCode() == 200) {
//                InputStream responseBody = myConnection.getInputStream();
//                InputStreamReader responseBodyReader =
//                        new InputStreamReader(responseBody, "UTF-8");
//                JsonReader jsonReader = new JsonReader(responseBodyReader);
//
//                jsonReader.beginObject(); // Start processing the JSON object
//                while (jsonReader.hasNext()) { // Loop through all keys
//                    String key = jsonReader.nextName(); // Fetch the next key
//                    if (key.equals("organization_url")) { // Check if desired key
//                        // Fetch the value as a String
//                        String value = jsonReader.nextString();
//
//                        // Do something with the value
//                        // ...
//
//                        break; // Break out of the loop
//                    } else {
//                        jsonReader.skipValue(); // Skip values of other keys
//                    }
//                }
//
//                jsonReader.close();
//                myConnection.disconnect();
//            } else {
//                // Error handling code goes here
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                // All your networking logic
//                // should be here
//            }
//        });


    }




}
