package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.ResendOtpData;

/**
 * Created by meghalagrawal on 14/07/17.
 */

public interface OnOtpResendResponse {

    void onSuccess(ResendOtpData resendOtpData);

    void onFailed(String message);

}
