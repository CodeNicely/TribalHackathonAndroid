package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.presenter;

import android.net.Uri;


/**
 * Created by meghalagrawal on 30/06/17.
 */

public interface DocumentPresenter {

    void uploadDocumentData(String access_token,int subscheme_document_id, Uri imageUri);

    void requestDocumentData(String access_token, int subscheme_id);

	void openGallery();
}
