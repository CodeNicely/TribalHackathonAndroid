package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OnOtpResendResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.OtpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.SignUpCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.api.SignUpRequestApi;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.OtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.ResendOtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.SignUpData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aman on 28/6/17.
 */

public class RetrofitSignUpProvider implements SignUpProvider {

    private SignUpRequestApi signUpRequestApi;
    Retrofit retrofit;

    public RetrofitSignUpProvider() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        signUpRequestApi = retrofit.create(SignUpRequestApi.class);
    }

    @Override
    public void requestOtp(String mobile, String company_name, String password, String refer_code, final OtpCallBack otpCallBack) {
        Call<OtpData> otpDataCall;

        otpDataCall= signUpRequestApi.requestOtp(mobile, company_name, password,refer_code);
        otpDataCall.enqueue(new Callback<OtpData>() {
            @Override
            public void onResponse(Call<OtpData> call, Response<OtpData> response) {
                otpCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<OtpData> call, Throwable t) {
                t.printStackTrace();;
                otpCallBack.onFailure();
            }
        });
    }

    @Override
    public void requestSignUp(String mobile, String gst_in, String company_name, String email,
                              String password, String otp, final SignUpCallBack signUpCallBack) {

        Call<SignUpData> signUpDataCall;

        signUpDataCall = signUpRequestApi.requestSignUp(mobile,gst_in,company_name,email,password,
                otp);
        signUpDataCall.enqueue(new Callback<SignUpData>() {
            @Override
            public void onResponse(Call<SignUpData> call, Response<SignUpData> response) {
                signUpCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SignUpData> call, Throwable t) {
                t.printStackTrace();
                signUpCallBack.onFailure();
            }
        });

    }

    @Override
    public void resendOtp(String temp_access_token, final OnOtpResendResponse onOtpResendResponse) {

        Call<ResendOtpData> call= signUpRequestApi.requestOtpResend(temp_access_token);

        call.enqueue(new Callback<ResendOtpData>() {
            @Override
            public void onResponse(Call<ResendOtpData> call, Response<ResendOtpData> response) {

                onOtpResendResponse.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResendOtpData> call, Throwable t) {

                t.printStackTrace();
                onOtpResendResponse.onFailed(t.getMessage());
            }
        });

    }
}
