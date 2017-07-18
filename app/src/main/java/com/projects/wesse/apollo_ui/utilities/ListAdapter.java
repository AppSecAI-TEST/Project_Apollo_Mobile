package com.projects.wesse.apollo_ui.utilities;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.R;

import java.util.ArrayList;

/**
 * Created by wesse on 2017/07/18.
 */

public class ListAdapter extends ArrayAdapter<String> {

    public ListAdapter(Context context, ArrayList<String> values) {
        super(context, R.layout.list_view_row_layout, values);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.list_view_row_layout, parent, false);
        String product = getItem(position);
        TextView theTextView = (TextView) theView.findViewById(R.id.textView1);
        theTextView.setText(product);
        ImageView theImageView = (ImageView) theView.findViewById(R.id.dotView);
        theImageView.setImageResource(R.drawable.purple_dot);
        return theView;
    }
}