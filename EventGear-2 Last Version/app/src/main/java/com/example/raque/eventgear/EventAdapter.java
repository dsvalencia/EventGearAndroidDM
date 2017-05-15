package com.example.raque.eventgear;

/**
 * Created by Sanchez on 05/01/17.
 */
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class EventAdapter {


    private Context context;
    private List<Evento> items;

    public EventAdapter(Context context, List<Evento> items) {
        this.context = context;
        this.items = items;
    }


    public int getCount() {
        return this.items.size();
    }


    public Object getItem(int position) {
        return this.items.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // Set data into the view.


        TextView evName = (TextView) rowView.findViewById(R.id.list_item_name);

        Evento item = this.items.get(position);
        //tvTitle.setText(item.getTitle());


        return rowView;
    }
}
