package com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.Home.view.helper.SharedPrefs;
import com.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.codenicely.tribalhackathon.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;


public class Home extends AppCompatActivity {
    private SharedPrefs sharedPrefs;
    private Toolbar toolbar;
    private String access_token;
    private static final String TAG = Home.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPrefs = new SharedPrefs(this);
        access_token = sharedPrefs.getAccessToken();
		BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar1);
		bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
			@Override
			public void onTabSelected(@IdRes int tabId) {
				if (tabId == R.id.tab_profile) {

				}

				else if (tabId == R.id.tab_events){
				}
				else if (tabId == R.id.tab_home){

				}
				else if (tabId == R.id.tab_sponsors){


				}
				else if (tabId == R.id.tab_aboutus){


				}

			}
		});
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {

            super.onBackPressed();

        } else {

            final android.support.v7.app.AlertDialog ad = new android.support.v7.app.AlertDialog.Builder(this)
                    .create();
            ad.setCancelable(false);
            ad.setTitle("Exit ?");
            ad.setMessage("Do you really want to exit ? ");
            ad.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();
                    finish();
                }
            });
            ad.setButton(DialogInterface.BUTTON_NEGATIVE, "no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ad.cancel();

                }
            });
            ad.show();
        }
    }

	public void addFragment(Fragment fragment) {
		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.contentContainer, fragment);
			fragmentTransaction.commit();
		}
	}
//    public void addFragment(Fragment fragment, String title) {
//        if (fragment != null) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
////            fragmentTransaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
//            fragmentTransaction.replace(R.id.home_layout, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//            //     getSupportActionBar().setTitle(title);
//        }
//    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
    }

}
