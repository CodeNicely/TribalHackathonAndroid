package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.model;

/**
 * Created by vrihas on 11/12/17.
 */

public class SchemesData {
    private String name;
    private int id;

    public SchemesData(int id) {
        this.id = id;
    }

    public SchemesData(String name) {
        this.name = name;
    }

    public String getTitle() {
        return name;
    }

    public int getId() {
        return id;
    }
}
