package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;

import java.util.ArrayList;

public class Products extends BaseActivity {

    ArrayList<String> products, menuItems;
    ListView list_products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        CurrentLayout.setLayout("ProductView");
        super.onCreateDrawer();

        products = new ArrayList<String>();
        for(int i = 0; i < 100; i++)
            products.add("Product " + (i + 1));

        //CustomAdapter adapter = new CustomAdapter(products, this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        list_products = (ListView) findViewById(R.id.listView1);
        list_products.setAdapter(adapter);

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
}