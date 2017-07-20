package com.projects.wesse.apollo_ui.utilities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projects.wesse.apollo_ui.CustomerView;
import com.projects.wesse.apollo_ui.Customers;
import com.projects.wesse.apollo_ui.ProductView;
import com.projects.wesse.apollo_ui.R;

import java.util.ArrayList;

/**
 * Created by Xander on 7/19/2017.
 */

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;



    public CustomAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_row_layout, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.txt_item);
        listItemText.setText(list.get(position));

        //Handle buttons and add onClickListeners
        Button viewBtn = (Button)view.findViewById(R.id.btn_view);
        Button editBtn = (Button)view.findViewById(R.id.btn_edit);

        viewBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(CurrentLayout.getLayout())
                {
                    case "CustomerView":
                        v.getContext().startActivity(new Intent(v.getContext(),CustomerView.class).putExtra("ID", list.get(position)));
                        break;
                    case "ProductView":
                        v.getContext().startActivity(new Intent(v.getContext(),ProductView.class).putExtra("ID", list.get(position)));

                        break;
                }

                if(CurrentLayout.getLayout().equals("CustomerView"))
                {
                }

            }
        });

        editBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //do something
                notifyDataSetChanged();
            }
        });

        return view;
    }
}
