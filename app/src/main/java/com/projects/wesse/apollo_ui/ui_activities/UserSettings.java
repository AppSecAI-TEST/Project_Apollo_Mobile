package com.projects.wesse.apollo_ui.ui_activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;

/**
 * Created by Xander on 8/2/2017.
 */

public class UserSettings extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        super.onCreateDrawer();



    }
}
