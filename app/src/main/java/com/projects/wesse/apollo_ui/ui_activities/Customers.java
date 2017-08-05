package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.loopj.android.http.RequestParams;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;
import com.projects.wesse.apollo_ui.utilities.RestClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Customers extends BaseActivity {

    private ArrayList<String> customers;
    private EditText etResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentLayout.setLayout("CustomerView");
        setContentView(R.layout.activity_customers);
        super.onCreateDrawer();

        etResponse = (EditText) findViewById(R.id.etResponse);
        etResponse.setText(retrieveCustomers());



        /*getCustomers();
         for(int i = 0; i < 10; i++)
             customers.add("Customer " + (i + 1));

        CustomAdapter adapter = new CustomAdapter(customers, this);
        ListView theListView = (ListView) findViewById(R.id.listView1);
        theListView.setAdapter(adapter);*/

        Intent previousActivity = getIntent();
    }

   public String retrieveCustomers() {
        InputStream inputStream = null;
        String result = "init";
        BufferedReader br;
        StringBuilder sb;

       RestClient.get("api/customer", new RequestParams("Authorization","Bearer " + LoginActivity.getUser().getJSONToken()));
       RestClient.getTimeline();
       /* try {
            inputStream = NewRESTClient.get("customer", LoginActivity.getUser().getJSONToken()).getEntity().getContent();
            try {
                if(inputStream != null) {
                    sb = new StringBuilder();
                    String line;
                    br = new BufferedReader(new InputStreamReader(inputStream));
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    result = sb.toString();
                }
                else
                    result = "Could not retrieve customers";
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return result;
    }

    /*public void getCustomers()
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
    }*/

}