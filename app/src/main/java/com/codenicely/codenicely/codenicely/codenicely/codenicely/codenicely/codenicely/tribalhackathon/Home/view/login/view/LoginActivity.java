package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.forgot_password.view.ForgotActivity;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Toaster;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils.FcmUtils;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.home.Home;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.RetrofitLoginProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.model.data.LoginData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.presenter.LoginPresenter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.presenter.LoginPresenterImpl;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.view.SignUpActivity;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private SharedPrefs sharedPreferences;
    private LoginPresenter loginPresenter;
    private Toaster toaster;
    private static LoginActivity loginActivity;
    private String mobile1, password1;

    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.button_login)
    Button button_login;
    @BindView(R.id.button_sign_up)
    Button button_sign_up;
    @BindView(R.id.forgot_password)
    TextView forgot_password;
    @BindView(R.id.layout_bar)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginActivity = this;
        ButterKnife.bind(loginActivity);
        intialize();

        mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mobile.getText().toString().length() == 10) {
//                    hideKeyboard();
                password.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mobile1 = mobile.getText().toString();
                password1 = password.getText().toString();
                hideKeyboard();
                if (mobile1.equals("") || mobile1.equals(null)) {
                    mobile.setError("Please Fill Mobile no.");
                    mobile.setFocusable(true);
                } else if (mobile1.length() != 10) {
                    mobile.setError("Invalid Mobile No.");
                    mobile.setFocusable(true);
                } else if (password1.equals("") || password1.equals(null)) {
                    password.setError("Please Fill Password");
                    password.setFocusable(true);
                } else {

                    loginPresenter.requestLogin(mobile1, password1);
                }
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(i);
            }
        });

        button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

    }

    private void intialize() {
        sharedPreferences = new SharedPrefs(loginActivity);
//        loginPresenter= new LoginPresenterImpl(loginActivity,new RetrofitLoginProvider());
        loginPresenter = new LoginPresenterImpl(loginActivity, new RetrofitLoginProvider());
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
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onVerified(LoginData loginData) {
        sharedPreferences.setLoggedIn(true);
        sharedPreferences.setFirstTimeLaunch(true);
        sharedPreferences.setFirstTimeLogin(true);
        
        sharedPreferences.setAccessToken(loginData.getAccess_token());
        sharedPreferences.setCompanyName(loginData.getCompany_name());
        sharedPreferences.setMobile(loginData.getMobile());

        FcmUtils fcmUtils=new FcmUtils(this);

        fcmUtils.sendFcmToServer();

        Intent i = new Intent(LoginActivity.this, Home.class);
        startActivity(i);
        finish();
    }

    @Override
    public void disable_login(boolean show) {
        if (show) {
            button_login.setEnabled(true);
        } else {
            button_login.setEnabled(false);
        }
    }

	@Override
	public void verifyOTP(String mobile, String temp_access_token) {
		Intent intent = new Intent(this,SignUpActivity.class);
		intent.putExtra("mobile",mobile);
		intent.putExtra("temp_access_token",temp_access_token);
		intent.putExtra("otp_bool",true);
		startActivity(intent);
	}

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        loginPresenter.onDestroy();
//    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
