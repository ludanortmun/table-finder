package com.example.ludanortmun.tablefinder.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ludanortmun.tablefinder.R;
import com.example.ludanortmun.tablefinder.model.Event;
import com.example.ludanortmun.tablefinder.utils.DynamoHelper;
import com.example.ludanortmun.tablefinder.utils.EventAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewAllEventsActivity extends Activity {
    ListView listView;
    EventAdapter adapter;
    List<Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_events);

        events = new ArrayList<>();
        listView = findViewById(R.id.event_list);

        adapter = new EventAdapter(this, events);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = adapter.getItem(position);
                Intent viewEventActivity = new Intent(getApplicationContext(), ViewEventActivity.class);
                viewEventActivity.putExtra("event", event);
                startActivity(viewEventActivity);
            }
        });

        // Run Task to get events
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<Event> events = DynamoHelper.getAllEvents();
                populateEvents(events);
                return null;
            }
        }.execute();
    }

    void populateEvents(final List<Event> newEvents) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                adapter.addAll(newEvents);
            }
        });
    }
}
