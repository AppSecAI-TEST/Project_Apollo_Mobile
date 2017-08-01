package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;

import java.util.ArrayList;

/**
 * Created by Xander on 7/18/2017.
 */

public class Orders extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        super.onCreateDrawer();

    }
}
