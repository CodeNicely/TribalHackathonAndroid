package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sub_schemes.model;

/**
 * Created by vrihas on 11/12/17.
 */

public class SubSchemesData {
    private String name,url;
    private int id;

    public SubSchemesData(int id) {
        this.id = id;
    }

    public SubSchemesData(String name,String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return name;
    }

    public int getId() {
        return id;
    }
}
