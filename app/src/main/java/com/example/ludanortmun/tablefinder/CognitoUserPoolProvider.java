package com.example.ludanortmun.tablefinder;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoUserPoolProvider {
    private static CognitoUserPool userPool;

    public static CognitoUserPool provideCognitoUserPool(Context context) {
        if (userPool == null) {
            userPool = new CognitoUserPool(context, "",
                    "",
                    "",
                    Regions.US_WEST_2);
        }

        return userPool;
    }
}
