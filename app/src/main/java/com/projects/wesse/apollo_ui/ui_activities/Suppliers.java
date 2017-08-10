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
import com.projects.wesse.apollo_ui.Attributes.Supplier;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Suppliers extends BaseActivity {

    ArrayList<Supplier> allSuppliers, shownSuppliers;
    private ArrayList<String> supp_names;
    ListView list_supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);
        CurrentLayout.setLayout("SuppliersView");
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject customerJSON;
        try {
            customerJSON = new JSONObject(NewRESTClient.retrieveResource("supplier"));
            JSONArray customerArray = customerJSON.getJSONArray("data");
            allSuppliers = new ArrayList<Supplier>();
            for(int i = 0; i < customerArray.length(); i++){
                Supplier temp = new Supplier(
                        customerArray.getJSONObject(i).getInt("id"),
                        customerArray.getJSONObject(i).getString("name"),
                        customerArray.getJSONObject(i).getString("email"),
                        customerArray.getJSONObject(i).getString("telephone"),
                        customerArray.getJSONObject(i).getString("address"),
                        customerArray.getJSONObject(i).getString("address_2"),
                        customerArray.getJSONObject(i).getString("city"),
                        customerArray.getJSONObject(i).getString("province"),
                        customerArray.getJSONObject(i).getString("country")
                );
                allSuppliers.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}

        shownSuppliers = new ArrayList<Supplier>();
        supp_names = new ArrayList<String>();
        for(int i = 0; i < allSuppliers.size(); i++)
        {
            supp_names.add(allSuppliers.get(i).getName());
        }
        loadMoreData(shownSuppliers.size());

        //CustomAdapter adapter = new CustomAdapter(allProducts, this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, supp_names);
        list_supplier = (ListView) findViewById(R.id.listView1);

        Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
        list_supplier.addFooterView(btnLoadMore);

        list_supplier.setAdapter(adapter);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                loadMoreData(shownSuppliers.size());
                adapter.notifyDataSetChanged();
            }
        });

        list_supplier.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent supplier_view = new Intent(view.getContext(), SupplierView.class).putExtra("SUPPLIER", (Serializable) allSuppliers.get((int) id));
                startActivity(supplier_view);
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent supp_add = new Intent(view.getContext(), SupplierAdd.class);
                startActivity(supp_add);
            }
        });

        Intent previousActivity = getIntent();
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allSuppliers.size() > i)
                shownSuppliers.add(allSuppliers.get(i));
    }
}