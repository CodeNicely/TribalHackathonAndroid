package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.ForgotCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.api.ForgotReqeustApi;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.data.ForgotData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
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

public class RetrofitForgotProvider implements ForgotProvider {

    private ForgotReqeustApi forgotReqeustApi;
    private Call<ForgotData> forgotDataCall;
    Retrofit retrofit;
    public RetrofitForgotProvider() {
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
        forgotReqeustApi = retrofit.create(ForgotReqeustApi.class);
    }


    @Override
    public void requestLogin(String mobile, final ForgotCallBack forgotCallBack) {

        forgotDataCall = forgotReqeustApi.requestForgot(mobile);
        forgotDataCall.enqueue(new Callback<ForgotData>() {
            @Override
            public void onResponse(Call<ForgotData> call, Response<ForgotData> response) {
                forgotCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ForgotData> call, Throwable t) {
                forgotCallBack.onFailure();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void changePassword(String mobile, String otp, String password, final ForgotCallBack forgotCallBack) {
        forgotDataCall = forgotReqeustApi.changePassword(mobile,otp,password);
        forgotDataCall.enqueue(new Callback<ForgotData>() {
            @Override
            public void onResponse(Call<ForgotData> call, Response<ForgotData> response) {
                forgotCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ForgotData> call, Throwable t) {
                forgotCallBack.onFailure();
                t.printStackTrace();
            }
        });

    }

    @Override
    public void responseOtp(String mobile, String otp, final ForgotCallBack forgotCallBack) {
        forgotDataCall = forgotReqeustApi.responseOtp(mobile,otp);
        forgotDataCall.enqueue(new Callback<ForgotData>() {
            @Override
            public void onResponse(Call<ForgotData> call, Response<ForgotData> response) {
                forgotCallBack.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ForgotData> call, Throwable t) {
                t.printStackTrace();
                forgotCallBack.onFailure();
            }
        });
    }

    @Override
    public void onDestroy() {
        forgotDataCall.cancel();
    }
}
