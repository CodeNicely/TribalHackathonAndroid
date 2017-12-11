package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.Toaster;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.image_loader.GlideImageLoader;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.image_loader.ImageLoader;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.home.Home;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.login.view.LoginActivity;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.RetrofitSplashScreenProvider;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.model.data.SplashScreenData;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.presenter.SplashScreenPresenter;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.splash.presenter.SplashScreenPresenterImpl;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activity extends AppCompatActivity implements SplashView {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.logo)
    ImageView logo;


    private ImageLoader imageLoader;
    private SharedPrefs sharedPrefs;
    private SplashScreenPresenter splashScreenPresenter;
    private Handler handler;
    private Toaster toaster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        toaster = new Toaster(this);
        imageLoader = new GlideImageLoader(this);
//        Glide.with(this).load(R.drawable.codenicely_logo).into(codenicely_logo);
        Glide.with(this).load(R.drawable.logooo).into(logo);
//        Glide.with(this).load(R.drawable.gst_invoice_maker).into(logo1);

        sharedPrefs = new SharedPrefs(this);
        splashScreenPresenter = new SplashScreenPresenterImpl(this, new RetrofitSplashScreenProvider());
//        splashScreenPresenter = new SplashScreenPresenterImpl(this,new MockSplashScreenProvider());

//        if(MyApplication.getFcm_token()!=null) {

//            splashScreenPresenter.requestSplash(MyApplication.getFcm_token(), sharedPrefs.getAccessToken());
//        }else{
            splashScreenPresenter.requestSplash("fcmisnull", sharedPrefs.getAccessToken());

//        }
    }

    @Override
    public void showMessage(String message) {
        toaster.showMessage(message);
    }

    @Override
    public void showDialog(String message) {

        try {
            final AlertDialog ad = new AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("No Internet Connection");
            ad.setMessage("Please connect to internet to use our app");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "Retry", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    splashScreenPresenter.requestSplash("fcm", sharedPrefs.getAccessToken());

                }
            });
            ad.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(android.view.View.VISIBLE);
        } else {
            progressBar.setVisibility(android.view.View.GONE);
        }
    }

    @Override
    public void onVersionReceived(SplashScreenData splashScreenData) throws PackageManager.NameNotFoundException {

        try {
            if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode <
                    splashScreenData.getVersion() && !splashScreenData.isCompulsory_update()) {


                final AlertDialog ad = new AlertDialog.Builder(this)
                        .create();
                ad.setCancelable(false);
                ad.setTitle("App Update Available");
                Log.d("SPLASH1---", "No");
                ad.setMessage("Please update the app for better experience");
                ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }

                        finish();
                    }
                });
                ad.setButton(DialogInterface.BUTTON_NEGATIVE, "Not Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();

                        if (!sharedPrefs.isFirstTimeLaunch()) {
                            if (sharedPrefs.isLoggedIn()) {
                                startActivity(new Intent(Activity.this, Home.class));
                                finish();
                            } else {
                                startActivity(new Intent(Activity.this, LoginActivity.class));
                                finish();
                            }

                        } else {
                            startActivity(new Intent(Activity.this, LoginActivity.class));
                            finish();
                        }

                    }
                });
                ad.show();


            } else if (getPackageManager().getPackageInfo(getPackageName(), 0).versionCode <
                    splashScreenData.getVersion() && splashScreenData.isCompulsory_update()) {

                final AlertDialog ad = new AlertDialog.Builder(this)

                        .create();
                ad.setCancelable(false);
                ad.setTitle("App Update Available");
                ad.setMessage("This is a compulsory Update . Please Update the app to enjoy our services");
                ad.setButton(DialogInterface.BUTTON_POSITIVE, "Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ad.cancel();
                        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }

                        finish();
                    }
                });
                ad.show();

            } else {
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (!sharedPrefs.isFirstTimeLaunch()) {
                            if (sharedPrefs.isLoggedIn()) {
                                startActivity(new Intent(Activity.this, Home.class));
                                finish();
                            } else {
                                startActivity(new Intent(Activity.this, LoginActivity.class));
                                finish();
                            }

                        } else {
                            Log.d("Splash---", "Intent Welcome");
                            startActivity(new Intent(Activity.this, LoginActivity.class));
                            finish();
                        }


                    }
                }, 300);

            }
        } catch (Exception e) {
            e.printStackTrace();
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    if (!sharedPrefs.isFirstTimeLaunch()) {
                        if (sharedPrefs.isLoggedIn()) {
                            startActivity(new Intent(Activity.this, Home.class));
                            finish();
                        } else {
                            startActivity(new Intent(Activity.this, LoginActivity.class));
                            finish();
                        }

                    } else {
                        startActivity(new Intent(Activity.this, LoginActivity.class));
                        finish();
                    }


                }
            }, 300);
        }
    }

    @Override
    public void onFailed() {

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (!sharedPrefs.isFirstTimeLaunch()) {
                    if (sharedPrefs.isLoggedIn()) {
                        startActivity(new Intent(Activity.this, Home.class));
                        finish();
                    } else {
                        startActivity(new Intent(Activity.this, LoginActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(Activity.this, LoginActivity.class));
                    finish();
                }

            }
        }, 300);
    }
}
