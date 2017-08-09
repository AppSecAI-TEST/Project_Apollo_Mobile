package com.projects.wesse.apollo_ui.Attributes;

import com.github.mikephil.charting.components.YAxis;
import com.projects.wesse.apollo_ui.utilities.NewRESTClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wesse on 2017/08/09.
 */

public class DashboardData {
    private int stockUnits;
    private Double stockValue;
    private Double estimateMargin;
    private String startDate;
    private String endDate;
    private String[] XAxisLabels;
    private Double[] YAxisData;

    private JSONObject dashboardJSON;

    public DashboardData(){
        try {
            dashboardJSON = new JSONObject(NewRESTClient.retrieveResource("dashboard"));
            stockUnits = (Integer) dashboardJSON.getJSONObject("data").get("stockUnits");
            stockValue = (Double) dashboardJSON.getJSONObject("data").get("stockValue");
            estimateMargin = (Double) dashboardJSON.getJSONObject("data").get("estimateMargin");
            startDate = (String) dashboardJSON.getJSONObject("data").getJSONObject("yearlyRecap").get("startDate");
            endDate = (String) dashboardJSON.getJSONObject("data").getJSONObject("yearlyRecap").get("endDate");

            JSONArray  XAxisLabelArray = (JSONArray)  dashboardJSON.getJSONObject("data").getJSONObject("yearlyRecap").get("labels");
            String splittable = XAxisLabelArray.toString();
            XAxisLabels = splittable.split(",");

            JSONArray  YAxisLabelArray = (JSONArray) dashboardJSON.getJSONObject("data").getJSONObject("yearlyRecap").get("data");
            YAxisData = new Double[YAxisLabelArray.length()];
            for(int i = 0; i < YAxisLabelArray.length(); i++)
                YAxisData[i] = YAxisLabelArray.getDouble(i);
        }
        catch(JSONException e){}
    }


    public int getStockUnits() {
        return stockUnits;
    }

    public void setStockUnits(int stockUnits) {
        this.stockUnits = stockUnits;
    }

    public Double getStockValue() {
        return stockValue;
    }

    public void setStockValue(Double stockValue) {
        this.stockValue = stockValue;
    }

    public Double getEstimateMargin() {
        return estimateMargin;
    }

    public void setEstimateMargin(Double estimateMargin) {
        this.estimateMargin = estimateMargin;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getXAxisLabels() {
        return XAxisLabels;
    }

    public void setXAxisLabels(String[] XAxisLabels) {
        this.XAxisLabels = XAxisLabels;
    }

    public Double[] getYAxiData() {
        return YAxisData;
    }

    public void setYAxiData(Double[] YAxiData) {
        this.YAxisData = YAxiData;
    }
}
