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

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Products extends BaseActivity {

    ArrayList<String> productList, shownProducts ;
    ListView list_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        CurrentLayout.setLayout("ProductView");
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject productJSON;
        try {
            productJSON = new JSONObject(NewRESTClient.retrieveResource("product"));
            JSONArray productArray = productJSON.getJSONArray("data");
            productList = new ArrayList<String>();
            for(int i = 0; i < productArray.length(); i++){
                productList.add((String) new JSONObject(productArray.getString(i)).get("name"));
            }

        } catch (JSONException e) {e.printStackTrace();}

        shownProducts = new ArrayList<String>();
        loadMoreData(shownProducts.size());

        //CustomAdapter adapter = new CustomAdapter(allProducts, this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shownProducts);
        list_products = (ListView) findViewById(R.id.listView1);

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
                String item = ((TextView)view).getText().toString();
                Intent product_view = new Intent(view.getContext(), ProductView.class).putExtra("ID", item);
                startActivity(product_view);
            }


        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO : Add new product
            }
        });
        Intent previousActivity = getIntent();
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(productList.size() > i)
                shownProducts.add(productList.get(i));
    }
}