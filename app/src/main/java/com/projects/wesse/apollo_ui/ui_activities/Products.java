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

import com.projects.wesse.apollo_ui.Attributes.Product;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Products extends BaseActivity {

    private ArrayList<Product> allProducts, shownProducts;
    private ArrayList<String> prod_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentLayout.setLayout("ProductView");
        setContentView(R.layout.activity_products);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject productJSON;
        try {
            productJSON = new JSONObject(NewRESTClient.retrieveResource("product"));
            JSONArray productArray = productJSON.getJSONArray("data");
            allProducts = new ArrayList<Product>();
            for(int i = 0; i < productArray.length(); i++){
                Product temp = new Product(
                        (Integer) new JSONObject(productArray.getString(i)).get("id"),
                        (String) new JSONObject(productArray.getString(i)).get("sku"),
                        (String) new JSONObject(productArray.getString(i)).get("description"),
                        (Double) new JSONObject(productArray.getString(i)).get("cost_price"),
                        (Double) new JSONObject(productArray.getString(i)).get("retail_price"),
                        (Double) new JSONObject(productArray.getString(i)).get("recommended_selling_price")
                );

                allProducts.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}
        String str = allProducts.get(4).getSku();
        shownProducts = new ArrayList<Product>();
        prod_names = new ArrayList<String>();
        for(int i = 0; i < allProducts.size(); i++)
        {
            prod_names.add(allProducts.get(i).getSku() + " || " + allProducts.get(i).getDescription());
        }
        loadMoreData(shownProducts.size());

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, prod_names);
        ListView list_products = (ListView) findViewById(R.id.listView1);

        Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
        list_products.addFooterView(btnLoadMore);
        list_products.setAdapter(adapter);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                loadMoreData(shownProducts.size());
                adapter.notifyDataSetChanged();
            }
        });

        list_products.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent prod_view = new Intent(view.getContext(), ProductView.class).putExtra("PROD", (Serializable) allProducts.get((int) id));
                startActivity(prod_view);
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent cut_add = new Intent(view.getContext(), ProductAdd.class);
                startActivity(cut_add);
            }
        });

        Intent previousActivity = getIntent();
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allProducts.size() > i)
                shownProducts.add(allProducts.get(i));
    }

}