package com.example.ludanortmun.tablefinder.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ludanortmun.tablefinder.R;
import com.example.ludanortmun.tablefinder.model.Event;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    private Context context;
    private List<Event> events;

    public EventAdapter(Context context, List<Event> events) {
        super(context, 0, events);
        this.context = context;
        this.events = events;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
        }

        Event currentEvent = events.get(position);
        TextView eventIdText = (TextView) listItem.findViewById(R.id.event_id);
        eventIdText.setText("Event by: " + currentEvent.getHostId());

        return listItem;
    }

}
