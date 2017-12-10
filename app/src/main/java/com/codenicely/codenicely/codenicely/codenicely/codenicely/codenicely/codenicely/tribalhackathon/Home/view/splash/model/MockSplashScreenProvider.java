package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model;

import android.content.pm.PackageManager;
import android.os.Handler;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.SplashScreenCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;


/**
 * Created by aman on 27/6/17.
 */

public class MockSplashScreenProvider implements SplashScreenProvider {
    @Override
    public void requestSplash(String fcm, String access_token, final SplashScreenCallBack splashScreenCallback)
    {
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               try
               {
                   splashScreenCallback.onSuccess(getMockSplashScreen());
               }
               catch (PackageManager.NameNotFoundException e){
                   e.printStackTrace();
               }
           }
       },3000
       );

    }

    private SplashScreenData getMockSplashScreen() {

        SplashScreenData splashScreenData = new SplashScreenData(true,"Success",1,false);
        return splashScreenData;
    }
}
