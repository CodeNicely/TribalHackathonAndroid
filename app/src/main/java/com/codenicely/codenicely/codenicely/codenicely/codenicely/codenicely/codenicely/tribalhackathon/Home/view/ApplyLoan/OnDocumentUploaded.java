package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentResponse;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public interface OnDocumentUploaded {

    void onSuccess(DocumentResponse editProfileData);

    void onFailed(String message);

}
