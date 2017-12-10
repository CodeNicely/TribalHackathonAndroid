package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.ForgotCallBack;

/**
 * Created by aman on 28/6/17.
 */

public interface ForgotProvider {

    void requestLogin(String mobile, ForgotCallBack forgotCallBack);
    void changePassword(String mobile, String otp, String password, ForgotCallBack forgotCallBack);
    void responseOtp(String mobile, String otp, ForgotCallBack forgotCallBack);
    void onDestroy();
}

