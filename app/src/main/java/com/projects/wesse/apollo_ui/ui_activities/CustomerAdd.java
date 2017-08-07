package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.projects.wesse.apollo_ui.Attributes.Customer;
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

public class CustomerAdd extends AppCompatActivity {

   private TextView txt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        clearFields();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btn = (Button) findViewById(R.id.btn_save);
        btn.setText("Add");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("id", findViewById(R.id.cust_name).toString()));
                nvps.add(new BasicNameValuePair("name", findViewById(R.id.cust_name).toString()));
                nvps.add(new BasicNameValuePair("telephone", findViewById(R.id.cust_tel).toString()));
                nvps.add(new BasicNameValuePair("email", findViewById(R.id.email).toString()));
                nvps.add(new BasicNameValuePair("address", findViewById(R.id.cust_address).toString()));
                nvps.add(new BasicNameValuePair("address_2", findViewById(R.id.cust_sec_address).toString()));
                nvps.add(new BasicNameValuePair("city", findViewById(R.id.cust_city).toString()));
                nvps.add(new BasicNameValuePair("province", findViewById(R.id.cust_prov).toString()));
                nvps.add(new BasicNameValuePair("country", findViewById(R.id.cust_country).toString()));

                try {
                    NewRESTClient.post(nvps, "customer");
                } catch (IOException e) {e.printStackTrace();}

                Toast.makeText(getBaseContext(), "Customer added!", Toast.LENGTH_SHORT).show();
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
        txt = (TextView) findViewById(R.id.cust_name);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_email);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_tel);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_address);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_sec_address);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_city);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_country);
        txt.setEnabled(true);
        txt.setText("");

        txt = (TextView) findViewById(R.id.cust_prov);
        txt.setEnabled(true);
        txt.setText("");

        Button btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setVisibility(View.VISIBLE);
    }
}
