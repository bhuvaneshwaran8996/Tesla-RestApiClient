package com.example.tesla_restapiclient.ui.splash;

import android.os.Handler;

import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.network.ApiHelper;
import com.example.tesla_restapiclient.ui.base.BaseViewModel;




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
        },3000);
    }






}
