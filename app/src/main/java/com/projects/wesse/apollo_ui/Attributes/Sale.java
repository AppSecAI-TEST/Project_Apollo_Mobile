package com.projects.wesse.apollo_ui.Attributes;

import java.io.Serializable;

/**
 * Created by wesse on 2017/08/08.
 */

public class Sale implements Serializable {
    private int id;
    private String placed_at;
    private Double total;
    private Customer customer;

    public Sale(int id, String placed_at, double total, Customer customer) {

        this.id = id;
        this.placed_at = placed_at;
        this.total = total;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaced_at() {
        return placed_at;
    }

    public void setPlaced_at(String placed_at) {
        this.placed_at = placed_at;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
