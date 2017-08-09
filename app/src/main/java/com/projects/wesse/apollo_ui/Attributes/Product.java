package com.projects.wesse.apollo_ui.Attributes;

import java.io.Serializable;

/**
 * Created by Xander on 8/7/2017.
 */

public class Product implements Serializable {
    private int id;
    private String sku;
    private String description;
    private Double cost_price;
    private Double retail_price;
    private Double recommend_price;

    public Product(int id, String sku, String description, Double cost_price, Double retail_price, Double recommend_price) {
        this.id = id;
        this.sku = sku;
        this.description = description;
        this.cost_price = cost_price;
        this.retail_price = retail_price;
        this.recommend_price = recommend_price;
    }


    //GETTER AND SETTERS
    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

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

    public Double getCost_price() {
        return cost_price;
    }

    public void setCost_price(Double cost_price) {
        this.cost_price = cost_price;
    }

    public Double getRetail_price() {
        return retail_price;
    }

    public void setRetail_price(Double retail_price) {
        this.retail_price = retail_price;
    }

    public Double getRecommend_price() {
        return recommend_price;
    }

    public void setRecommend_price(Double recommend_price) {
        this.recommend_price = recommend_price;
    }
}
