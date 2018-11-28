package com.example.ludanortmun.tablefinder.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ludanortmun.tablefinder.R;
import com.example.ludanortmun.tablefinder.model.Event;
import com.example.ludanortmun.tablefinder.model.UserProperties;
import com.example.ludanortmun.tablefinder.utils.CognitoHelper;
import com.example.ludanortmun.tablefinder.utils.DynamoHelper;

import java.util.UUID;

public class CreateEventActivity extends Activity {
    TextView host;
    TextView time;
    TextView date;
    TextView location;
    TextView games;
    TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        time = findViewById(R.id.time_edit_text);
        date = findViewById(R.id.date_edit_text);
        location = findViewById(R.id.location_edit_text);
        games = findViewById(R.id.games_edit_text);
        description = findViewById(R.id.description_edit_text);
    }

    protected void onCreateClick(View view) {
        UserProperties user = CognitoHelper.getInstance().getUserInfo();
        final Event event = new Event(UUID.randomUUID().toString(),
                user.getName(),
                user.getUsername(),
                location.getText().toString(),
                time.getText().toString(),
                date.getText().toString(),
                description.getText().toString(),
                games.getText().toString());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DynamoHelper.saveEvent(event);
                endActivity();
                return null;
            }
        }.execute();
    }

    void endActivity() {
        finish();
    }
}
