package com.example.ludanortmun.tablefinder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class CognitoHelper {
    private static CognitoHelper instance;

    private CognitoUserPool userPool;
    private CognitoUser currentUser;

    private CognitoHelper(Context context) {
        this.userPool = CognitoUserPoolProvider.provideCognitoUserPool(context);
        currentUser = userPool.getCurrentUser();
    }

    public static CognitoHelper getInstance(Context context) {
        if (instance == null) {
            instance = new CognitoHelper(context);
        }

        return instance;
    }

    public void signUp(UserProperties parameters, final Context context) {
        CognitoUserAttributes attributes = new CognitoUserAttributes();
        attributes.addAttribute("email", parameters.getEmail());
        attributes.addAttribute("name", parameters.getName());
        attributes.addAttribute("gender", parameters.getGender());
        attributes.addAttribute("locale", parameters.getCity());
        attributes.addAttribute("birthdate", parameters.getBirthdate());
        attributes.addAttribute("custom:favorite_genre", parameters.getFavoriteGenre());
        attributes.addAttribute("custom:about", parameters.getAbout());

        SignUpHandler signUpCallback = new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState,
                                  CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                ((Activity) context).finish();
            }

            @Override
            public void onFailure(Exception exception) {

            }
        };

        userPool.signUpInBackground(parameters.getUsername(), parameters.getPassword(),
                attributes, null, signUpCallback);
    }

    @SuppressLint("StaticFieldLeak")
    boolean signIn(final String username, final String password) {
        boolean result = false;

        SignInParameters parameters = new SignInParameters(userPool.getUser(username), username, password);
        try {
            result = new SignInTask().execute(parameters).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        currentUser = userPool.getCurrentUser();
        return result;
    }

    @SuppressLint("StaticFieldLeak")
    public boolean isLoggedIn() {

        final AuthenticationResult result = new AuthenticationResult(false);
        final AuthenticationHandler authenticationHandler = new AuthenticationHandler() {
            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                result.isValid = userSession.isValid();
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {

            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {

            }

            @Override
            public void onFailure(Exception exception) {

            }
        };

        try {
            return new AsyncTask<CognitoUser, Void, Boolean>() {

                @Override
                protected Boolean doInBackground(CognitoUser... cognitoUser) {
                    cognitoUser[0].getSession(authenticationHandler);
                    return result.isValid;
                }

            }.execute(currentUser).get();
        } catch (Exception e) {
            Log.d(TAG, "signin " + Log.getStackTraceString(e));
        }

        return false;
    }

    public void logout() {
        currentUser.signOut();
    }

    @SuppressLint("StaticFieldLeak")
    public UserProperties getUserInfo() {
        if (!isLoggedIn()) return null;


        final UserProperties params = new UserProperties();
        try {
            return new AsyncTask<CognitoUser, Void, UserProperties>() {
                @Override
                protected UserProperties doInBackground(CognitoUser... cognitoUsers) {
                    cognitoUsers[0].getDetails(new GetDetailsHandler() {
                        @Override
                        public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                            params.name = cognitoUserDetails.getAttributes().getAttributes().get("name");
                        }

                        @Override
                        public void onFailure(Exception exception) {

                        }
                    });
                    return params;
                }
            }.execute(currentUser).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


}

class AuthenticationResult {
    public boolean isValid;

    public AuthenticationResult(boolean isValid) {
        this.isValid = isValid;
    }
}