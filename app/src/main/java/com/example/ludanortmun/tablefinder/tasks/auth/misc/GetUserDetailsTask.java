package com.example.ludanortmun.tablefinder.tasks.auth.misc;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.example.ludanortmun.tablefinder.model.MapToUserPropertiesConverter;
import com.example.ludanortmun.tablefinder.model.UserProperties;

import java.util.Map;

public class GetUserDetailsTask extends AsyncTask<GetUserDetailsTaskParameters, Void, UserProperties> {

    @Override
    protected UserProperties doInBackground(GetUserDetailsTaskParameters... getUserDetailsTaskParameters) {
        final GetUserDetailsTaskParameters parameters = getUserDetailsTaskParameters[0];

        GetDetailsHandler handler = new GetDetailsHandler() {
            @Override
            public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                Map<String, String> attributeMap = cognitoUserDetails.getAttributes().getAttributes();
                parameters.setResult(new MapToUserPropertiesConverter().apply(attributeMap));
            }

            @Override
            public void onFailure(Exception exception) {
                parameters.setResult(null);
            }
        };

        parameters.getUser().getDetails(handler);

        return parameters.getResult();
    }
}

