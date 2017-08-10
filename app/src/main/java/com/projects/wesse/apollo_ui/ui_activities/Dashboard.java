package com.projects.wesse.apollo_ui.ui_activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.projects.wesse.apollo_ui.Attributes.Customer;
import com.projects.wesse.apollo_ui.Attributes.DashboardData;
import com.projects.wesse.apollo_ui.R;
import com.projects.wesse.apollo_ui.ui_activity_helpers.BaseActivity;
import com.projects.wesse.apollo_ui.ui_activity_helpers.CustomMarkerView;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends BaseActivity {

    private List<Entry> entries;
    private static DashboardData dashD;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        super.onCreateDrawer();


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        dashD = new DashboardData();


        txt = (TextView) findViewById(R.id.dash_overall_stock_levels);
        txt.setText(String.valueOf(dashD.getStockUnits()));
        txt = (TextView) findViewById(R.id.dash_stock);
        txt.setText("R " + Double.toString(dashD.getStockValue()));
        txt = (TextView) findViewById(R.id.dash_est_margin);
        txt.setText("R " + Double.toString(dashD.getEstimateMargin()));

//        Double[] data = dashD.getYAxiData();

        final LineChart chart = (LineChart) findViewById(R.id.chart);
        entries = new ArrayList<>();
        for(int i = 1; i < 13 ; i++)
        {
            entries.add(new Entry(i, dashD.getYAxiData(i - 1).floatValue()));
        }

//        String[] labels = dashD.getXAxisLabels();

        final ArrayList<String> xLabel = new ArrayList<String>();
        xLabel.add("");
        for(int i = 0; i < 12 ; i++)
        {
            xLabel.add(dashD.getXAxisLabels(i));
        }


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
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setLineWidth(2);
        dataSet.setColor(Color.BLUE);
        dataSet.setDrawValues(false);


        LineData lineData = new LineData(dataSet);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setHighlightPerTapEnabled(true);
        chart.animateY(2000);

        final CustomMarkerView mv = new CustomMarkerView (getApplicationContext(), R.layout.tv_content);
        chart.setMarkerView(mv);

        chart.setNoDataText("No input found");

        chart.setData(lineData);
        chart.invalidate();

        Intent previousActivity = getIntent();
    }








}
