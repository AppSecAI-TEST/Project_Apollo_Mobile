package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.utilities.CustomAdapter;

import java.util.ArrayList;

/**
 * Created by Xander on 7/20/2017.
 */

public class CustomerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

//        Bundle b = getIntent().getExtras();
//        String value = "Peter";
//        if(b != null)
//            value = b.getString("CUSTOMER_ID");
//        setCustomer(value);

    }

    public void setCustomer(String name)
    {
        TextView custName = (TextView) findViewById(R.id.cust_name);
    }
}
