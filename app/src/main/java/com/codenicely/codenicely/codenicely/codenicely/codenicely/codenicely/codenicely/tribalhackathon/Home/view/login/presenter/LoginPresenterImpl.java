package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.presenter;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.LoginCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.LoginProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data.LoginData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.view.LoginView;

/**
 * Created by aman on 28/6/17.
 */

public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;
    private LoginProvider loginProvider;

    public LoginPresenterImpl(LoginView loginView, LoginProvider loginProvider) {
        this.loginView = loginView;
        this.loginProvider = loginProvider;
    }

    @Override
    public void requestLogin(final String mobile, String password) {
        loginView.disable_login(false);
        loginView.showProgressBar(true);
        loginProvider.requestLogin(mobile, password, new LoginCallBack() {
            @Override
            public void onSuccess(LoginData loginData) {
                if (loginData.isSuccess()) {

                    loginView.showProgressBar(false);
                    loginView.disable_login(true);
                    loginView.onVerified(loginData);
                } else {
                    if (loginData.getMessage().equals("Please verify OTP to login")){
						loginView.verifyOTP(mobile,loginData.getTemp_access_token());
                    }else {

                    }
                    loginView.disable_login(true);
                    loginView.showMessage(loginData.getMessage());
                    loginView.showProgressBar(false);
                }
            }

            @Override
            public void onFailure() {
                loginView.disable_login(true);
                loginView.showMessage("No Internet Connection");
                loginView.showProgressBar(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        loginProvider.onDestroy();
    }
}
