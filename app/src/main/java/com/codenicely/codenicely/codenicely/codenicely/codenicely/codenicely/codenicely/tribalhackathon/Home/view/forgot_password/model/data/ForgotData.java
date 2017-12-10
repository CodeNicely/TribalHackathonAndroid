package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.data;

/**
 * Created by aman on 28/6/17.
 */

public class ForgotData {
    private String message;
    private boolean success;

    public ForgotData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
