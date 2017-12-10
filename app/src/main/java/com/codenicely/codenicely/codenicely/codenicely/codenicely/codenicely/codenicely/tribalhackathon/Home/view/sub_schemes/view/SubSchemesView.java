package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.view;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model.SchemesData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.model.SubSchemesData;

import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public interface SubSchemesView {
    void showProgressBar(boolean show);
    void setSchemeData(List<SubSchemesData> subSchemesDataList);
}
