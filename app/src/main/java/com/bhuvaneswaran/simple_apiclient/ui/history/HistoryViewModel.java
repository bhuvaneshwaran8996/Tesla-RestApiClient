package com.bhuvaneswaran.simple_apiclient.ui.history;

import androidx.lifecycle.ViewModel;

import com.bhuvaneswaran.simple_apiclient.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_apiclient.network.ApiHelper;

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
