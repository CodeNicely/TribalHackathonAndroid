package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.api;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vrihas on 11/12/17.
 */

public interface RequestSchemesApi {
    @GET(Urls.REQUEST_SCHEMES)
    Call<SchemesList> getSchemesData(@Query("access_token") String access_token,@Query("flag_lang") boolean language);
}
