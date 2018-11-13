package com.example.ludanortmun.tablefinder;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;

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

class GetUserDetailsTaskParameters {
    private CognitoUser user;

    public UserProperties getResult() {
        return result;
    }

    public void setResult(UserProperties result) {
        this.result = result;
    }

    public CognitoUser getUser() {

        return user;
    }

    public GetUserDetailsTaskParameters(CognitoUser user) {

        this.user = user;
    }

    private UserProperties result;
}
