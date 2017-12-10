package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model;

import android.os.Handler;
import android.util.Log;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OnOtpResendResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OtpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.SignUpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.OtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.SignUpData;


/**
 * Created by aman on 28/6/17.
 */

public class MockSignUpProvider implements SignUpProvider {
    @Override
    public void requestOtp(String mobile, String company_name, String password, String otp1, String address, String aadhaar, final OtpCallBack otpCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("MockSignUp","OTP Send");
                otpCallBack.onSuccess(getMockOtpData());
            }
        },1000);
    }

    private OtpData getMockOtpData() {

        return new OtpData(true,"Otp Sent","");
    }

    @Override
    public void requestSignUp(String mobile, String company_name,  String password, String otp,String address,String aadhaar, final SignUpCallBack signUpCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("MockSignUp","OTP Verification");
                signUpCallBack.onSuccess(getMockSignUpData());
            }
        },2000);
    }

    @Override
    public void resendOtp(String temp_access_token, OnOtpResendResponse onOtpResendResponse) {

    }

    private SignUpData getMockSignUpData() {

        return new SignUpData(true,"OTP Verified","23jhg2312hjb12kj1h2");
    }
}
