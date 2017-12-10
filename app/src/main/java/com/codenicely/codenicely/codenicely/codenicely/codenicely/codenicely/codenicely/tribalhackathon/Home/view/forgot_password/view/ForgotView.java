package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.view;

/**
 * Created by aman on 28/6/17.
 */

public interface ForgotView {
    void showMessage(String message);

    void showProgressBar(boolean show);

    void onOtpSend();

    void onOtpVerified();

    void onPasswordChanged();

    void enable_password(boolean show);

    void enable_otp(boolean show);
}
