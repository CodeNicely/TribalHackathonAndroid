package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model;

import android.os.Handler;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.LoginCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data.LoginData;


/**
 * Created by aman on 28/6/17.
 */

public class MockLoginProvider  implements LoginProvider{

    @Override
    public void requestLogin(String mobile, String password, final LoginCallBack loginCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loginCallBack.onSuccess(getMockLoginData());
            }
        },1000);
    }

    private LoginData getMockLoginData() {
        return new LoginData(true,"Success","1232hh1jb3hj1bh1k2h3jk","","","","","");
    }

    @Override
    public void onDestroy() {

    }
}
