package com.projects.wesse.apollo_ui.Attributes;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by Xander on 8/6/2017.
 */

public class Customer implements Serializable {
    private int id;
    private String name;
    private String email;
    private String tel;
    private String address;
    private String sec_address;
    private String city;
    private String province;
    private String country;

    public Customer(int id, String name, String email, String tel, String address, String sec_address, String city, String province, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.sec_address = sec_address;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public String convertToJSONString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    //GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getSec_address() {
        return sec_address;
    }

    public String getCity() {
        return city;
    }

    public String getProvince() {
        return province;
    }

    public String getCountry() {
        return country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSec_address(String sec_address) {
        this.sec_address = sec_address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
