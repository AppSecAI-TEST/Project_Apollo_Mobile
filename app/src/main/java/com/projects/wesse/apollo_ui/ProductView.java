package com.projects.wesse.apollo_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Xander on 7/20/2017.
 */

public class ProductView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        Bundle b = getIntent().getExtras();
        String value = "Random Product";
        if(b != null)
            value = b.getString("ID");
        setProduct(value);

    }

    public void setProduct(String name)
    {
        TextView prodName = (TextView) findViewById(R.id.cust_name);
        prodName.setText(name);
    }
}
