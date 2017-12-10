package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.presenter;

/**
 * Created by aman on 28/6/17.
 */

public interface SignUpPresenter {

    void requestOtp(String mobile, String company_name, String password, String otp1, String address, String aadhaar, boolean isChecked);

    void requestSignUp(String mobile, String company_name,
					   String password, String otp,String address,String aadhaar);

    void requestOtpResend(String temp_access_token);
}
