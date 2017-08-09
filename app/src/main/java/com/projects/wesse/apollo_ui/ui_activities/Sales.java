package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.projects.wesse.apollo_ui.Attributes.Customer;
import com.projects.wesse.apollo_ui.Attributes.Sale;
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

public class Sales extends BaseActivity {

    ArrayList<Sale> allSales, shownSales;
    private ArrayList<String> sale_names;
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
        setContentView(R.layout.activity_sales);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject saleJSON;
        try {
            saleJSON = new JSONObject(NewRESTClient.retrieveResource("sale"));
            JSONArray saleArray = saleJSON.getJSONArray("data");
            allSales = new ArrayList<Sale>();
            for(int i = 0; i < saleArray.length(); i++){
                Customer tempCus = new Customer(
                        (Integer) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("id"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("name"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("email"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("telephone"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("address"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("address_2"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("city"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("province"),
                        (String) new JSONObject(saleArray.getString(i)).getJSONObject("customer").getJSONObject("data").get("country")
                );
                Sale temp = new Sale(
                        (Integer) new JSONObject(saleArray.getString(i)).get("id"),
                        (String) new JSONObject(saleArray.getString(i)).get("placed_at"),
                        (Double) new JSONObject(saleArray.getString(i)).get("total"),
                        tempCus);

                allSales.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}
        String str = allSales.get(3).getId() + " || " + allSales.get(3).getCustomer().getName();
        shownSales = new ArrayList<Sale>();
        sale_names = new ArrayList<String>();
        for(int i = 0; i < allSales.size(); i++)
        {
            sale_names.add(allSales.get(i).getId() + " || " + allSales.get(i).getCustomer().getName());
        }
        loadMoreData(shownSales.size());

        shownSales = new ArrayList<Sale>();
        loadMoreData(shownSales.size());


        //CustomAdapter adapter = new CustomAdapter(allProducts, this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sale_names);
        ListView list_sales = (ListView) findViewById(R.id.listView1);

        // LoadMore button
        Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
        list_sales.addFooterView(btnLoadMore);

        // Getting adapter
        list_sales.setAdapter(adapter);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                loadMoreData(shownSales.size());
                adapter.notifyDataSetChanged();
            }
        });

        list_sales.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cust_view = new Intent(view.getContext(), SalesView.class).putExtra("PURCHASE", (Serializable) allSales.get((int) id));
                startActivity(cust_view);
            }
        });
        
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allSales.size() > i)
                shownSales.add(allSales.get(i));
    }
}

