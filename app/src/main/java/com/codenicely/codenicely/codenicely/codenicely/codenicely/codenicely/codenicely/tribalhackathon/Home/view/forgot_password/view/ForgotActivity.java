package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.model.RetrofitForgotProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.presenter.ForgotPresenter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.presenter.ForgotPresenterImpl;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Toaster;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.view.LoginActivity;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotActivity extends AppCompatActivity implements ForgotView {

    private SharedPrefs sharedPrefs;
    private ForgotPresenter forgotPresenter;
    private String mobile1,password1,confirm_password1;
    private String otp1;
    private Toaster toaster;

    @BindView(R.id.detailsLayout)
    LinearLayout detailsLayout;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.confirm_password)
    EditText confirm_password;
    @BindView(R.id.proceed)
    Button proceed;
	@BindView(R.id.proceed_change_password)
	Button proceed_change_password;
    @BindView(R.id.layout_bar)
    ProgressBar progressBar;
    @BindView(R.id.changePasswordLayout)
    LinearLayout changePasswordLayout;

    @BindView(R.id.otp_layout)
    LinearLayout otp_layout;
    @BindView(R.id.otp)
    EditText otp;
    @BindView(R.id.verify)
    Button verify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        otp_layout.setVisibility(View.GONE);
        detailsLayout.setVisibility(View.VISIBLE);
        changePasswordLayout.setVisibility(View.GONE);
        initialise();
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile1=mobile.getText().toString();
    //     password1=password.getText().toString();
    //     confirm_password1=confirm_password.getText().toString();
                hideKeyboard();
                if (mobile1.equals("")||mobile1.equals(null)){
                    mobile.setError("Please Fill Mobile no.");
                    mobile.setFocusable(true);
                }
                else if(mobile1.length()!=10){
                    mobile.setError("Invalid Mobile No.");
                    mobile.setFocusable(true);
                }
//                else if(password1.equals("") || password1.equals(null)){
//                    password.setError("Please Fill Password");
//                    password.setFocusable(true);
//                }
//                else if(!password1.equals(confirm_password1)){
//                    password.setError("Passwords do not match");
//                    confirm_password.setError("Passwords do not match");
//                    password.setFocusable(true);
//                }
                else {
                    forgotPresenter.requestForgot(mobile1);
                }
            }
        });
    }

    private void initialise() {
        sharedPrefs = new SharedPrefs(this);
        forgotPresenter= new ForgotPresenterImpl(new RetrofitForgotProvider(),this);
        toaster = new Toaster(this);
    }

    @Override
    public void showMessage(String message) {
        toaster.showMessage(message);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onOtpSend() {
        otp_layout.setVisibility(View.VISIBLE);
        detailsLayout.setVisibility(View.GONE);
        otp.setFocusable(true);
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp1=otp.getText().toString();
				password1=password.getText().toString();
				confirm_password1=confirm_password.getText().toString();
				hideKeyboard();

				if (otp1.equals("")|| otp1.equals(null)){
                    otp.setError("Please Fill OTP.");
                    otp.setFocusable(true);

                }else if(password1.equals("") || password1.equals(null)){
					password.setError("Please Fill Password");
					password.setFocusable(true);
				}
				else if(!password1.equals(confirm_password1)){
					password.setError("Passwords do not match");
					confirm_password.setError("Passwords do not match");
					password.setFocusable(true);
				}
                else{
                //    forgotPresenter.responseOtp(mobile1,otp1);
					forgotPresenter.changePassword(mobile1,otp1,password1);
				}

            }
        });
    }

    @Override
    public void onOtpVerified() {
//		changePasswordLayout.setVisibility(View.VISIBLE);
//		otp_layout.setVisibility(View.GONE);
//		otp_layout.setVisibility(View.GONE);
//		initialise();
//		proceed_change_password.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//                password1=password.getText().toString();
//                confirm_password1=confirm_password.getText().toString();
//				hideKeyboard();
//
//                if(password1.equals("") || password1.equals(null)){
//                    password.setError("Please Fill Password");
//                    password.setFocusable(true);
//                }
//                else if(!password1.equals(confirm_password1)){
//                    password.setError("Passwords do not match");
//                    confirm_password.setError("Passwords do not match");
//                    password.setFocusable(true);
//                }
//				else {
//				forgotPresenter.changePassword(mobile1,password1);
//				}
//			}
//		});
		startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
		finish();
	}

	@Override
	public void onPasswordChanged() {

		startActivity(new Intent(ForgotActivity.this, LoginActivity.class));
        finish();
	}

    @Override
    public void enable_password(boolean show) {
        if (show) {
            verify.setEnabled(true);
        }else{
            verify.setEnabled(false);
        }
    }

    @Override
    public void enable_otp(boolean show) {
        if (show) {
            proceed.setEnabled(true);
        }else{
            proceed.setEnabled(false);
        }
    }
    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
