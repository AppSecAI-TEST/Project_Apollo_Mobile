package com.projects.wesse.apollo_ui.Attributes;

import java.io.Serializable;

/**
 * Created by wesse on 2017/08/08.
 */

public class Purchase implements Serializable {
    private int id;
    private String placed_at;
    private String processed_at;
    private Double total;
    private Supplier supplier;

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

    public String getProcessed_at() {
        return processed_at;
    }

    public void setProcessed_at(String processed_at) {
        this.processed_at = processed_at;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Purchase(int id, String placed_at, String processed_at, Double total, Supplier supplier) {
        this.id = id;
        this.placed_at = placed_at;
        this.processed_at = processed_at;
        this.total = total;
        this.supplier = supplier;
    }
}
