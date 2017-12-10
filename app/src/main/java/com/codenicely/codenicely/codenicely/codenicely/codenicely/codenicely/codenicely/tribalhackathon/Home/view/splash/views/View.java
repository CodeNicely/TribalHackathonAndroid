package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.views;

import android.content.pm.PackageManager;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;


/**
 * Created by aman on 26/6/17.
 */

public interface View {


    void showMessage(String message);
    void showDialog(String message);
    void showProgressBar(boolean show);

    void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException;

    void onFailed();

}
