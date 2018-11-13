package com.example.ludanortmun.tablefinder;

import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

public class RegisterTask extends AsyncTask<RegisterTaskParameters, Void, Boolean> {
    @Override
    protected Boolean doInBackground(RegisterTaskParameters... registerTaskParameters) {
        final RegisterTaskParameters parameters = registerTaskParameters[0];
        UserProperties properties = parameters.getProperties();

        CognitoUserAttributes attributes = new CognitoUserAttributes();
        attributes.addAttribute("email", properties.getEmail());
        attributes.addAttribute("name", properties.getName());
        attributes.addAttribute("gender", properties.getGender());
        attributes.addAttribute("locale", properties.getCity());
        attributes.addAttribute("birthdate", properties.getBirthdate());
        attributes.addAttribute("custom:favorite_genre", properties.getFavoriteGenre());
        attributes.addAttribute("custom:about", properties.getAbout());

        SignUpHandler handler = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                parameters.setResult(true);
            }

            @Override
            public void onFailure(Exception exception) {
                parameters.setResult(false);
            }
        };

        parameters.getUserPool().signUp(properties.getUsername(), properties.getPassword(),attributes,null, handler);
        return parameters.getResult();
    }
}

class RegisterTaskParameters {

    private CognitoUserPool userPool;
    private UserProperties properties;
    private boolean result;

    public RegisterTaskParameters(CognitoUserPool userPool, UserProperties properties) {
        this.userPool = userPool;
        this.properties = properties;
    }

    public CognitoUserPool getUserPool() {

        return userPool;
    }

    public UserProperties getProperties() {
        return properties;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
