package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.apply_loan.api;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.apply_loan.data.ApplyLoanData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by ujjwal on 11/12/17.
 */

public interface ApplyyLoanApi {
	@Multipart
	@POST(Urls.REQUEST_RESEND_OTP)
	Observable<ApplyLoanData> requestEditProfile(
	  @Part("access_token") RequestBody access_token,
	  @Part("name") RequestBody company_name,
	  @Part("email") RequestBody email,
	  @Part("gstin") RequestBody gst_in,
	  @Part("address") RequestBody address,
	  @Part("city") RequestBody city,
	  @Part MultipartBody.Part file
	);
}
