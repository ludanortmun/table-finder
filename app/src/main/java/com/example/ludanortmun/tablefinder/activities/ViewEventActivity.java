package com.example.ludanortmun.tablefinder.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ludanortmun.tablefinder.R;
import com.example.ludanortmun.tablefinder.model.Event;

public class ViewEventActivity extends Activity {
    Event event;
    TextView host;
    TextView time;
    TextView date;
    TextView location;
    TextView games;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        event = (Event) this.getIntent().getSerializableExtra("event");

        host = findViewById(R.id.eventHost);
        time = findViewById(R.id.eventTime);
        date = findViewById(R.id.eventDate);
        location = findViewById(R.id.eventLocation);
        games = findViewById(R.id.eventGames);
        description = findViewById(R.id.eventDescription);

        populateFiels();
    }

    protected void populateFiels() {
        host.setText(event.getHostName() + " (" + event.getHostId() + ")");
        time.setText(event.getTime());
        date.setText(event.getDate());
        location.setText(event.getAddress());
        games.setText(event.getGames());
        description.setText(event.getDescription());
    }
}
