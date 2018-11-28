package com.example.ludanortmun.tablefinder.tasks.auth.login;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.example.ludanortmun.tablefinder.utils.CognitoHelper;
import com.example.ludanortmun.tablefinder.utils.CognitoUserPoolProvider;
import com.example.ludanortmun.tablefinder.utils.DynamoDBProvider;

import java.util.HashMap;
import java.util.Map;

public class LoginTask extends AsyncTask<LoginTaskParameters, Void, Boolean> {
    @Override
    protected Boolean doInBackground(LoginTaskParameters... loginTaskParameters) {
        final LoginTaskParameters parameters = loginTaskParameters[0];

        AuthenticationHandler handler = new AuthenticationHandler() {
            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                parameters.setResult(true);

                String idToken = userSession.getIdToken().getJWTToken();
                Map<String, String> logins = new HashMap<String, String>();
                logins.put("cognito-idp.us-west-2.amazonaws.com/us-west-2_cecR08Oaw", userSession.getIdToken().getJWTToken());
                parameters.getCredentialsProvider().setLogins(logins);
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
                AuthenticationDetails authDetails = new AuthenticationDetails(parameters.getUsername(), parameters.getPassword(), null);
                authenticationContinuation.setAuthenticationDetails(authDetails);
                authenticationContinuation.continueTask();
            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {

            }

            @Override
            public void onFailure(Exception exception) {
                parameters.setResult(false);
            }
        };

        parameters.getUser().getSession(handler);

        return parameters.getResult();
    }
}


