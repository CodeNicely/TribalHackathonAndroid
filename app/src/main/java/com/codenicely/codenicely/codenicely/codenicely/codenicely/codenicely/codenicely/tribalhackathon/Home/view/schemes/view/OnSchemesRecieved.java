package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesList;

/**
 * Created by vrihas on 11/12/17.
 */

public interface OnSchemesRecieved {
    void onSuccess(SchemesList schemesList);
    void onFailure();
}
