package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.LoginCallBack;

/**
 * Created by aman on 28/6/17.
 */

public interface LoginProvider {
    void requestLogin(String mobile, String password, LoginCallBack loginCallBack);
    void onDestroy();
}
