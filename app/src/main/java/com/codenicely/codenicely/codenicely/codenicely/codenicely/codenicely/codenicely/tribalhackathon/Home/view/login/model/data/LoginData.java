package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data;

/**
 * Created by aman on 28/6/17.
 */

public class LoginData {
    private boolean success;
    private String message;
    private String access_token;
    private String name;
    private String mobile;
    private String temp_access_token;


    public LoginData(boolean success, String message, String access_token, String name, String mobile, String temp_access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
        this.name = name;
        this.mobile = mobile;
        this.temp_access_token = temp_access_token;
    }

    public String getTemp_access_token() {
        return temp_access_token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getCompany_name() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }
}
