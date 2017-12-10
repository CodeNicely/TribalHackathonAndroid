package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data;

/**
 * Created by aman on 28/6/17.
 */

public class LoginData {
    private boolean success;
    private String message;
    private String access_token;
    private String company_name;
    private String mobile;
    private String gst_in;
    private String email;
    private String temp_access_token;


    public LoginData(boolean success, String message, String access_token, String company_name, String mobile, String gst_in, String email, String temp_access_token) {
        this.success = success;
        this.message = message;
        this.access_token = access_token;
        this.company_name = company_name;
        this.mobile = mobile;
        this.gst_in = gst_in;
        this.email = email;
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
        return company_name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getGst_in() {
        return gst_in;
    }

    public String getEmail() {
        return email;
    }
}
