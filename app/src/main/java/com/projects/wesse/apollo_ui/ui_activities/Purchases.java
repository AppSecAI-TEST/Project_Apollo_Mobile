package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Xander on 7/18/2017.
 */

public class Purchases extends BaseActivity {

    ArrayList<String> allPurchases, shownPurchases;
    ListView list_purchases;
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


        allPurchases = new ArrayList<String>();
        for (int i = 0; i < 27; i++)
            allPurchases.add("Purchase " + (i + 1));

        shownPurchases = new ArrayList<String>();
        loadMoreData(shownPurchases.size());


        //CustomAdapter adapter = new CustomAdapter(products, this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shownPurchases);
        list_purchases = (ListView) findViewById(R.id.lvItems);


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
//                RelativeLayout.LayoutParams mParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
//                list_purchases.setLayoutParams(mParam);
            }
        });

        list_purchases.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();

                Intent product_view = new Intent(view.getContext(), PurchasesView.class).putExtra("ID", item);
                startActivity(product_view);
            }
        });

//        ListView lvItems = (ListView) findViewById(R.id.lvItems);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allPurchases);
//        lvItems.setAdapter(adapter);
//
//        spinner = (ProgressBar)findViewById(R.id.progressBar1);
//
//        lvItems.setOnScrollListener(new EndlessScrollListener() {
//            @Override
//            public boolean onLoadMore(int page, int totalItemsCount) {
//                // Triggered only when new data needs to be appended to the list
//                // Add whatever code is needed to append new items to your AdapterView
//                Context context = getApplicationContext();
//                CharSequence text = "Before " + allPurchases.size();
//                int duration = Toast.LENGTH_SHORT;
//
//                Toast toast = Toast.makeText(context, text, duration);
//                toast.show();
//
//                loadMoreData(page);
//
//                Toast toast2 = Toast.makeText(context, "After " + allPurchases.size(), duration);
//                toast2.show();
//
//
//                // or loadNextDataFromApi(totalItemsCount);
//                return false; // ONLY if more data is actually being loaded; false otherwise.
//            }
//        });
//
//
//
//
//        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String item = ((TextView)view).getText().toString();
//
//                Intent product_view = new Intent(view.getContext(), PurchasesView.class).putExtra("ID", item);
//                startActivity(product_view);
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
