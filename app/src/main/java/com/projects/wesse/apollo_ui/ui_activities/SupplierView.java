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

import com.projects.wesse.apollo_ui.Attributes.Supplier;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Xander on 7/20/2017.
 */

public class SupplierView extends AppCompatActivity {

    private TextView txt;
    private Supplier value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        value = (Supplier) getIntent().getSerializableExtra("SUPPLIER");

        getSupportActionBar().setTitle(value.getName());

        txt = (TextView) findViewById(R.id.cust_name);
        txt.setText(value.getName());

        txt = (TextView) findViewById(R.id.cust_email);
        txt.setText(value.getEmail());

        txt = (TextView) findViewById(R.id.cust_tel);
        txt.setText(value.getTel());

        txt = (TextView) findViewById(R.id.cust_address);
        txt.setText(value.getAddress());

        txt = (TextView) findViewById(R.id.cust_sec_address);
        txt.setText(value.getSec_address());

        txt = (TextView) findViewById(R.id.cust_city);
        txt.setText(value.getCity());

        txt = (TextView) findViewById(R.id.cust_country);
        txt.setText(value.getCountry());

        txt = (TextView) findViewById(R.id.cust_prov);
        txt.setText(value.getProvince());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_top, menu);
        getMenuInflater().inflate(R.menu.menu_options_top, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_delete:
                try {
                    NewRESTClient.delete("supplier", value.getId(), LoginActivity.getUser().getJSONToken());
                    value = null;
                    redirectSupplier("Supplier deleted!");
                }
                catch (IOException e) { e.printStackTrace(); }
                break;
            case R.id.action_edit:
                enableFields();

                Button btn_save = (Button) findViewById(R.id.btn_save);
                btn_save.setVisibility(View.VISIBLE);

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("name", ((TextView) findViewById(R.id.cust_name)).getText().toString()));
                        nvps.add(new BasicNameValuePair("telephone", ((TextView) findViewById(R.id.cust_tel)).getText().toString()));
                        nvps.add(new BasicNameValuePair("email", ((TextView) findViewById(R.id.cust_email)).getText().toString()));
                        nvps.add(new BasicNameValuePair("address", ((TextView) findViewById(R.id.cust_address)).getText().toString()));
                        nvps.add(new BasicNameValuePair("address_2", ((TextView) findViewById(R.id.cust_sec_address)).getText().toString()));
                        nvps.add(new BasicNameValuePair("city", ((TextView) findViewById(R.id.cust_city)).getText().toString()));
                        nvps.add(new BasicNameValuePair("province", ((TextView) findViewById(R.id.cust_prov)).getText().toString()));
                        nvps.add(new BasicNameValuePair("country", ((TextView) findViewById(R.id.cust_country)).getText().toString()));
                        nvps.add(new BasicNameValuePair("_method", "PATCH"));
                        try {
                            NewRESTClient.patch(nvps, "supplier", value.getId(),LoginActivity.getUser().getJSONToken());
                            //NewRESTClient.post(nvps, "customer/" + value.getId(), LoginActivity.getUser().getJSONToken());
                        } catch (IOException e) {e.printStackTrace();}
                        redirectSupplier("Updated!");
                    }
                });
                break;
            default:
                break;
        }

        return true;
    }

    public void redirectSupplier(String message)
    {
        finish();
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        Intent supp = new Intent(this, Suppliers.class);
        startActivity(supp);
    }

    public void enableFields()
    {
        EditText txt;
        txt = (EditText) findViewById(R.id.cust_name);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_email);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_tel);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_address);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_sec_address);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_city);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_prov);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.cust_country);
        txt.setEnabled(true);
    }
}
