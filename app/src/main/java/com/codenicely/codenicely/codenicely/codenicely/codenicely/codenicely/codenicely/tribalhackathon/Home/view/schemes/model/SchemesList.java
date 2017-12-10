package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model;

import java.util.List;

/**
 * Created by vrihas on 11/12/17.
 */

public class SchemesList {
    boolean success;
    String message;
    private List<SchemesData> scheme_list;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<SchemesData> getGet_schemes() {
        return scheme_list;
    }

    public SchemesList(boolean success, String message, List<SchemesData> get_schemes) {

        this.success = success;
        this.message = message;
        this.scheme_list = scheme_list;
    }
}
