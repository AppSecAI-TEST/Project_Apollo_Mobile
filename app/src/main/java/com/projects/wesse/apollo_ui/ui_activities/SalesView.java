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

import com.projects.wesse.apollo_ui.Attributes.Sale;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import java.io.IOException;

/**
 * Created by wesse on 2017/08/09.
 */

public class SalesView  extends AppCompatActivity {
    private TextView txt;
    private Sale value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        value = (Sale) getIntent().getSerializableExtra("PURCHASE");

        getSupportActionBar().setTitle(value.getCustomer().getName());


        //SETTING ATTRIBUTES
        txt = (TextView) findViewById(R.id.sale_cust);
        txt.setText(value.getCustomer().getName());

        txt = (TextView) findViewById(R.id.sale_placed_at);
        txt.setText(value.getPlaced_at());

        txt = (TextView) findViewById(R.id.sale_total);
        txt.setText(value.getTotal().toString());
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
                    NewRESTClient.delete("sale", value.getId(), LoginActivity.getUser().getJSONToken());
                    value = null;
                    finish();
                    Toast.makeText(getBaseContext(), "Sale deleted!", Toast.LENGTH_SHORT).show();
                    Intent geturchases = new Intent(this, Sales.class);
                    startActivity(geturchases);
                }
                catch (IOException e) { e.printStackTrace(); }
                break;
            case R.id.action_edit:
                enableFields();

                Button btn_save = (Button) findViewById(R.id.btn_save);
                btn_save.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        return true;
    }

    public void enableFields()
    {
        EditText txt;
        txt = (EditText) findViewById(R.id.sale_total);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.sale_placed_at);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.sale_total);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.sale_cust);
        txt.setEnabled(true);
    }
}

