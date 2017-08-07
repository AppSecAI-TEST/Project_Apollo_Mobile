package com.projects.wesse.apollo_ui.utilities;

import com.projects.wesse.apollo_ui.ui_activities.LoginActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.client.methods.HttpDelete;
import cz.msebera.android.httpclient.client.methods.HttpPatch;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

/**
 * Created by wesse on 2017/07/29.
 */

public class NewRESTClient {
    private static final String BASE_URL = "https://apollo.lomejo.co.za/api/";
    private static final HttpClient httpclient = HttpClientBuilder.create().build();

    public static HttpResponse post(List<NameValuePair> nvps, String url) throws IOException {
        HttpPost httpPost = new HttpPost(BASE_URL + url);
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        return httpclient.execute(httpPost);
    }
    public static HttpResponse get(String url, String JSONAuthToken) throws IOException {
        HttpGet httpGet = new HttpGet(BASE_URL + url);
        httpGet.addHeader("Authorization", "Bearer " + JSONAuthToken);
        return httpclient.execute(httpGet);
    }

    public static HttpResponse delete(String url, int resourceID, String JSONAuthToken) throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpDelete httpDel = new HttpDelete(BASE_URL + url + "/" + resourceID);
        httpDel.addHeader("Authorization", "Bearer " + JSONAuthToken);
        return httpclient.execute(httpDel);
    }

    public static HttpResponse patch(String url, int resourceID, String JSONAuthToken) throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPatch httpPatch = new HttpPatch(BASE_URL + url + "/" + resourceID);
        httpPatch.addHeader("Authorization", "Bearer " + JSONAuthToken);
        return httpclient.execute(httpPatch);
    }

    public static String retrieveResource(String resourceToGet) {
        InputStream inputStream;
        String result = "init";
        try {
            inputStream = NewRESTClient.get(resourceToGet, LoginActivity.getUser().getJSONToken()).getEntity().getContent();
            try {
                if(inputStream != null) {
                    result = convertInputStreamToString(inputStream);
                }
                else
                    result = "Could not retrieve " + resourceToGet;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject retrieveIndividualResource(String resourceToGet, int resourceID) {
        InputStream inputStream;
        JSONObject jsonRet = null;
        String jsonString;
        try {
            inputStream = NewRESTClient.get(resourceToGet + "/" + resourceID, LoginActivity.getUser().getJSONToken()).getEntity().getContent();
            try {
                if(inputStream != null) {
                    jsonString = convertInputStreamToString(inputStream);
                    jsonRet = new JSONObject(jsonString);
                }
                else {
                    jsonString = null;
                    jsonRet = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonRet;
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader br;
        StringBuilder sb;
        sb = new StringBuilder();
        String line;
        br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

}
