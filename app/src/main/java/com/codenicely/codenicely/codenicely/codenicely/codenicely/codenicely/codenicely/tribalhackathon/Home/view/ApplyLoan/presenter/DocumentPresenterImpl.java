package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.presenter;

import android.net.Uri;
import android.util.Log;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.OnCompanyDataResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.provider.DocumentProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.view.DocumentView;

import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public class DocumentPresenterImpl implements DocumentPresenter {

    private DocumentView documentView;
    private DocumentProvider documentProvider;
    private Observable<DocumentResponse> editProfileDataObservable;
    private static final String TAG = "EditCompanyProfileImpl";
    private Subscription subscription;


    public DocumentPresenterImpl(DocumentView documentView, DocumentProvider documentProvider) {
        this.documentView = documentView;
        this.documentProvider = documentProvider;
    }


    @Override
    public void uploadDocumentData(String access_token, int subscheme_document_id, Uri imageUri) {
        documentView.showLoader(true);
        try {
            editProfileDataObservable = documentProvider.uploadDocumentData(access_token,subscheme_document_id, imageUri);
            Log.i(TAG, "Value of Observable" + editProfileDataObservable.toString());
            subscription = editProfileDataObservable.subscribeOn(Schedulers.newThread()).
                                                                                                observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DocumentResponse>() {


                @Override
                public void onCompleted() {

                }
                @Override
                public void onError(Throwable e) {

                    documentView.showLoader(false);
                    documentView.showMessage("Unable to connect to server");
                    e.printStackTrace();
                }


                @Override
                public void onNext(DocumentResponse documentResponse) {

                    Log.i(TAG, "CityData " + documentResponse.toString());
                    documentView.showLoader(false);
                    if (documentResponse.isSuccess()) {
                        documentView.showMessage(documentResponse.getMessage());
                        documentView.onDocumentUploaded();
                    } else {
                        documentView.showMessage(documentResponse.getMessage());
                    }
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void requestDocumentData(String access_token, int subscheme_id) {
        documentView.showLoader(true);
        documentProvider.requestDocumentData(access_token, subscheme_id,new OnCompanyDataResponse() {
            @Override
            public void onSuccess(DocumentData documentData) {
                if (documentData != null) {
                    if (documentData.isSuccess()) {
                        documentView.setData(documentData);
                    } else {
                        documentView.showMessage(documentData.getMessage());
                    }


                } else {
                    documentView.showMessage("Something went wrong, Please try again");

                }
                documentView.showLoader(false);
            }

            @Override
            public void onFailed(String message) {
                documentView.showMessage(message);
                documentView.showLoader(false);

            }
        });
    }

    @Override
    public void openGallery() {
        if (documentView.checkPermissionForGallery()) {

            documentView.showGallery();
        } else {

            if (documentView.requestGalleryPermission()) {
                documentView.showGallery();
            }
        }
    }
}
