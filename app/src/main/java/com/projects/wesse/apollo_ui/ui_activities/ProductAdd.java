package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Xander on 8/7/2017.
 */

public class ProductAdd extends AppCompatActivity {

    TextView txt;
    private List<NameValuePair> nvps;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        clearFields();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nvps = new ArrayList<NameValuePair>();

        Button btn = (Button) findViewById(R.id.btn_save);
        btn.setText("Add");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nvps.add(new BasicNameValuePair("sku", ((TextView) findViewById(R.id.prod_sku)).getText().toString()));
                nvps.add(new BasicNameValuePair("description", ((TextView) findViewById(R.id.prod_decs)).getText().toString()));
                nvps.add(new BasicNameValuePair("cost_price", ((TextView) findViewById(R.id.prod_cost_price)).getText().toString()));
                nvps.add(new BasicNameValuePair("retail_price", ((TextView) findViewById(R.id.prod_retail_price)).getText().toString()));
                nvps.add(new BasicNameValuePair("recommended_selling_price", ((TextView) findViewById(R.id.prod_sell_price)).getText().toString()));

                try {
                    NewRESTClient.post(nvps, "product", LoginActivity.getUser().getJSONToken());
                } catch (IOException e) {e.printStackTrace();}
                clearFields();
                finish();
                Toast.makeText(getBaseContext(), "Product added!", Toast.LENGTH_SHORT).show();
                Intent getCustomer = new Intent(v.getContext(), Products.class);
                startActivity(getCustomer);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options_top, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }
        return true;
    }


    public void clearFields()
    {
        txt = (TextView) findViewById(R.id.prod_sell_price);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.prod_sku);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.prod_cost_price);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.prod_decs);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.prod_retail_price);
        txt.setEnabled(true);
        txt.setText("");

        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setVisibility(View.VISIBLE);
    }
}
