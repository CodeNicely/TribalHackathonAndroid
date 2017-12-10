package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.presenter;

/**
 * Created by aman on 28/6/17.
 */

public interface SignUpPresenter {

    void requestOtp(String mobile, String company_name, String password, String refer_code, boolean isChecked);

    void requestSignUp(String mobile, String gst_in, String company_name, String email,
					   String password, String otp);

    void requestOtpResend(String temp_access_token);
}
