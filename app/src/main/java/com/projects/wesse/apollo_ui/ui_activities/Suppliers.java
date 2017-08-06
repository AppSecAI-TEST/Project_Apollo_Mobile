package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CurrentLayout;

import java.util.ArrayList;

public class Suppliers extends BaseActivity {

    ArrayList<String> allSuppliers, shownSuppliers;
    ListView list_supplier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers);
        CurrentLayout.setLayout("SuppliersView");
        super.onCreateDrawer();

        allSuppliers = new ArrayList<String>();
        for(int i = 0; i < 100; i++)
            allSuppliers.add("Supplier " + (i + 1));

        shownSuppliers = new ArrayList<String>();
        loadMoreData(shownSuppliers.size());

        //CustomAdapter adapter = new CustomAdapter(allProducts, this);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, shownSuppliers);
        list_supplier = (ListView) findViewById(R.id.listView1);

        Button btnLoadMore = new Button(this);
        btnLoadMore.setText("Load More");

        // Adding Load More button to lisview at bottom
        list_supplier.addFooterView(btnLoadMore);

        list_supplier.setAdapter(adapter);

        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // Starting a new async task
                loadMoreData(shownSuppliers.size());
                adapter.notifyDataSetChanged();
            }
        });

        list_supplier.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((TextView)view).getText().toString();

                Intent product_view = new Intent(view.getContext(), SupplierView.class).putExtra("ID", item);
                startActivity(product_view);
            }


        });

        Intent previousActivity = getIntent();
    }

    public void loadMoreData(int length)
    {
        for (int i = length ; i < length + 10; i++)
            if(allSuppliers.size() > i)
                shownSuppliers.add(allSuppliers.get(i));
    }
}