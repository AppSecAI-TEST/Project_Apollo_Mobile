package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.projects.wesse.apollo_ui.utilities.CurrentLayout;
import com.projects.wesse.apollo_ui.utilities.CustomAdapter;
import com.projects.wesse.apollo_ui.utilities.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.entity.mime.Header;

public class Customers extends AppCompatActivity {

    ArrayList<String> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentLayout.setLayout("CustomerView");
        setContentView(R.layout.activity_customers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getCustomers();


//        for(int i = 0; i < 10; i++)
//            customers.add("Customer " + (i + 1));

        CustomAdapter adapter = new CustomAdapter(customers, this);
        ListView theListView = (ListView) findViewById(R.id.listView1);
        theListView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Intent previousActivity = getIntent();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dashboard_drawer, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

    public void getCustomers()
    {
        RestClient.get("api/customer", null, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray

            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray newCustomers) throws JSONException {
                // Pull out the first event on the public timeline
                Object firstEvent = newCustomers.get(0);
                int i = newCustomers.length();
                String text = firstEvent.toString();

                // Do something with the response
                System.out.println(text);

            }

        });
    }
}