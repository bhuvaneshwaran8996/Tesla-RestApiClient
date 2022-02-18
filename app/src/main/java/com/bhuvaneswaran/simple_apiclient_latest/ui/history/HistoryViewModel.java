package com.bhuvaneswaran.simple_apiclient_latest.ui.history;

import androidx.lifecycle.ViewModel;

import com.bhuvaneswaran.simple_apiclient_latest.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient_latest.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient_latest.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_apiclient_latest.network.ApiHelper;

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
