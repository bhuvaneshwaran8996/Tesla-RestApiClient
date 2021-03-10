package com.bhuvaneswaran.simple_api_client.ui.history;

import androidx.lifecycle.ViewModel;

import com.bhuvaneswaran.simple_api_client.db.DbHelper;
import com.bhuvaneswaran.simple_api_client.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_api_client.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_api_client.network.ApiHelper;

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
