package com.projects.wesse.apollo_ui.ui_activities;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
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
    ListView list_sales;
    ArrayAdapter<String> adapter;

    public int TOTAL_LIST_ITEMS;
    public int NUM_ITEMS_PAGE;
    private int TOTAL_PAGES;
    int currentPage = 0;
    private int noOfBtns;
    private Button[] btns;

    private JSONObject page_button;


    ProgressBar spinner;

    // Index from which pagination should start (0 is 1st page in our case)
    private static final int PAGE_START = 0;
    // Indicates if footer ProgressBar is shown (i.e. next page is loading)
    private boolean isLoading = false;
    // If current page is the last page (Pagination will stop after this page load)
    private boolean isLastPage = false;
    // total no. of pages to load. Initial load is page 0, after which 2 more pages will load.
    // indicates the current page which Pagination is fetching.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            page_button = new JSONObject(NewRESTClient.retrieveResource("sale"));
            NUM_ITEMS_PAGE = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("per_page");
            TOTAL_LIST_ITEMS = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total");
            TOTAL_PAGES = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total_pages");
        } catch (JSONException e) {e.printStackTrace();}

        allSales = new ArrayList<Sale>();

        for(int i = 1;i < TOTAL_PAGES + 1; i++)
            loadFromApi("sale?page=" + Integer.toString(i));


        shownSales = new ArrayList<Sale>();
        sale_names = new ArrayList<String>();
        for(int i = 0; i < allSales.size(); i++)
        {
            sale_names.add(allSales.get(i).getId() + " || " + allSales.get(i).getCustomer().getName());
        }
        loadMoreData(shownSales.size());

        list_sales = (ListView) findViewById(R.id.listView1);

        Btnfooter();

        loadList(0);

        CheckBtnBackGroud(0);

        list_sales.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent cust_view = new Intent(view.getContext(), SalesView.class).putExtra("SALES", (Serializable) allSales.get((int)id + (NUM_ITEMS_PAGE*currentPage)));
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
            if(i<allSales.size())
            {
                sort.add(allSales.get(i).getId() + " || " + allSales.get(i).getCustomer().getName());
            }
            else
            {
                break;
            }
        }
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sort);
        list_sales.setAdapter(adapter);
    }

    private void loadFromApi(String path)
    {
        JSONObject saleJSON;
        try {
            saleJSON = new JSONObject(NewRESTClient.retrieveResource(path));
            JSONArray saleArray = saleJSON.getJSONArray("data");
            for(int i = 0; i < saleArray.length(); i++){
                final JSONObject customer = saleArray.getJSONObject(i).getJSONObject("customer").getJSONObject("data");
                Customer tempCus = new Customer(
                        customer.getInt("id"),
                        customer.getString("name"),
                        customer.getString("email"),
                        customer.getString("telephone"),
                        customer.getString("address"),
                        customer.getString("address_2"),
                        customer.getString("city"),
                        customer.getString("province"),
                        customer.getString("country")
                );
                final JSONObject sale = saleArray.getJSONObject(i);
                Sale temp = new Sale(
                        sale.getInt("id"),
                        sale.getString("placed_at"),
                        sale.getDouble("total"),
                        tempCus);
                allSales.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}
    }
}

