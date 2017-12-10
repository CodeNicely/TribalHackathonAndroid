package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.presenter;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OnOtpResendResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OtpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.SignUpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.SignUpProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.OtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.ResendOtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.SignUpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.view.SignUpView;

/**
 * Created by aman on 28/6/17.
 */

public class SignUpPreseterImpl implements SignUpPresenter {


    private SignUpView signUpView;
    private SignUpProvider signUpProvider;

    public SignUpPreseterImpl(SignUpView signUpView, SignUpProvider signUpProvider) {
        this.signUpView = signUpView;
        this.signUpProvider = signUpProvider;
    }

    @Override
    public void requestOtp(String mobile, String company_name, String password, String refer_code, final boolean isChecked) {
        signUpView.enable_signUp(false);
        signUpView.showProgressBar(true);
        signUpProvider.requestOtp(mobile, company_name, password, refer_code,new OtpCallBack() {
            @Override
            public void onSuccess(OtpData otpData) {
                if(otpData.isSuccess()){
					if ((otpData.isRcCorrect()) && isChecked){
						signUpView.showMessage("Incorrect Referral Code");
					}else {
						signUpView.onOtpSend(otpData);
					}
                    signUpView.enable_signUp(true);
                    signUpView.showProgressBar(false);
                }else{
					signUpView.showMessage(otpData.getMessage());
                    signUpView.enable_signUp(true);
                    signUpView.showProgressBar(false);
                }
            }
            @Override
            public void onFailure() {
                signUpView.showMessage("Poor Internet Connection");
                signUpView.enable_signUp(true);
                signUpView.showProgressBar(false);
            }
        });
    }

    @Override
    public void requestSignUp(String mobile, final String gst_in, String company_name, String email,
                              String password, String otp) {
        signUpView.enable_otp(false);
        signUpView.showProgressBar(true);
        signUpProvider.requestSignUp(mobile, gst_in, company_name, email, password, otp,
                new SignUpCallBack() {
            @Override
            public void onSuccess(SignUpData signUpData) {
                if(signUpData.isSuccess()){
                    signUpView.onOtpVerified(signUpData.getAccess_token());
                    signUpView.enable_otp(false);
                    signUpView.showProgressBar(false);
                    signUpView.showMessage(signUpData.getMessage());
                }else{
                    signUpView.showMessage(signUpData.getMessage());
                    signUpView.enable_otp(true);
                    signUpView.showProgressBar(false);
                }


            }

            @Override
            public void onFailure() {
                signUpView.showMessage("Poor/No Internet Connection");
                signUpView.enable_otp(true);
                signUpView.showProgressBar(false);
            }
        });

    }

    @Override
    public void requestOtpResend(String temp_access_token) {
        signUpView.showProgressBar(true);
        signUpProvider.resendOtp(temp_access_token, new OnOtpResendResponse() {
            @Override
            public void onSuccess(ResendOtpData resendOtpData) {
                if(resendOtpData.isSuccess()){

                    signUpView.showMessage("Otp Resent");


                }else{

                    signUpView.showMessage(resendOtpData.getMessage());


                }

                signUpView.showProgressBar(false);
            }

            @Override
            public void onFailed(String message) {

                signUpView.showMessage(message);

                signUpView.showProgressBar(false);



            }
        });


    }
}
