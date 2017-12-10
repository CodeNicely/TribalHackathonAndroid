package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.presenter;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesList;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.provider.SchemesProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.OnSchemesRecieved;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.SchemesView;

/**
 * Created by vrihas on 11/12/17.
 */

public class SchemesPresenterImpl implements SchemesPresenter {
    SchemesView schemesView;
    SchemesProvider schemesProvider;

    public SchemesPresenterImpl(SchemesView schemesView, SchemesProvider schemesProvider) {
        this.schemesView = schemesView;
        this.schemesProvider = schemesProvider;
    }

    @Override
    public void requestSchemes(String access_token, boolean language) {
        schemesView.showProgressBar(true);
        schemesProvider.requestSchemeData(access_token, language, new OnSchemesRecieved() {
            @Override
            public void onSuccess(SchemesList schemesList) {
                schemesView.showProgressBar(false);
                if (schemesList.isSuccess()){
                    schemesView.setSchemeData(schemesList.getGet_schemes());
                }
            }

            @Override
            public void onFailure() {
                schemesView.showProgressBar(false);
            }
        });
    }
}
