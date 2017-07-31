package com.projects.wesse.apollo_ui.utilities;

import org.json.JSONObject;

/**
 * Created by wesse on 2017/07/31.
 */

public class SessionUser {

    private String JSONToken;
    private JSONObject jsonObject;

    private String name;
    private String email;



    public String getJSONToken() {
        return JSONToken;
    }

    public void setJSONToken(String JSONToken) {
        this.JSONToken = JSONToken;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

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

}
