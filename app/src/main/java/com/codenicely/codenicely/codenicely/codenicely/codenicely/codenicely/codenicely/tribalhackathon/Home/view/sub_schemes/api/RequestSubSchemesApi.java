package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.api;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.model.SubSchemesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vrihas on 11/12/17.
 */

public interface RequestSubSchemesApi {
    @GET(Urls.REQUEST_SUB_SCHEMES)
    Call<SubSchemesList> getSchemesData(@Query("access_token") String access_token, @Query("flag_lang") boolean language, @Query("scheme_id") int scheme_id);
}
