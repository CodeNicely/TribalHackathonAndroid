package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model;

import android.content.pm.PackageManager;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.SplashScreenCallBack;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.api.SplashScreenRequestApi;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;
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
 * Created by aman on 26/6/17.
 */

public class RetrofitSplashScreenProvider  implements  SplashScreenProvider{
    private static final String TAG = "RetrofitSplashScreen";
    private static final String LOG_TAG = "Activity";
    private SplashScreenRequestApi splashScreenRequestApi;

    @Override
    public void requestSplash(String fcm,String access_token, final SplashScreenCallBack splashScreenCallback) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        splashScreenRequestApi = retrofit.create(SplashScreenRequestApi.class);
        Call<SplashScreenData> call = splashScreenRequestApi.requestSplash(fcm,access_token);

        call.enqueue(new Callback<SplashScreenData>() {

            @Override
            public void onResponse(Call<SplashScreenData> call, Response<SplashScreenData> response) {

                // if(response.body().isSuccess()) {
//                    Log.d(TAG,response.body().toString());
                try {
                    splashScreenCallback.onSuccess(response.body());
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                //  }

            }

            @Override
            public void onFailure(Call<SplashScreenData> call, Throwable t) {
                t.printStackTrace();
                splashScreenCallback.onFailure("Unable to connect to api");

            }
        });


    }
}
