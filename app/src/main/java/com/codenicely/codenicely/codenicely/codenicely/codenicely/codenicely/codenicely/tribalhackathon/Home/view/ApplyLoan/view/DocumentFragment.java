package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.data.DocumentData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.presenter.DocumentPresenter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.presenter.DocumentPresenterImpl;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.ApplyLoan.provider.RetrofitDocumentProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Toaster;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.image_loader.ImageLoader;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.schemes.view.SchemesAdapter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DocumentFragment} interface
 * to handle interaction events.
 * Use the {@link DocumentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DocumentFragment extends DialogFragment implements DocumentView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
	private boolean GALLERY_REQUEST = false;
	private final int GALLERY_REQUEST_ID = 1;
	private Uri imageUri = null;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.proceed)
    Button proceed;

	@BindView(R.id.toolbar)
	Toolbar toolbar;
	private Bitmap bitmap;



	private SharedPrefs sharedPrefs;
    private DocumentPresenter documentPresenter;
    private Context context;

	@BindView(R.id.recycler_schemes)
	RecyclerView document_recycler;

	ProgressBar document_progressBar;
	LinearLayoutManager linearLayoutManager;
	DocumentsAdapter adapter;

    public DocumentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DocumentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DocumentFragment newInstance(String param1, String param2) {
        DocumentFragment fragment = new DocumentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		View view  = inflater.inflate(R.layout.fragment_document, container, false);
		ButterKnife.bind(this,view);
		sharedPrefs = new SharedPrefs(getContext());
		document_recycler.setHasFixedSize(true);
		linearLayoutManager = new LinearLayoutManager(getContext());
		document_recycler.setLayoutManager(linearLayoutManager);
		adapter = new DocumentsAdapter(getContext());
		document_recycler.setAdapter(adapter);
		document_recycler.setNestedScrollingEnabled(false);

		documentPresenter = new DocumentPresenterImpl(this, new RetrofitDocumentProvider(getContext()));

        sharedPrefs = new SharedPrefs(context);

		documentPresenter.requestDocumentData(sharedPrefs.getAccessToken(),2);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
			documentPresenter.uploadDocumentData(sharedPrefs.getAccessToken(),1,imageUri);
            }
        });

        return view;

    }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == GALLERY_REQUEST_ID && resultCode == RESULT_OK && data != null && data.getData() != null) {
			imageUri = data.getData();
			try {
				//Getting the Bitmap from Gallery
				//bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
				if (imageUri != null) {
					bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);

				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}


    @Override
    public void showMessage(String message) {
        Toaster.showLongMessage(context, message);
    }

    @Override
    public void showLoader(boolean show) {
        if (show)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onDocumentUploaded() {
    }

	@Override
	public void setData(DocumentData companyData) {

	}

	@Override
	public boolean checkPermissionForGallery() {
		if (ContextCompat.checkSelfPermission(context,
				Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
			return true;
		else
			return false;

	}
	@Override
	public boolean requestGalleryPermission() {

		Dexter.checkPermission(new PermissionListener() {
			@Override
			public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */

				GALLERY_REQUEST = true;
			}

			@Override
			public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */

				GALLERY_REQUEST = false;
			}

			@Override
			public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
		}, Manifest.permission.READ_EXTERNAL_STORAGE);


		return GALLERY_REQUEST;
	}
	@Override
	public void showGallery() {
//		Intent intent = new Intent();
//		intent.setType("image/*");
//		intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//		intent.setAction(Intent.ACTION_GET_CONTENT);
//		startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_ID);
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		intent.setType("image/*");
		startActivityForResult(Intent.createChooser(intent, "Select File"), GALLERY_REQUEST_ID);
	}


}
