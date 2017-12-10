package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.presenter;

/**
 * Created by aman on 28/6/17.
 */

public interface ForgotPresenter {
    void requestForgot(String mobile);
    void responseOtp(String mobile, String otp);
    void changePassword(String mobile, String otp, String password);
    void onDestroy();
}
