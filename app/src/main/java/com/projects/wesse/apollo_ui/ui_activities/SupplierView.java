package com.projects.wesse.apollo_ui.ui_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;

/**
 * Created by Xander on 7/20/2017.
 */

public class SupplierView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle b = getIntent().getExtras();
        String value = "Random Supplier";
        if(b != null)
            value = b.getString("ID");
        setProduct(value);

    }

    public void setProduct(String name)
    {
        EditText prodName = (EditText) findViewById(R.id.cust_name);
        prodName.setText(name);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_top, menu);
        getMenuInflater().inflate(R.menu.menu_options_top, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_delete:
                //DELETE
                break;
            case R.id.action_edit:
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

                Button btn_save = (Button) findViewById(R.id.btn_save);
                btn_save.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }

        return true;
    }
}
