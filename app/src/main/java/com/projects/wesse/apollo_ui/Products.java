package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projects.wesse.apollo_ui.utilities.ListAdapter;

import java.util.ArrayList;

public class Products extends AppCompatActivity {

    ArrayList<String> products = new ArrayList<String>();;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

<<<<<<< HEAD
        //display list of products

        ListView theListView = (ListView) findViewById(R.id.product_view);
        ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        theListView.setAdapter(myarrayAdapter);
        theListView.setTextFilterEnabled(true);

        for(int i = 0; i < 10; i++)
            products.add("Stock Item " + i+1);


        myarrayAdapter.notifyDataSetChanged();

=======
        products = new ArrayList<String>();


        ListView theListView = (ListView) findViewById(R.id.listView1);
        adapter = new ListAdapter(this, products);
        theListView.setAdapter(adapter);
        theListView.setTextFilterEnabled(true);

        for(int i = 0; i < 100; i++)
            products.add("Product " + (i + 1));

        adapter.notifyDataSetChanged();
>>>>>>> 532d833a16fd6556c140d4c30971bf6f503f3a2a

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        Intent previousActivity = getIntent();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_dashboard_drawer, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }

}
