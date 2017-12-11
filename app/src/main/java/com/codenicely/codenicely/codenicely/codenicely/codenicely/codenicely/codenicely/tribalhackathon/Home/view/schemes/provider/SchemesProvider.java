package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.provider;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.OnSchemesRecieved;

/**
 * Created by vrihas on 11/12/17.
 */

public interface SchemesProvider {
    void requestSchemeData(String access_token, boolean language, OnSchemesRecieved onSchemesRecieved);
}
