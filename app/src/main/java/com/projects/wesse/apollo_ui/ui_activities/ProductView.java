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
import com.projects.wesse.apollo_ui.Attributes.Product;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;
import com.projects.wesse.apollo_ui.utilities.SessionUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Xander on 7/20/2017.
 */

public class ProductView extends AppCompatActivity {

    private TextView txt;
    private Product value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        value = (Product) getIntent().getSerializableExtra("PROD");

        getSupportActionBar().setTitle(value.getSku());

        //SETTING ATTRIBUTES
        txt = (TextView) findViewById(R.id.prod_sku);
        txt.setText(value.getSku());

        txt = (TextView) findViewById(R.id.prod_decs);
        txt.setText(value.getDescription());

        txt = (TextView) findViewById(R.id.prod_cost_price);
        txt.setText(value.getCost_price().toString());

        txt = (TextView) findViewById(R.id.prod_retail_price);
        txt.setText(value.getRetail_price().toString());

        txt = (TextView) findViewById(R.id.prod_sell_price);
        txt.setText(value.getRecommend_price().toString());

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
                    NewRESTClient.delete("product", value.getId(), LoginActivity.getUser().getJSONToken());
                    value = null;
                    finish();
                    Toast.makeText(getBaseContext(), "Product deleted!", Toast.LENGTH_SHORT).show();
                    Intent getProducts = new Intent(this, Products.class);
                    startActivity(getProducts);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_edit:
                enableFields();

                Button btn_save = (Button) findViewById(R.id.btn_save);
                btn_save.setVisibility(View.VISIBLE);

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                        nvps.add(new BasicNameValuePair("sku", ((TextView) findViewById(R.id.prod_sku)).getText().toString()));
                        nvps.add(new BasicNameValuePair("description", ((TextView) findViewById(R.id.prod_decs)).getText().toString()));
                        nvps.add(new BasicNameValuePair("cost_price", ((TextView) findViewById(R.id.prod_cost_price)).getText().toString()));
                        nvps.add(new BasicNameValuePair("retail_price", ((TextView) findViewById(R.id.prod_retail_price)).getText().toString()));
                        nvps.add(new BasicNameValuePair("recommended_selling_price", ((TextView) findViewById(R.id.prod_sell_price)).getText().toString()));

                        nvps.add(new BasicNameValuePair("_method", "PATCH"));
                        try {
                            NewRESTClient.patch(nvps, "product", value.getId(),LoginActivity.getUser().getJSONToken());
                            //NewRESTClient.post(nvps, "customer/" + value.getId(), LoginActivity.getUser().getJSONToken());
                        } catch (IOException e) {e.printStackTrace();}
                        redirectCustomers("Updated!");
                    }
                });
                break;
            default:
                break;
        }

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent i=new Intent(this, UserSettings.class);
            startActivity(i);
        }


        else if (id == R.id.action_logout) {
            SessionUser.logoutAction();
            Intent i=new Intent(this, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void redirectCustomers(String message)
    {
        finish();
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
        Intent getCustomer = new Intent(this, Products.class);
        startActivity(getCustomer);
    }

    public void enableFields()
    {
        EditText txt;

        txt = (EditText) findViewById(R.id.prod_sku);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.prod_decs);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.prod_cost_price);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.prod_retail_price);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.prod_sell_price);
        txt.setEnabled(true);
    }
}
