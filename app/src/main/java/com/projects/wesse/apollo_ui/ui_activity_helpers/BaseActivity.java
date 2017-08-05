package com.projects.wesse.apollo_ui.ui_activity_helpers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.ui_activities.Customers;
import com.projects.wesse.apollo_ui.ui_activities.Dashboard;
import com.projects.wesse.apollo_ui.ui_activities.LoginActivity;
import com.projects.wesse.apollo_ui.ui_activities.Message;
import com.projects.wesse.apollo_ui.ui_activities.Orders;
import com.projects.wesse.apollo_ui.ui_activities.Products;
import com.projects.wesse.apollo_ui.ui_activities.Purchases;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activities.Sales;
import com.projects.wesse.apollo_ui.ui_activities.Suppliers;
import com.projects.wesse.apollo_ui.ui_activities.UserSettings;
import com.projects.wesse.apollo_ui.utilities.SessionUser;
import com.projects.wesse.apollo_ui.utilities.Test;

/**
 * Created by Xander on 7/19/2017.
 */

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    public String currentActivity;
    public static NavigationView navigationView;
    private TextView nav_draw_name;
    private TextView nav_draw_email;

    protected void onCreateDrawer()
    {
        //currentActivity = actv;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView =  LayoutInflater.from(this).inflate(R.layout.nav_header_dashboard, navigationView, false);
        navigationView.addHeaderView(headerView);
        TextView Name = (TextView) headerView.findViewById(R.id.nav_draw_name);
        Name.setText(LoginActivity.getUser().getName());
        TextView email = (TextView) headerView.findViewById(R.id.nav_draw_email);
        email.setText(LoginActivity.getUser().getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this, UserSettings.class);
            startActivity(i);
        }
        else if (id == R.id.action_logout) {
            //TODO : CLEAR ALL USER DATA
            dashboardNavAction();
            finish();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            dashboardNavAction();
        } else if (id == R.id.nav_product) {
            productNavAction();
        } else if (id == R.id.nav_supplier) {
            supplierNavAction();
        }else if (id == R.id.nav_customer) {
            customerNavAction();
        }else if (id == R.id.nav_purchases) {
            purchaseNavAction();
        }/*else if (id == R.id.nav_orders) {
            ordersNavAction();
        }*/else if (id == R.id.nav_sales) {
            salesNavAction();
        }else if (id == R.id.nav_messages) {
            messageNavAction();
        }else if (id == R.id.nav_test)
        {
            testNavAction();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void testNavAction() {
        Intent getTestActivity = new Intent(this, Test.class);
        startActivity(getTestActivity);
    }

    public void dashboardNavAction() {
        Intent i=new Intent(this, Dashboard.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
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