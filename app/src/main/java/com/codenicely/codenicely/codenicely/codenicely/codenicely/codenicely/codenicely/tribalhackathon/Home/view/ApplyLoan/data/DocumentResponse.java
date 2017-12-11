package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public class DocumentResponse {

    private boolean success;
    private String message;

    public DocumentResponse(boolean success, String message) {
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
