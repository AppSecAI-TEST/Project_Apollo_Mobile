package com.projects.wesse.apollo_ui.ui_activity_helpers;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.projects.wesse.apollo_ui.R;

/**
 * Created by Xander on 8/6/2017.
 */

public class CustomMarkerView extends MarkerView {

    private TextView tvContent;
    public CustomMarkerView (Context context, int layoutResource) {
        super(context, layoutResource);
        // this markerview only displays a textview
        tvContent = (TextView) findViewById(R.id.tvContent);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText(/*e.getX() + " " + */(int) e.getY()); // set the entry-value as the display text

        super.refreshContent(e, highlight);
    }

    public int getXOffset() {
        // this will center the marker-view horizontally
        return -(getWidth() / 2);
    }

    public int getYOffset() {
        // this will cause the marker-view to be above the selected value
        return -getHeight();
    }
}