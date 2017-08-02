package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Customers extends BaseActivity {

    ArrayList<String> customers;
    EditText etResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentLayout.setLayout("CustomerView");
        setContentView(R.layout.activity_customers);
        super.onCreateDrawer();

        etResponse = (EditText) findViewById(R.id.etResponse);
        try {
            etResponse.setText(JSONAuthToken());
        }
        catch(Exception e){ etResponse.setText("nope");}


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
        String result = "";
        try {
            inputStream = NewRESTClient.get("customer", JSONAuthToken()).getEntity().getContent();

            try {

                if(inputStream != null) {
                    result = NewRESTClient.convertInputStreamToString(inputStream);
                }
                else
                    result = "Did not work!";

            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
                e.printStackTrace();
            }
            //return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return null;
        return result;
    }

    public String JSONAuthToken(){
        String JSONFullAuthToken = LoginActivity.getTokenToPass();
        String tokenManipulation1 = JSONFullAuthToken.replace("{", "");
        String tokenManipulation2 = tokenManipulation1.replace("}", "");
        String tokenManipulation3 = tokenManipulation2.replace("\"", "");
        return tokenManipulation3.split(": ")[1];
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