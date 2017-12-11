package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.presenter;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesList;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.provider.SchemesProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.OnSchemesRecieved;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.SchemesView;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.model.SubSchemesList;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.provider.SubSchemesProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view.OnSubSchemesRecieved;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view.SubSchemesView;

/**
 * Created by vrihas on 11/12/17.
 */

public class SubSchemesPresenterImpl implements SubSchemesPresenter {
    SubSchemesView subSchemesView;
    SubSchemesProvider subSchemesProvider;

    public SubSchemesPresenterImpl(SubSchemesView subSchemesView, SubSchemesProvider subSchemesProvider) {
        this.subSchemesView = subSchemesView;
        this.subSchemesProvider = subSchemesProvider;
    }

    @Override
    public void requestSchemes(String access_token, boolean language,int scheme_id) {
        subSchemesView.showProgressBar(true);
        subSchemesProvider.requestSubSchemeData(access_token, language,scheme_id, new OnSubSchemesRecieved() {
            @Override
            public void onSuccess(SubSchemesList subSchemesList) {
                subSchemesView.showProgressBar(false);
                if (subSchemesList.isSuccess()){
                    subSchemesView.setSchemeData(subSchemesList.getGet_subSchemes());
                }
            }

            @Override
            public void onFailure() {
                subSchemesView.showProgressBar(false);
            }
        });
    }
}
