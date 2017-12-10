package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.view;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.OtpData;

/**
 * Created by aman on 28/6/17.
 */

public interface SignUpView {

    void showMessage(String message);

    void showProgressBar(boolean show);

    void onOtpSend(OtpData otpData);

    void onOtpVerified(String access_token);

    void enable_signUp(boolean show);

    void enable_otp(boolean show);

}
