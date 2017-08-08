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
    private List<NameValuePair> nvps;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        clearFields();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nvps = new ArrayList<NameValuePair>();

        Button btn = (Button) findViewById(R.id.btn_save);
        btn.setText("Add");

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                nvps.add(new BasicNameValuePair("name", ((TextView) findViewById(R.id.cust_name)).getText().toString()));
                nvps.add(new BasicNameValuePair("telephone", ((TextView) findViewById(R.id.cust_tel)).getText().toString()));
                nvps.add(new BasicNameValuePair("email", ((TextView) findViewById(R.id.cust_email)).getText().toString()));
                nvps.add(new BasicNameValuePair("address", ((TextView) findViewById(R.id.cust_address)).getText().toString()));
                nvps.add(new BasicNameValuePair("address_2", ((TextView) findViewById(R.id.cust_sec_address)).getText().toString()));
                nvps.add(new BasicNameValuePair("city", ((TextView) findViewById(R.id.cust_city)).getText().toString()));
                nvps.add(new BasicNameValuePair("province", ((TextView) findViewById(R.id.cust_prov)).getText().toString()));
                nvps.add(new BasicNameValuePair("country", ((TextView) findViewById(R.id.cust_country)).getText().toString()));

               try {
                    NewRESTClient.post(nvps, "customer", LoginActivity.getUser().getJSONToken());
                } catch (IOException e) {e.printStackTrace();}
                clearFields();
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
