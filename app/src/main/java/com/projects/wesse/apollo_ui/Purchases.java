package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Xander on 7/18/2017.
 */

public class Purchases extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ArrayList<String> purhcases, menuItems;
    ListView list_purchases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //nav view
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //nav view





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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {

        } else if (id == R.id.nav_product) {
            productNavAction();
        } else if (id == R.id.nav_supplier) {
            supplierNavAction();
        }else if (id == R.id.nav_customer) {
            customerNavAction();
        }else if (id == R.id.nav_purchases) {
            purchaseNavAction();
        }else if (id == R.id.nav_orders) {
            ordersNavAction();
        }else if (id == R.id.nav_sales) {
            salesNavAction();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void productNavAction() {
        Intent getProductActivity = new Intent(this, Products.class);
        startActivity(getProductActivity);
    }

    public void supplierNavAction() {
        Intent getSupplyActivity = new Intent(this, Suppliers.class);
        startActivity(getSupplyActivity);
    }

    public void customerNavAction() {
        Intent getCustomer = new Intent(this, Customers.class);
        startActivity(getCustomer);
    }

    public void messageNavAction() {
        Intent getMessageActivity = new Intent(this, Message.class);
        startActivity(getMessageActivity);
    }

    public void purchaseNavAction() {
        Intent getMessageActivity = new Intent(this, Purchases.class);
        startActivity(getMessageActivity);
    }

    public void ordersNavAction() {
        Intent getMessageActivity = new Intent(this, Orders.class);
        startActivity(getMessageActivity);
    }

    public void salesNavAction() {
        Intent getMessageActivity = new Intent(this, Sales.class);
        startActivity(getMessageActivity);
    }
}
