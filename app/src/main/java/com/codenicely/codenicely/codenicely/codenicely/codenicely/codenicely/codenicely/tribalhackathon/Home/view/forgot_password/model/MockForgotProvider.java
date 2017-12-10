package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model;

import android.os.Handler;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.ForgotCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.data.ForgotData;


/**
 * Created by aman on 29/6/17.
 */

public class MockForgotProvider implements ForgotProvider {
    @Override
    public void requestLogin(String mobile, final ForgotCallBack forgotCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                forgotCallBack.onSuccess(new ForgotData("Otp Sent",true));
            }
        },1000);
    }

    @Override
    public void changePassword(String mobile, String otp, String password, ForgotCallBack forgotCallBack) {

    }


    @Override
    public void responseOtp(String mobile, String otp,final ForgotCallBack forgotCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                forgotCallBack.onSuccess(new ForgotData("Otp Sent",true));
            }
        },1000);
    }

    @Override
    public void onDestroy() {

    }
}
