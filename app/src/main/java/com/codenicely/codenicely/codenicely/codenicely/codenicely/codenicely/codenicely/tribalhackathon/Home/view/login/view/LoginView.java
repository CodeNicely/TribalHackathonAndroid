package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.view;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data.LoginData;

/**
 * Created by aman on 28/6/17.
 */

public interface LoginView {
    void showMessage(String message);

    void showProgressBar(boolean show);

    void onVerified(LoginData loginData);

    void disable_login(boolean show);

    void verifyOTP(String mobile, String temp_access_token);
}
