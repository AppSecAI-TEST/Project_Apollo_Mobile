package com.projects.wesse.apollo_ui.utilities;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by wesse on 2017/07/31.
 */

public class SessionUser implements Parcelable {

    private String JSONToken;
    private JSONObject jsonObject;

    private String name;
    private String email;

    public SessionUser() {

    }

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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(email);
        out.writeString(name);
        out.writeString(JSONToken);
    }

    public static final Parcelable.Creator<SessionUser> CREATOR
            = new Parcelable.Creator<SessionUser>() {
        public SessionUser createFromParcel(Parcel in) {
            return new SessionUser(in);
        }

        public SessionUser[] newArray(int size) {
            return new SessionUser[size];
        }
    };

    private SessionUser(Parcel in) {
        in.readString();
        in.readString();
        in.readString();
    }

}
