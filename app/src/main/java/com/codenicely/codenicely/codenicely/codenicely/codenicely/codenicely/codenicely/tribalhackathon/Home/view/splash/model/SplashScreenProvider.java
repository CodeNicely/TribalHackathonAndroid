package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.SplashScreenCallBack;

/**
 * Created by aman on 26/6/17.
 */

public interface SplashScreenProvider {
    void requestSplash(String fcm, String access_token, SplashScreenCallBack splashScreenCallback);
}
