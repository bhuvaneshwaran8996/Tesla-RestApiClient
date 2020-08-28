package com.example.android_api_client.ui.splash;

import android.os.Handler;

import com.example.android_api_client.db.DbHelper;
import com.example.android_api_client.db.prefs.PreferencesHelper;
import com.example.android_api_client.network.ApiHelper;
import com.example.android_api_client.ui.base.BaseViewModel;




public class SplashViewModel extends BaseViewModel<SplashNavigator>{

    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;



    public SplashViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        super(apiHelper, dbHelper, preferencesHelper);
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;



    }

    public void waitInSplash(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getNavigator().navigateToRestActivity();
            }
        },100);
    }






}
