package com.projects.wesse.apollo_ui.utilities;

/**
 * Created by Xander on 7/29/2017.
 */
public class MessageItem {

    private String title;
    private String description;

    public MessageItem(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
