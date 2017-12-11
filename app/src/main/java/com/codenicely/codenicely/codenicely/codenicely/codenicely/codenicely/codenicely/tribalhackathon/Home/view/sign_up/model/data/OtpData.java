package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data;

/**
 * Created by aman on 28/6/17.
 */

public class OtpData {

    private boolean success;
    private String message;
    private String temp_access_token;


	public OtpData(boolean success, String message, String temp_access_token) {
		this.success = success;
		this.message = message;
		this.temp_access_token = temp_access_token;
	}


	public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getTemp_access_token() {
        return temp_access_token;
    }
}
