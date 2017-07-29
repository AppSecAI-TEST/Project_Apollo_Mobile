package com.projects.wesse.apollo_ui.utilities;

/**
 * Created by Xander on 7/29/2017.
 */
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;

public class MessageAdapter extends ArrayAdapter<MessageItem> {

    private final Context context;
    private final ArrayList<MessageItem> itemsArrayList;

    public MessageAdapter(Context context, ArrayList<MessageItem> itemsArrayList) {

        super(context, R.layout.message_item, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.message_item, parent, false);

        TextView msg_send = (TextView) rowView.findViewById(R.id.message_sender);
        TextView msg_desc = (TextView) rowView.findViewById(R.id.message_description);

        msg_send.setText(itemsArrayList.get(position).getTitle());
        msg_desc.setText(itemsArrayList.get(position).getDescription());

        return rowView;
    }

}
