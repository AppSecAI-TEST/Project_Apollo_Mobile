package com.projects.wesse.apollo_ui.utilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wesse on 2017/07/31.
 */

public class SessionUser{

    private static String jsonToken;

    private static String name;
    private String email;
    private static String role;
    private static String[] abilities;

    public SessionUser(JSONObject json) {
     try
     {
         name = (String) json.getJSONObject("data").getJSONObject("user").get("name");
         email = (String) json.getJSONObject("data").getJSONObject("user").get("email");
         jsonToken = (String) json.getJSONObject("meta").get("token");
         role = (String) json.getJSONObject("data").get("role");

         JSONArray abilityArray = (JSONArray)  json.getJSONObject("data").get("abilities");
         String splittable = abilityArray.toString();
         abilities = splittable.split(",");

     } catch (JSONException e) { e.printStackTrace();}
    }

    private String abilityArrayToString(){
        String ret = "";
        for(int i = 0; i < abilities.length; i++)
            ret += abilities[i] + " || ";
        return ret;
    }

    public static void logoutAction(){
		jsonToken = null;
		name = null;
		role = null;
		abilities  = null;
	}

    @Override
    public String toString() {
        return "NAME: " + name + "\n" +
                "EMAIL: " + email + "\n" +
                "ROLE: " + role + "\n" +
                "ABILITIES: " + abilityArrayToString() + "\n" +
                "TOKEN: " + jsonToken + "\n";
    }

    public String getJSONToken() {
        return jsonToken;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static String getRole() {
        return role;
    }

    public String getAbility(int index) { return abilities[index]; }
}
