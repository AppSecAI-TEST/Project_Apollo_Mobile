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
        setContentView(R.layout.activity_suppliers);
        CurrentLayout.setLayout("SuppliersView");
        super.onCreateDrawer();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            page_button = new JSONObject(NewRESTClient.retrieveResource("supplier"));
            NUM_ITEMS_PAGE = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("per_page");
            TOTAL_LIST_ITEMS = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total");
            TOTAL_PAGES = (Integer) page_button.getJSONObject("meta").getJSONObject("pagination").get("total_pages");
        } catch (JSONException e) {e.printStackTrace();}

        allSuppliers = new ArrayList<Supplier>();

        for(int i = 1;i < TOTAL_PAGES + 1; i++)
            loadFromApi("supplier?page=" + Integer.toString(i));



        shownSuppliers = new ArrayList<Supplier>();
        supp_names = new ArrayList<String>();
        for(int i = 0; i < allSuppliers.size(); i++)
        {
            supp_names.add(allSuppliers.get(i).getName());
        }
        loadMoreData(shownSuppliers.size());

        list_supplier = (ListView) findViewById(R.id.listView1);

        Btnfooter();

        loadList(0);

        CheckBtnBackGroud(0);

        list_supplier.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent supplier_view = new Intent(view.getContext(), SupplierView.class).putExtra("SUPPLIER", (Serializable) allSuppliers.get((int)id + (NUM_ITEMS_PAGE*currentPage)));
                startActivity(supplier_view);
            }


        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);
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
            if(i<allSuppliers.size())
            {
                sort.add(allSuppliers.get(i).getName());
            }
            else
            {
                break;
            }
        }
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                sort);
        list_supplier.setAdapter(adapter);
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allSuppliers.size() > i)
                shownSuppliers.add(allSuppliers.get(i));
    }


    private void loadFromApi(String path)
    {
        JSONObject customerJSON;
        try {
            customerJSON = new JSONObject(NewRESTClient.retrieveResource(path));
            JSONArray customerArray = customerJSON.getJSONArray("data");
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
                        customerArray.getJSONObject(i).getString("country"),
                        customerArray.getJSONObject(i).getInt("lead_time")
                );
                allSuppliers.add(temp);
            }
        } catch (JSONException e) {e.printStackTrace();}
    }
}