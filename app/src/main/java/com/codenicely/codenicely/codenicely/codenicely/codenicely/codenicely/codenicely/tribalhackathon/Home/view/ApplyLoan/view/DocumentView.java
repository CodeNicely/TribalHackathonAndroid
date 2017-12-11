package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.view;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public interface DocumentView {

    void showMessage(String message);
    void showLoader(boolean show);
    void onDocumentUploaded();

    void setData(DocumentData companyData);


	boolean checkPermissionForGallery();

	boolean requestGalleryPermission();

	void showGallery();
}
