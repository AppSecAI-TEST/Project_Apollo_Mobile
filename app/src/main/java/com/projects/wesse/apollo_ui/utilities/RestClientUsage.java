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
        RestClient.get("api/customer", null);
    }
}
