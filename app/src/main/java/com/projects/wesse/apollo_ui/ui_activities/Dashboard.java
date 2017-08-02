package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        super.onCreateDrawer();



        LineChart chart = (LineChart) findViewById(R.id.chart);


        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 6500));
        entries.add(new Entry(2, 7800));
        entries.add(new Entry(3, 3200));
        entries.add(new Entry(4, 4500));
        entries.add(new Entry(5, 1300));
        entries.add(new Entry(6, 6500));
        entries.add(new Entry(7, 7800));
        entries.add(new Entry(8, 3200));
        entries.add(new Entry(9, 4500));
        entries.add(new Entry(10, 1300));
        entries.add(new Entry(11, 4500));
        entries.add(new Entry(12, 1300));

        final ArrayList<String> xLabel = new ArrayList<String>();
        xLabel.add("");
        xLabel.add("January");
        xLabel.add("February");
        xLabel.add("March");
        xLabel.add("April");
        xLabel.add("May");
        xLabel.add("June");
        xLabel.add("July");
        xLabel.add("August");
        xLabel.add("September");
        xLabel.add("October");
        xLabel.add("November");
        xLabel.add("December");

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int)value);
            }
        });

        YAxis rightYAxis = chart.getAxisRight();
        rightYAxis.setEnabled(false);

        LineDataSet dataSet = new LineDataSet(entries, "Stock"); // add entries to dataset
        dataSet.setDrawValues(false);

        LineData lineData = new LineData(dataSet);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);

        chart.setNoDataText("No input found");

        chart.setData(lineData);
        chart.invalidate();

//        GraphView graph = (GraphView) findViewById(R.id.graph);
//        graph.getViewport().setScrollable(true);
//        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
//        staticLabelsFormatter.setHorizontalLabels(new String[] {"Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec", "Jan"});
//        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
//
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
//                new DataPoint(1, 6500),
//                new DataPoint(2, 5900),
//                new DataPoint(3, 8000),
//                new DataPoint(4, 8100),
//                new DataPoint(5, 5600),
//                new DataPoint(6, 5500),
//                new DataPoint(7, 4000),
//                new DataPoint(8, 6500),
//                new DataPoint(9, 5900),
//                new DataPoint(10, 8000),
//                new DataPoint(11, 8100),
//                new DataPoint(12, 5600),
//        });
//        graph.addSeries(series);

        Intent previousActivity = getIntent();
    }






}