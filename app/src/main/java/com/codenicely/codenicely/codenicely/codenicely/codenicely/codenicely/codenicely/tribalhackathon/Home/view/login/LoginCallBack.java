package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data.LoginData;

/**
 * Created by aman on 28/6/17.
 */

public interface LoginCallBack {
    void onSuccess(LoginData loginData);
    void onFailure();
}
