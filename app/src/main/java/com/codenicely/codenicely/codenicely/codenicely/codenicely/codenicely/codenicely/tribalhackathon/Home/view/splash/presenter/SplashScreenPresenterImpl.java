package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.presenter;

import android.content.pm.PackageManager;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.SplashScreenCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.SplashScreenProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.views.View;


/**
 * Created by aman on 26/6/17.
 */

public class SplashScreenPresenterImpl implements SplashScreenPresenter{

    private static final String LOG_TAG = "Activity";
    private View splashScreenView;
    private SplashScreenProvider splashScreenProvider;

    public SplashScreenPresenterImpl(View splashScreenView, SplashScreenProvider splashScreenProvider) {
        this.splashScreenView = splashScreenView;
        this.splashScreenProvider = splashScreenProvider;
    }

    @Override
    public void requestSplash(String fcm,String access_token) {
        splashScreenView.showProgressBar(true);

        splashScreenProvider.requestSplash(fcm, access_token,new SplashScreenCallBack() {
            @Override
            public void onSuccess(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

                if (splashScreenData.isSuccess()) {
                    splashScreenView.onVersionReceived(splashScreenData);
                    splashScreenView.showProgressBar(false);
                } else {
                    //splashScreenView.version_check(splashScreenData);
                    splashScreenView.onFailed();
                    splashScreenView.showMessage(splashScreenData.getMessage());
                    splashScreenView.showProgressBar(false);
                }

            }

            @Override
            public void onFailure(String error) {
                splashScreenView.showProgressBar(false);
//                splashScreenView.showMessage("No Internet Connection");
                splashScreenView.showDialog("No Internet Connection");

            }
        });


    }
}
