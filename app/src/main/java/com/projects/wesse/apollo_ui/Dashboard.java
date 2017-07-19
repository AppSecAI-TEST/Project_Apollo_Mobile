package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.projects.wesse.apollo_ui.utilities.BaseActivity;

public class Dashboard extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        super.onCreateDrawer();


        GraphView graph = (GraphView) findViewById(R.id.graph);
        graph.getViewport().setScrollable(true);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec", "Jan"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(1, 6500),
                new DataPoint(2, 5900),
                new DataPoint(3, 8000),
                new DataPoint(4, 8100),
                new DataPoint(5, 5600),
                new DataPoint(6, 5500),
                new DataPoint(7, 4000),
                new DataPoint(8, 6500),
                new DataPoint(9, 5900),
                new DataPoint(10, 8000),
                new DataPoint(11, 8100),
                new DataPoint(12, 5600),
        });
        graph.addSeries(series);





        Intent previousActivity = getIntent();
        String dataFromPrev = previousActivity.getExtras().getString("fromLogin");
    }




}
