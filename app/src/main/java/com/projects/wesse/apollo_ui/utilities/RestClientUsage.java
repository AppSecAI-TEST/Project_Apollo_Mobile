package com.projects.wesse.apollo_ui.utilities;

/**
 * Created by Xander on 7/19/2017.
 */
import org.json.*;
import com.loopj.android.http.*;
import com.projects.wesse.apollo_ui.utilities.RestClient;

import cz.msebera.android.httpclient.entity.mime.Header;

public class RestClientUsage {

    public void getPublicTimeline() throws JSONException {
        RestClient.get("api/customer", null, new JsonHttpResponseHandler() {

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) throws JSONException {
                // Pull out the first event on the public timeline
                Object firstEvent = timeline.get(0);
                String text = firstEvent.toString();

                // Do something with the response
                System.out.println(text);
            }
        });
    }
}
