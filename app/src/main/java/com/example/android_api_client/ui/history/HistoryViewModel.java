package com.example.android_api_client.ui.history;

import androidx.lifecycle.ViewModel;

import com.example.android_api_client.db.DbHelper;
import com.example.android_api_client.db.prefs.PreferencesHelper;
import com.example.android_api_client.db.room.dao.HistoryDao;
import com.example.android_api_client.network.ApiHelper;

import javax.inject.Inject;

public class HistoryViewModel extends ViewModel {



    @Inject
    HistoryDao historyDao;
    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;


    public HistoryViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper){

        this.apiHelper = apiHelper;
        this.dbHelper =  dbHelper;
        this.preferencesHelper = preferencesHelper;


    }

    public void testing(){

    }
}
