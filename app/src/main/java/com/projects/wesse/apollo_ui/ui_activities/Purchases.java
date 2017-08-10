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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.Attributes.Purchase;
import com.projects.wesse.apollo_ui.Attributes.Supplier;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Xander on 7/18/2017.
 */

public class Purchases extends BaseActivity {

    ArrayList<Purchase> allPurchases, shownPurchases;
    private ArrayList<String> purchase_names;
    ProgressBar spinner;

    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 0;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    private int TOTAL_PAGES = 3;
    // indicates the current page which Pagination is fetching.
    private int currentPage = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject purchaseJSON;
        try {
            purchaseJSON = new JSONObject(NewRESTClient.retrieveResource("purchase"));
            JSONArray purchaseArray = purchaseJSON.getJSONArray("data");
            allPurchases = new ArrayList<Purchase>();
            for(int i = 0; i < purchaseArray.length(); i++){
                Supplier tempSup = new Supplier(
                        (Integer) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("id"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("name"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("email"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("telephone"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("address"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("address_2"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("city"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("province"),
                        (String) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("country"),1
//                        (Integer) new JSONObject(purchaseArray.getString(i)).getJSONObject("supplier").getJSONObject("data").get("lead_time")
                );
                Purchase temp = new Purchase(
                        (Integer) new JSONObject(purchaseArray.getString(i)).get("id"),
                        (String) new JSONObject(purchaseArray.getString(i)).get("placed_at"),
                        (String) new JSONObject(purchaseArray.getString(i)).get("processed_at"),
                        (Double) new JSONObject(purchaseArray.getString(i)).get("total"),
                        tempSup);

                allPurchases.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}
    String str = allPurchases.get(3).getId() + " || " + allPurchases.get(3).getSupplier().getName();
        shownPurchases = new ArrayList<Purchase>();
        purchase_names = new ArrayList<String>();
        for(int i = 0; i < allPurchases.size(); i++)
        {
            purchase_names.add(allPurchases.get(i).getId() + " || " + allPurchases.get(i).getSupplier().getName());
        }
        loadMoreData(shownPurchases.size());

        shownPurchases = new ArrayList<Purchase>();
        loadMoreData(shownPurchases.size());


        //CustomAdapter adapter = new CustomAdapter(allProducts, this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, purchase_names);
        ListView list_purchases = (ListView) findViewById(R.id.listView1);

        // LoadMore button
       Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
        list_purchases.addFooterView(btnLoadMore);

        // Getting adapter
        list_purchases.setAdapter(adapter);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                loadMoreData(shownPurchases.size());
                adapter.notifyDataSetChanged();
            }
        });

        list_purchases.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cust_view = new Intent(view.getContext(), PurchasesView.class).putExtra("PURCHASE", (Serializable) allPurchases.get((int) id));
                startActivity(cust_view);
            }
        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//                Intent pur_add = new Intent(view.getContext(), PurchasesAdd.class);
//                startActivity(pur_add);
//            }
//        });
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allPurchases.size() > i)
                shownPurchases.add(allPurchases.get(i));
    }
}
