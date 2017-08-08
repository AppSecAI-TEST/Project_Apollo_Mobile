package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.Attributes.Customer;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CustomAdapter;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Customers extends BaseActivity {

    private ArrayList<Customer> allCustomers, shownCustomers;
    private ArrayList<String> cust_names;

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
            allCustomers = new ArrayList<Customer>();
            for(int i = 0; i < customerArray.length(); i++){
                Customer temp = new Customer(
                        (Integer) new JSONObject(customerArray.getString(i)).get("id"),
                        (String) new JSONObject(customerArray.getString(i)).get("name"),
                        (String) new JSONObject(customerArray.getString(i)).get("email"),
                        (String) new JSONObject(customerArray.getString(i)).get("telephone"),
                        (String) new JSONObject(customerArray.getString(i)).get("address"),
                        (String) new JSONObject(customerArray.getString(i)).get("address_2"),
                        (String) new JSONObject(customerArray.getString(i)).get("city"),
                        (String) new JSONObject(customerArray.getString(i)).get("province"),
                        (String) new JSONObject(customerArray.getString(i)).get("country")
                );

                allCustomers.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}

        shownCustomers = new ArrayList<Customer>();
        cust_names = new ArrayList<String>();
        for(int i = 0; i < allCustomers.size(); i++)
        {
            cust_names.add(allCustomers.get(i).getName());
        }
        loadMoreData(shownCustomers.size());

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cust_names);
        ListView list_customers = (ListView) findViewById(R.id.listView1);

        Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
        list_customers.addFooterView(btnLoadMore);
        list_customers.setAdapter(adapter);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                loadMoreData(shownCustomers.size());
                adapter.notifyDataSetChanged();
            }
        });

        list_customers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent product_view = new Intent(view.getContext(), CustomerView.class).putExtra("CUST", (Serializable) allCustomers.get((int) id));
                startActivity(product_view);
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent cut_add = new Intent(view.getContext(), CustomerAdd.class);
                startActivity(cut_add);
            }
        });

        Intent previousActivity = getIntent();
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allCustomers.size() > i)
                shownCustomers.add(allCustomers.get(i));
    }

}