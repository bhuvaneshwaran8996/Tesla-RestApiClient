package com.example.tesla_restapiclient.ui.history;

import androidx.lifecycle.ViewModel;

import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.db.room.dao.HistoryDao;
import com.example.tesla_restapiclient.network.ApiHelper;

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
