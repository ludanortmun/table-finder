package com.example.ludanortmun.tablefinder.tasks.auth.logout;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;

public class LogoutTask extends AsyncTask<LogoutTaskParameters, Void, Void> {
    @Override
    protected Void doInBackground(LogoutTaskParameters... logoutTaskParameters) {
        final LogoutTaskParameters parameters = logoutTaskParameters[0];
        GenericHandler handler = new GenericHandler() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure(Exception exception) {
                // Manually sign out cached user if server-side logout fails
                parameters.getUser().signOut();
            }
        };

        parameters.getUser().globalSignOut(handler);
        return null;
    }
}


