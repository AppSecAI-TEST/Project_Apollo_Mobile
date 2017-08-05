package com.projects.wesse.apollo_ui.utilities;

/**
 * Created by Xander on 7/19/2017.
 */

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;


public class RestClient {
    private static final String BASE_URL = "https://apollo.lomejo.co.za/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();
    static JSONArray timeline;
    static JSONObject response;

    public static void get(String url, RequestParams params) {
        client.get(getAbsoluteUrl(url), params, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                RestClient.response = response;
                System.out.println("here now");
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) throws JSONException {
                RestClient.timeline = timeline;
                System.out.println("here");
            }
        });
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static JSONArray getTimeline() {
        return timeline;
    }

    public static JSONObject getResponse() {
        return response;
    }
}