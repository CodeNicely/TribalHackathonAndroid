package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.api;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.OtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.ResendOtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.SignUpData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aman on 28/6/17.
 */

public interface SignUpRequestApi {

    @FormUrlEncoded
    @POST(Urls.REQUEST_SIGN_UP)
    Call<OtpData> requestOtp(@Field("mobile") String mobile,
                             @Field("name") String company_name,
                             @Field("password") String password,
                             @Field("address") String address,@Field("aadhaar_no") String aadhaar,
    @Field("flag_lang") Boolean lang);

    @FormUrlEncoded
    @POST(Urls.REQUEST_OTP_VERIFY)
    Call<SignUpData> requestSignUp(@Field("mobile") String mobile,
								   @Field("otp") String otp);

    @FormUrlEncoded
    @POST(Urls.REQUEST_RESEND_OTP)
    Call<ResendOtpData> requestOtpResend(@Field("temp_access_token") String temp_access_token);

    
}
