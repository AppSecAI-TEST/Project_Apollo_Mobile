package com.projects.wesse.apollo_ui.utilities;


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

    public static HttpResponse delete(String url, String JSONAuthToken) throws IOException {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpDelete httpDel = new HttpDelete(BASE_URL + url);
        httpDel.addHeader("Authorization", "Bearer " + JSONAuthToken);
        return httpclient.execute(httpDel);
    }

    public static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

}
