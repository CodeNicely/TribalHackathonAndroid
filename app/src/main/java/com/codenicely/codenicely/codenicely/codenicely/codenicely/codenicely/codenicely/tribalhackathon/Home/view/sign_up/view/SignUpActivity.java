package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Toaster;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.utils.FcmUtils;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.home.Home;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.RetrofitSignUpProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.model.data.OtpData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.presenter.SignUpPresenter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.sign_up.presenter.SignUpPreseterImpl;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.MyApplication.getContext;

public class SignUpActivity extends AppCompatActivity implements SignUpView {

    private SharedPrefs sharedPrefs;
    private SignUpPresenter signUpPresenter;
    private String company_name1, mobile1, password1, gst_in1, email1, confirm_password1,aadhaar,address;
    private String otp1,refer_code;
    private Toaster toaster;
	private boolean checkBoxIsChecked =false;
	private Context context;

	@BindView(R.id.address)
    EditText Address;

	@BindView(R.id.aadhaar)
    EditText Aadhaar;

	@BindView(R.id.detailsLayout)
    LinearLayout detailsLayout;

    @BindView(R.id.company_name)
    EditText company_name;

    @BindView(R.id.mobile)
    EditText mobile;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.confirm_password)
    EditText confirm_password;

    @BindView(R.id.proceed)
    Button proceed;

    @BindView(R.id.layout_bar)
    ProgressBar progressBar;

    @BindView(R.id.otp_layout)
    LinearLayout otp_layout;

    @BindView(R.id.otp)
    EditText otp;

    @BindView(R.id.verify)
    Button verify;

    @BindView(R.id.login)
    TextView login;

    @BindView(R.id.resend_otp)
    TextView resend_otp;

    @BindView(R.id.referralInfo)
    ImageView referralInfo;

    @BindView(R.id.refer_code)
    EditText refer_code_edittext;

    @BindView(R.id.referralLayout)
    LinearLayout referralLayout;

    @BindView(R.id.checkbox)
    CheckBox checkBox;

    private String temp_access_token;
	private boolean otp_bool = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Random rand = new Random();
		ButterKnife.bind(this);
        otp_layout.setVisibility(View.GONE);
        detailsLayout.setVisibility(View.VISIBLE);
        initialise();
		refer_code_edittext.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
		context = getContext();
		try {
			mobile1 = getIntent().getStringExtra("mobile");
			temp_access_token = getIntent().getStringExtra("temp_access_token");
			otp_bool = getIntent().getBooleanExtra("otp_bool",false);
			if (otp_bool){
				otp_layout.setVisibility(View.VISIBLE);
				detailsLayout.setVisibility(View.GONE);
			}
		}catch (NullPointerException e){

		}

		proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                company_name1 = company_name.getText().toString();
                mobile1 = mobile.getText().toString();
                password1 = password.getText().toString();
                confirm_password1 = confirm_password.getText().toString();
                refer_code = refer_code_edittext.getText().toString();
                address = Address.getText().toString();
                aadhaar= Aadhaar.getText().toString();
                hideKeyboard();

                if (mobile1.equals("") || mobile1.equals(null)) {
                    mobile.setError("Please Fill Mobile no.");
                    mobile.setFocusable(true);
                } else if (mobile1.length() != 10) {
                    mobile.setError("Invalid Mobile No.");
                    mobile.setFocusable(true);
                } else if (aadhaar.length() != 12) {
                    Aadhaar.setError("Invalid Aadhar No.");
                    Aadhaar.setFocusable(true);
                } else if(rand.nextInt(50) % 2 != 0)
                {
                    Aadhaar.setError("Invalid Aadhar No.");
                    Aadhaar.setFocusable(true);
                }
                else if (company_name1.equals("") || company_name1.equals(null)) {
                    company_name.setError("Please Fill Company Name");
                    company_name.setFocusable(true);
                }else if (aadhaar.equals("") || company_name1.equals(null)) {
                    company_name.setError("Please Fill Address");
                    company_name.setFocusable(true);
                }
                else if (!password1.equals(confirm_password1)) {
                    password.setError("Passwords do not match");
                    confirm_password.setError("Passwords do not match");
                    confirm_password.setFocusable(true);
                }else if (checkBoxIsChecked && (refer_code.equals("") || refer_code.equals(null))) {
					refer_code_edittext.setError("Please Fill Referral Code");
					refer_code_edittext.setFocusable(true);
				} else {
                    signUpPresenter.requestOtp(mobile1,company_name1,password1, otp1,address,aadhaar,checkBoxIsChecked);
                }
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxIsChecked = isChecked;
				if (isChecked){

                    referralLayout.setVisibility(View.VISIBLE);
                }else {
                    referralLayout.setVisibility(View.GONE);
                }
            }
        });
		verify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				otp1 = otp.getText().toString();
				if (otp1.equals("") || otp1.equals(null)) {
					otp.setError("Please Fill OTP.");
					otp.setFocusable(true);
				} else {

					signUpPresenter.requestSignUp(mobile1,company_name1,password1, otp1,address,aadhaar);
				}
			}
		});
//        referralInfo.setOnClickListener(new SplashView.OnClickListener() {
//            @Override
//            public void onClick(SplashView v) {
////                referralInfo.show
//				new SimpleTooltip.Builder(context)
//						.anchorView(referralInfo)
//						.text("Signup using a referral code and get free usage of 45 days.")
//						.textColor(getResources().getColor(R.color.white))
//						.gravity(Gravity.TOP)
//						.animated(false)
//						.transparentOverlay(true)
//						.build()
//						.show();
//            }
//        });
        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpPresenter.requestOtpResend(temp_access_token);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initialise() {
        sharedPrefs = new SharedPrefs(this);
//        signUpPresenter = new SignUpPreseterImpl(this,new RetrofitSignUpProvider());
        signUpPresenter = new SignUpPreseterImpl(this, new RetrofitSignUpProvider());
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
    public void onOtpSend(OtpData otpData) {

        temp_access_token = otpData.getTemp_access_token();

        otp_layout.setVisibility(View.VISIBLE);
        detailsLayout.setVisibility(View.GONE);
        otp.setFocusable(true);

    }

    @Override
    public void onOtpVerified(String access_token) {

        sharedPrefs.setAccessToken(access_token);
        sharedPrefs.setCompanyName(company_name1);
        sharedPrefs.setEmail(address);
        sharedPrefs.setMobile(mobile1);
        sharedPrefs.setAadhaar(aadhaar);
        sharedPrefs.setLoggedIn(true);

        FcmUtils fcmUtils=new FcmUtils(this);

        fcmUtils.sendFcmToServer();
        startActivity(new Intent(SignUpActivity.this, Home.class));

        finish();
    }

    @Override
    public void enable_signUp(boolean show) {
        if (show) {
            proceed.setEnabled(true);
        } else {
            proceed.setEnabled(false);
        }
    }

    @Override
    public void enable_otp(boolean show) {
        if (show) {
            verify.setEnabled(true);
        } else {
            verify.setEnabled(false);
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


