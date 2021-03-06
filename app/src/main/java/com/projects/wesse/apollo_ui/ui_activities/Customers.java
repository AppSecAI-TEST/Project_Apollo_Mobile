package com.projects.wesse.apollo_ui.ui_activities;

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
import android.app.ActionBar.LayoutParams;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.wesse.apollo_ui.Attributes.Customer;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CustomAdapter;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;
import com.projects.wesse.apollo_ui.utilities.SessionUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Customers extends BaseActivity {

    private ArrayList<Customer> allCustomers, shownCustomers;
    private ArrayList<String> cust_names;
    ListView list_customers;
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
        CurrentLayout.setLayout("CustomerView");
        setContentView(R.layout.activity_customers);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            page_button = new JSONObject(NewRESTClient.retrieveResource("customer"));
            NUM_ITEMS_PAGE = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("per_page");
            TOTAL_LIST_ITEMS = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total");
            TOTAL_PAGES = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total_pages");
        } catch (JSONException e) {e.printStackTrace();}

        allCustomers = new ArrayList<Customer>();

//            loadFromApi("customer?page=" + Integer.toString(2));
        for(int i = 1;i < TOTAL_PAGES + 1; i++)
            loadFromApi("customer?page=" + Integer.toString(i));



        shownCustomers = new ArrayList<Customer>();
        cust_names = new ArrayList<String>();
        for(int i = 0; i < allCustomers.size(); i++)
        {
            cust_names.add(allCustomers.get(i).getName());
        }
        loadMoreData(shownCustomers.size());

        list_customers = (ListView) findViewById(R.id.listView1);

        Btnfooter();

        loadList(0);

        CheckBtnBackGroud(0);

//        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cust_names);
//        ListView list_customers = (ListView) findViewById(R.id.listView1);

        Button btnLoadMore = new Button(this);
//        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
//        list_customers.addFooterView(btnLoadMore);
//        list_customers.setAdapter(adapter);

//        btnLoadMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                // Starting a new async task
//                loadMoreData(shownCustomers.size());
//                adapter.notifyDataSetChanged();
//            }
//        });

        list_customers.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent product_view = new Intent(view.getContext(), CustomerView.class).putExtra("CUST", (Serializable) allCustomers.get((int)id + (NUM_ITEMS_PAGE*currentPage)));
                startActivity(product_view);
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(SessionUser.getRole().equals("sales"))
            fab.setVisibility(View.INVISIBLE);
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

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            ll.addView(btns[i], lp);

            final int j = i;
            btns[j].setOnClickListener(new OnClickListener() {

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
            if(i<allCustomers.size())
            {
                sort.add(allCustomers.get(i).getName());
            }
            else
            {
                break;
            }
        }
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sort);
        list_customers.setAdapter(adapter);
    }

    private void loadFromApi(String path)
    {
        JSONObject customerJSON;
        try {
            customerJSON = new JSONObject(NewRESTClient.retrieveResource(path));
            JSONArray customerArray = customerJSON.getJSONArray("data");
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
    }

}