package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.provider;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.api.RequestSchemesApi;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesList;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.OnSchemesRecieved;
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
 * Created by vrihas on 11/12/17.
 */

public class RetrofitSchemesProvider implements SchemesProvider{
    private Retrofit retrofit;
    private RequestSchemesApi requestSchemesApi;
    Call<SchemesList> call;

    @Override
    public void requestSchemeData(String access_token, boolean language, final OnSchemesRecieved onSchemesRecieved) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                                      .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();
        retrofit = new Retrofit.Builder()
//                .baseUrl("http://server.gstinvoicemaker.com:8000/")
                           .baseUrl(Urls.BASE_URL)
                           .client(client)
                           .addConverterFactory(GsonConverterFactory.create(gson))
                           .build();

        requestSchemesApi = retrofit.create(RequestSchemesApi.class);
        call = requestSchemesApi.getSchemesData(access_token, language);
        call.enqueue(new Callback<SchemesList>() {
            @Override
            public void onResponse(Call<SchemesList> call, Response<SchemesList> response) {
                onSchemesRecieved.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<SchemesList> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
