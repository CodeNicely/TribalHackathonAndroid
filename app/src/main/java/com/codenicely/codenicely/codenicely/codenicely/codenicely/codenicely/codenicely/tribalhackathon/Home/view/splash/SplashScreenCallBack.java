package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash;

import android.content.pm.PackageManager;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;


/**
 * Created by aman on 26/6/17.
 */

public interface SplashScreenCallBack {

    void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailure(String error);
}
