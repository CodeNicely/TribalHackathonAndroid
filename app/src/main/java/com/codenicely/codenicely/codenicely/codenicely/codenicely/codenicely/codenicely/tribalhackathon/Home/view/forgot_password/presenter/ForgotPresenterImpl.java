package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.presenter;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.ForgotCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.ForgotProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.data.ForgotData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.view.ForgotView;

/**
 * Created by aman on 28/6/17.
 */

public class ForgotPresenterImpl implements ForgotPresenter {
    private ForgotProvider forgotProvider;
    private ForgotView forgotView;

    public ForgotPresenterImpl(ForgotProvider forgotProvider, ForgotView forgotView) {
        this.forgotProvider = forgotProvider;
        this.forgotView = forgotView;
    }

    @Override
    public void requestForgot(String mobile) {
        forgotView.enable_otp(false);
        forgotView.showProgressBar(true);
        forgotProvider.requestLogin(mobile, new ForgotCallBack() {
            @Override
            public void onSuccess(ForgotData forgotData) {
                if (forgotData.isSuccess()){

                    forgotView.onOtpSend();
                    forgotView.showProgressBar(false);
                }else {

                    forgotView.showMessage(forgotData.getMessage());
                    forgotView.showProgressBar(false);
                    forgotView.enable_otp(true);
                }
            }

            @Override
            public void onFailure() {

                forgotView.showMessage("No Internet Connection");
                forgotView.showProgressBar(false);
                forgotView.enable_otp(true);
            }
        });
    }

    @Override
    public void responseOtp(String mobile, String otp) {
        forgotView.enable_password(false);
        forgotView.showProgressBar(true);
        forgotProvider.responseOtp(mobile, otp, new ForgotCallBack() {
            @Override
            public void onSuccess(ForgotData forgotData) {


                if (forgotData.isSuccess()){



                    forgotView.onOtpVerified();
                    forgotView.showMessage(forgotData.getMessage());
                    forgotView.showProgressBar(false);
                }else {
                    forgotView.showMessage(forgotData.getMessage());
                    forgotView.showProgressBar(false);
                    forgotView.enable_password(true);
                }
            }

            @Override
            public void onFailure() {
                forgotView.showMessage("Poor Internet Connection ! Try Again");
                forgotView.showProgressBar(false);
                forgotView.enable_password(true);
            }
        });
    }

    @Override
    public void changePassword(String mobile, String otp, String password) {
        forgotView.showProgressBar(true);
        forgotProvider.changePassword(mobile,otp, password, new ForgotCallBack() {
            @Override
            public void onSuccess(ForgotData forgotData) {
                if (forgotData.isSuccess()){


                    forgotView.onPasswordChanged();
                    forgotView.showMessage(forgotData.getMessage());
                    forgotView.showProgressBar(false);
                }else {

                    forgotView.showMessage(forgotData.getMessage());
                    forgotView.showProgressBar(false);
                }
            }

            @Override
            public void onFailure() {
                forgotView.showMessage("Poor Internet Connection ! Try Again");
                forgotView.showProgressBar(false);
                }
        });
    }

    @Override
    public void onDestroy() {
        forgotProvider.onDestroy();
    }
}
