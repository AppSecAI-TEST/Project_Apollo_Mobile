package com.projects.wesse.apollo_ui.Attributes;

import java.io.Serializable;

/**
 * Created by Xander on 8/6/2017.
 */

public class Customer implements Serializable {
    private String name;
    private String email;
    private String tel;
    private String address;
    private String sec_address;
    private String city;
    private String province;
    private String country;

    //GETTERS & SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSec_address() {
        return sec_address;
    }

    public void setSec_address(String sec_address) {
        this.sec_address = sec_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
