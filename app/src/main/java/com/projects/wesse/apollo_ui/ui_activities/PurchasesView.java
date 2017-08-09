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
import com.projects.wesse.apollo_ui.Attributes.Purchase;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import java.io.IOException;

/**
 * Created by Xander on 7/20/2017.
 */

public class PurchasesView extends AppCompatActivity {

    private TextView txt;
    private Purchase value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        value = (Purchase) getIntent().getSerializableExtra("PURCHASE");

        getSupportActionBar().setTitle(value.getId());


        //SETTING ATTRIBUTES
        txt = (TextView) findViewById(R.id.pur_supp);
        txt.setText(value.getSupplier().getName());

        txt = (TextView) findViewById(R.id.pur_id);
        txt.setText(value.getId());

        txt = (TextView) findViewById(R.id.pur_placed_at);
        txt.setText(value.getPlaced_at());

        txt = (TextView) findViewById(R.id.pur_proc_at);
        txt.setText(value.getProcessed_at());

        txt = (TextView) findViewById(R.id.pur_total);
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
                    NewRESTClient.delete("purchase", value.getId(), LoginActivity.getUser().getJSONToken());
                    value = null;
                    finish();
                    Toast.makeText(getBaseContext(), "Purchase deleted!", Toast.LENGTH_SHORT).show();
                    Intent geturchases = new Intent(this, Purchases.class);
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
        txt = (EditText) findViewById(R.id.pur_total);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.pur_proc_at);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.pur_placed_at);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.pur_id);
        txt.setEnabled(true);

        txt = (EditText) findViewById(R.id.pur_supp);
        txt.setEnabled(true);
    }
}
