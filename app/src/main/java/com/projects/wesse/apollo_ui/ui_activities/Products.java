package com.projects.wesse.apollo_ui.ui_activities;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.projects.wesse.apollo_ui.Attributes.Customer;
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
    ListView list_products;
    ArrayAdapter<String> adapter;

    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE;
    private int TOTAL_PAGES;
    int currentPage = 0;
    private int noOfBtns;
    private Button[] btns;

    private JSONObject page_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentLayout.setLayout("ProductView");
        setContentView(R.layout.activity_products);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            page_button = new JSONObject(NewRESTClient.retrieveResource("product"));
            NUM_ITEMS_PAGE = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("per_page");
            TOTAL_LIST_ITEMS = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total");
            TOTAL_PAGES = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total_pages");
        } catch (JSONException e) {e.printStackTrace();}

        allProducts = new ArrayList<Product>();

        for(int i = 1;i < TOTAL_PAGES + 1; i++)
            loadFromApi("product?page=" + Integer.toString(i));


        shownProducts = new ArrayList<Product>();
        prod_names = new ArrayList<String>();
        for (int i = 0; i < allProducts.size(); i++) {
            prod_names.add(allProducts.get(i).getSku() + " || " + allProducts.get(i).getDescription());
        }
        loadMoreData(shownProducts.size());

        list_products = (ListView) findViewById(R.id.listView1);

        Btnfooter();

        loadList(0);

        CheckBtnBackGroud(0);

        list_products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent prod_view = new Intent(view.getContext(), ProductView.class).putExtra("PROD", (Serializable) allProducts.get((int)id + (NUM_ITEMS_PAGE*currentPage)));
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

    public void loadMoreData(int length) {
        for (int i = length; i < length + 10; i++)
            if (allProducts.size() > i)
                shownProducts.add(allProducts.get(i));
    }

    private void Btnfooter()
    {
        int val = TOTAL_LIST_ITEMS%NUM_ITEMS_PAGE;
        val = val==0?0:1;
        noOfBtns=TOTAL_LIST_ITEMS/NUM_ITEMS_PAGE+val;

        LinearLayout ll = (LinearLayout)findViewById(R.id.btnLay);

        btns    =new Button[noOfBtns];

        for(int i=0;i<noOfBtns;i++)
        {
            btns[i] =   new Button(this);
            btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
            btns[i].setText(""+(i+1));

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;
            btns[j].setOnClickListener(new View.OnClickListener() {

                public void onClick(View v)
                {
                    currentPage = j;
//                    loadFromApi("customer?page=" + j + 1);
                    loadList(j);
                    CheckBtnBackGroud(j);
                }
            });
        }

    }

    private void CheckBtnBackGroud(int index)
    {
//        title.setText("Page "+(index+1)+" of "+noOfBtns);
        for(int i=0;i<noOfBtns;i++)
        {
            if(i==index)
            {
                btns[index].setBackgroundColor(Color.BLACK);
//                btns[index].setBackgroundDrawable(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_dark_normal));
                btns[i].setTextColor(getResources().getColor(android.R.color.white));
            }
            else
            {
                btns[i].setBackgroundColor(getResources().getColor(android.R.color.transparent));
                btns[i].setTextColor(getResources().getColor(android.R.color.black));
            }
        }
    }

    private void loadList(int number)
    {
        ArrayList<String> sort = new ArrayList<String>();

        int start = number * NUM_ITEMS_PAGE;
        for(int i=start;i<(start)+NUM_ITEMS_PAGE;i++)
        {
            if(i<allProducts.size())
            {
                sort.add(allProducts.get(i).getSku() + " || " + allProducts.get(i).getDescription());
            }
            else
            {
                break;
            }
        }
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sort);
        list_products.setAdapter(adapter);
    }

    private void loadFromApi(String path)
    {
        JSONObject productJSON;
        try {
            productJSON = new JSONObject(NewRESTClient.retrieveResource(path));
            JSONArray productArray = productJSON.getJSONArray("data");
            for (int i = 0; i < productArray.length(); i++) {
                Product temp = new Product(
                        productArray.getJSONObject(i).getInt("id"),
                        productArray.getJSONObject(i).getString("sku"),
                        productArray.getJSONObject(i).getString("description"),
                        productArray.getJSONObject(i).getDouble("cost_price"),
                        productArray.getJSONObject(i).getDouble("retail_price"),
                        productArray.getJSONObject(i).getDouble("recommended_selling_price")
                );

                allProducts.add(temp);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}