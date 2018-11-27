package com.example.ludanortmun.tablefinder.utils;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.regions.Regions;

public class CognitoUserPoolProvider {
    private static CognitoUserPool userPool;
    private static Context context;

    public static CognitoUserPool provideCognitoUserPool() {
        if (userPool == null) {
            userPool = new CognitoUserPool(context, "us-west-2_cecR08Oaw",
                    "5dof0574uvf8vd9gvt1mqccted",
                    "cue034a7qd4gt0r17i0ui6b3cd6pfr825do0al6maov9t6ga92a",
                    Regions.US_WEST_2);
        }

        return userPool;
    }

    public static void setContext(Context c) {
        context = c;
    }
}
