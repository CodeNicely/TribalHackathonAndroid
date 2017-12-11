package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OnOtpResendResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OtpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.SignUpCallBack;

/**
 * Created by aman on 28/6/17.
 */

public interface SignUpProvider {

    void requestOtp(String mobile, String company_name, String password, String otp1, String address, String aadhaar, OtpCallBack otpCallBack);

    void requestSignUp(String mobile, String gst_in, String company_name, String email,
					   String password, String otp, SignUpCallBack signUpCallBack);

    void resendOtp(String temp_access_token, OnOtpResendResponse onOtpResendResponse);

}
