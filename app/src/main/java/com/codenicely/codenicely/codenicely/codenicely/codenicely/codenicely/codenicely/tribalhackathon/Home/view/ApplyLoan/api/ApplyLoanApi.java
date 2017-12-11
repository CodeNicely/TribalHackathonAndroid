package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.api;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public interface ApplyLoanApi {

    @Multipart
    @POST(Urls.REQUEST_UPLOAD_DOCUMENTS)
    Observable<DocumentResponse> uploadDocumentData(
		  @Part("access_token") RequestBody access_token,
		  @Part("subscheme_document_id") RequestBody subscheme_document_id,
		  @Part MultipartBody.Part logo);

    @GET(Urls.REQUEST_UPLOAD_DOCUMENTS)
    Call<DocumentData> requestDocumentData(@Query("access_token") String access_token,
										   @Query("flag_lang") boolean flag_lang,
										   @Query("subscheme_id") int subscheme_id
										  );

}