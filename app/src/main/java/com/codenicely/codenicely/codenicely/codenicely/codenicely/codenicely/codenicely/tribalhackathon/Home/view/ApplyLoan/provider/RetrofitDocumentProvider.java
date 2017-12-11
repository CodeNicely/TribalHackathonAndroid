package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.provider;

import android.content.Context;
import android.net.Uri;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.OnCompanyDataResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.api.ApplyLoanApi;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentResponse;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Urls;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils.BitmapUtils;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils.FileUtils;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils.UriUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by meghalagrawal on 30/06/17.
 */

public class RetrofitDocumentProvider implements DocumentProvider {

    private Retrofit retrofit;
	private Context context;

    public RetrofitDocumentProvider(Context context) {
		this.context = context;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES).build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
			   .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
			   .build();
    }



	@Override
	public Observable<DocumentResponse> uploadDocumentData(String access_token, int sub_scheme_document_id, Uri imageUri) throws IOException {
		ApplyLoanApi applyLoanApi = retrofit.create(ApplyLoanApi.class);

		RequestBody access_token1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), access_token);
		RequestBody sub_scheme_document_id1 =
				RequestBody.create(
						MediaType.parse("multipart/form-data"), String.valueOf(sub_scheme_document_id));

		if (imageUri != null) {
			//    File imageFile=new File(imageUri.getPath());
			File imageFile = FileUtils.BitmapToFileConverter(context, BitmapUtils.filePathToBitmapConverter(UriUtils.uriToFilePathConverter(context, imageUri)));
			RequestBody fbody = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);

			MultipartBody.Part logo =
					MultipartBody.Part.createFormData("logo", imageFile.getName(), fbody);

			return applyLoanApi.uploadDocumentData(access_token1,sub_scheme_document_id1,logo);
		}else {
			MultipartBody.Part image =null;
			return applyLoanApi.uploadDocumentData(access_token1,sub_scheme_document_id1,image);
		}
	}

	@Override
	public void requestDocumentData(String access_token, int subscheme_id, final OnCompanyDataResponse onCompanyDataResponse) {
		ApplyLoanApi applyLoanApi = retrofit.create(ApplyLoanApi.class);
		Call<DocumentData> documentDataCall = applyLoanApi.requestDocumentData(access_token,false,subscheme_id);

		documentDataCall.enqueue(new Callback<DocumentData>() {
			@Override
			public void onResponse(Call<DocumentData> call, Response<DocumentData> response) {

				onCompanyDataResponse.onSuccess(response.body());
			}

			@Override
			public void onFailure(Call<DocumentData> call, Throwable t) {
				t.printStackTrace();
				onCompanyDataResponse.onFailed(t.getMessage());
			}
		});
	}
}
