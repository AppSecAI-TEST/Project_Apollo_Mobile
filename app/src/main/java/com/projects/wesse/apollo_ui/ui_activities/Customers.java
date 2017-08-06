package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.loopj.android.http.RequestParams;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CustomAdapter;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;
import com.projects.wesse.apollo_ui.utilities.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Customers extends BaseActivity {

    private ArrayList<String> customerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentLayout.setLayout("CustomerView");
        setContentView(R.layout.activity_customers);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject customerJSON;
        try {
            customerJSON = new JSONObject(NewRESTClient.retrieveResource("customer"));
            JSONArray customerArray = customerJSON.getJSONArray("data");
           customerList = new ArrayList<String>();
            for(int i = 0; i < customerArray.length(); i++){
                customerList.add((String) new JSONObject(customerArray.getString(i)).get("name"));
            }

        } catch (JSONException e) {e.printStackTrace();}


        CustomAdapter adapter = new CustomAdapter(customerList, this);
        ListView theListView = (ListView) findViewById(R.id.listView1);
        theListView.setAdapter(adapter);

        Intent previousActivity = getIntent();
    }

}