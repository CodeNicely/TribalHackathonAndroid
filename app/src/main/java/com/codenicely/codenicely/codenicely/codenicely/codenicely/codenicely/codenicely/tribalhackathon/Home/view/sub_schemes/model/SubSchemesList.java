package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.model;

import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public class SubSchemesList {
    boolean success;
    String message;
    private List<SubSchemesData> subscheme_list;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<SubSchemesData> getGet_subSchemes() {
        return subscheme_list;
    }

    public SubSchemesList(boolean success, String message, List<SubSchemesData> get_subschemes) {

        this.success = success;
        this.message = message;
        this.subscheme_list = get_subschemes;
    }
}
