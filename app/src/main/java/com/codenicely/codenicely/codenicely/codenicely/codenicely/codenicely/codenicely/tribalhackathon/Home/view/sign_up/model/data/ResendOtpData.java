package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data;

/**
 * Created by meghalagrawal on 14/07/17.
 */

public class ResendOtpData {

    private boolean success;
    private String message;

    public ResendOtpData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }


    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
