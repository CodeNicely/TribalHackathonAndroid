package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.provider;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.OnSchemesRecieved;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view.OnSubSchemesRecieved;

/**
 * Created by vrihas on 11/12/17.
 */

public interface SubSchemesProvider {
    void requestSubSchemeData(String access_token, boolean language,int scheme_id, OnSubSchemesRecieved onSubSchemesRecieved);
}
