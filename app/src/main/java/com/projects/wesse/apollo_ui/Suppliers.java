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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.projects.wesse.apollo_ui.utilities.CustomAdapter;

import java.util.ArrayList;

public class Suppliers extends AppCompatActivity {

    ArrayList<String> suppliers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        suppliers = new ArrayList<String>();
        for(int i = 0; i < 100; i++)
            suppliers.add("Supplier " + (i + 1));

        CustomAdapter adapter = new CustomAdapter(suppliers, this);
        ListView theListView = (ListView) findViewById(R.id.listView1);
        theListView.setAdapter(adapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                String supplierPicked = "You selected " + String.valueOf(adapterView.getItemAtPosition(i));
                Toast.makeText(Suppliers.this, supplierPicked, Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
