package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.presenter;

import android.content.pm.PackageManager;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.SplashScreenCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.SplashScreenProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.views.SplashView;


/**
 * Created by aman on 26/6/17.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter{

    private static final String LOG_TAG = "Activity";
    private SplashView splashScreenSplashView;
    private SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(SplashView splashScreenSplashView, SplashScreenProvider splashScreenProvider) {
        this.splashScreenSplashView = splashScreenSplashView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void requestSplash(String fcm,String access_token) {
        splashScreenSplashView.showProgressBar(true);

        splashScreenProvider.requestSplash(fcm, access_token,new SplashScreenCallBack() {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

                if (splashScreenData.isSuccess()) {
                    splashScreenSplashView.onVersionReceived(splashScreenData);
                    splashScreenSplashView.showProgressBar(false);
                } else {
                    //splashScreenSplashView.version_check(splashScreenData);
                    splashScreenSplashView.onFailed();
                    splashScreenSplashView.showMessage(splashScreenData.getMessage());
                    splashScreenSplashView.showProgressBar(false);
                }

            }

            @Override
            public void onFailure(String error) {
                splashScreenSplashView.showProgressBar(false);
//                splashScreenSplashView.showMessage("No Internet Connection");
                splashScreenSplashView.showDialog("No Internet Connection");

            }
        });


    }
}
