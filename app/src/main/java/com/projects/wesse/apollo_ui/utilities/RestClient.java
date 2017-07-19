package com.projects.wesse.apollo_ui.utilities;

/**
 * Created by Xander on 7/19/2017.
 */

import com.loopj.android.http.*;


public class RestClient {
    private static final String BASE_URL = "https://apollo.co.za/api/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}