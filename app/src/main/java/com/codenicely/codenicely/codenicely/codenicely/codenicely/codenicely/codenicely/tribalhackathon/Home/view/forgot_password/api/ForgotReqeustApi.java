package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.api;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.data.ForgotData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by aman on 28/6/17.
 */

public interface ForgotReqeustApi {

    @GET(Urls.REQUEST_FORGOT)
    Call<ForgotData> requestForgot(@Query("mobile") String mobile);

    @FormUrlEncoded
    @POST(Urls.REQUEST_OTP_VERIFY)
    Call<ForgotData> responseOtp(@Field("mobile") String mobile, @Field("otp") String otp);

    @FormUrlEncoded
    @POST(Urls.REQUEST_FORGOT)
    Call<ForgotData> changePassword(@Field("mobile") String mobile, @Field("otp") String otp, @Field("password") String password);

}
