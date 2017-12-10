package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesData;

import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public interface SchemesView {
    void showProgressBar(boolean show);
    void setSchemeData(List<SchemesData> schemesDataList);
}
