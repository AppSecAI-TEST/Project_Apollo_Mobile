package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Xander on 7/18/2017.
 */

public class Purchases extends BaseActivity {

    ArrayList<String> purhcases, menuItems;
    ListView list_purchases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
        super.onCreateDrawer();


        purhcases = new ArrayList<String>();
        for(int i = 0; i < 10; i++)
            purhcases.add("Purchase " + (i + 1));

        //CustomAdapter adapter = new CustomAdapter(products, this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, purhcases);
        list_purchases = (ListView) findViewById(R.id.listView1);
        list_purchases.setAdapter(adapter);

        list_purchases.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();

                Intent product_view = new Intent(view.getContext(), PurchasesView.class).putExtra("ID", item);
                startActivity(product_view);
            }


        });

    }
}
