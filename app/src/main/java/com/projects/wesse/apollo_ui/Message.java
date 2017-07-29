package com.projects.wesse.apollo_ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.projects.wesse.apollo_ui.utilities.BaseActivity;
import com.projects.wesse.apollo_ui.utilities.MessageAdapter;
import com.projects.wesse.apollo_ui.utilities.MessageItem;

import java.util.ArrayList;

/**
 * Created by Xander on 7/17/2017.
 */

public class Message extends BaseActivity
{

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<MessageItem> listItems = new ArrayList<>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    String api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        super.onCreateDrawer();

        //list of messages
        ListView lv = (ListView)findViewById(R.id.msgview);
        MessageAdapter myarrayAdapter = new MessageAdapter(this, listItems);
        lv.setAdapter(myarrayAdapter);
        lv.setTextFilterEnabled(true);


        listItems.add(new MessageItem("Peter","Peters message"));
        listItems.add(new MessageItem("Sarah","Sarah's message"));

        myarrayAdapter.notifyDataSetChanged();






        //API


//        try {
//            URL apolloEndpoint = new URL("https://api.github.com/");
//            HttpsURLConnection myConnection =
//                    (HttpsURLConnection) apolloEndpoint.openConnection();
//            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
//            if (myConnection.getResponseCode() == 200) {
//                InputStream responseBody = myConnection.getInputStream();
//                InputStreamReader responseBodyReader =
//                        new InputStreamReader(responseBody, "UTF-8");
//                JsonReader jsonReader = new JsonReader(responseBodyReader);
//
//                jsonReader.beginObject(); // Start processing the JSON object
//                while (jsonReader.hasNext()) { // Loop through all keys
//                    String key = jsonReader.nextName(); // Fetch the next key
//                    if (key.equals("organization_url")) { // Check if desired key
//                        // Fetch the value as a String
//                        String value = jsonReader.nextString();
//
//                        // Do something with the value
//                        // ...
//
//                        break; // Break out of the loop
//                    } else {
//                        jsonReader.skipValue(); // Skip values of other keys
//                    }
//                }
//
//                jsonReader.close();
//                myConnection.disconnect();
//            } else {
//                // Error handling code goes here
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                // All your networking logic
//                // should be here
//            }
//        });


    }




}
