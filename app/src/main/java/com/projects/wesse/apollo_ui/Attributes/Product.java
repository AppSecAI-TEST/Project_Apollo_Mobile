package com.projects.wesse.apollo_ui.Attributes;

import java.io.Serializable;

/**
 * Created by Xander on 8/7/2017.
 */

public class Product implements Serializable {
    private int id;
    private String supplier;
    private String sku;
    private String description;
    private String cost_price;
    private String retail_price;
    private String recommend_price;

    //GETTER AND SETTERS
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCost_price() {
        return cost_price;
    }

    public void setCost_price(String cost_price) {
        this.cost_price = cost_price;
    }

    public String getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(String retail_price) {
        this.retail_price = retail_price;
    }

    public String getRecommend_price() {
        return recommend_price;
    }

    public void setRecommend_price(String recommend_price) {
        this.recommend_price = recommend_price;
    }
}
