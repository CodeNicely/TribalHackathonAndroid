package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.provider;

import android.net.Uri;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.OnCompanyDataResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentResponse;

import java.io.IOException;

import rx.Observable;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public interface DocumentProvider {
    Observable<DocumentResponse> uploadDocumentData(String access_token,int sub_scheme_document_id,Uri imageUri) throws IOException;


    void requestDocumentData(String access_token, int subscheme_id, OnCompanyDataResponse onCompanyDataResponse);

}
