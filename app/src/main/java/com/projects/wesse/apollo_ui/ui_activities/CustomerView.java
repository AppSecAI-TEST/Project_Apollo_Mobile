package com.projects.wesse.apollo_ui.ui_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;

/**
 * Created by Xander on 7/20/2017.
 */

public class CustomerView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        Bundle b = getIntent().getExtras();
        String value = "Peter";
        if(b != null)
            value = b.getString("ID");
        setCustomer(value);

    }

    public void setCustomer(String name)
    {
        TextView custName = (TextView) findViewById(R.id.cust_name);
        custName.setText(name);
    }
}
