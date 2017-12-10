package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;


//import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by meghal on 11/10/16.
 */

public class MyApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        FontsOverride.setDefaultFont(this, "DEFAULT", "fonts/nunito_light.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "fonts/nunito_light.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/nunito_light.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "fonts/nunito_light.ttf");
        FirebaseApp.initializeApp(this);

    }

//    public static String getFcm_token() {
//        try {
//            fcm_token = "fcmisnull";
//            Log.d(TAG, "Fcm is null" );
//        } catch (Exception e) {
//            e.printStackTrace();
//            fcm_token = "fcmisnull";
//        }
//        return fcm_token;
//    }

    public static Context getContext() {
        return context;
    }


}
