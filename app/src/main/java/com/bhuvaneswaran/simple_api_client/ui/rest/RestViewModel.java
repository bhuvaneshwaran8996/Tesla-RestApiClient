package com.bhuvaneswaran.simple_api_client.ui.rest;

import androidx.databinding.ObservableBoolean;

import com.bhuvaneswaran.simple_api_client.db.DbHelper;
import com.bhuvaneswaran.simple_api_client.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_api_client.network.ApiHelper;
import com.bhuvaneswaran.simple_api_client.ui.base.BaseViewModel;

public class RestViewModel extends BaseViewModel {

    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;
    public ObservableBoolean isLoading = new ObservableBoolean(false);



    public RestViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        super(apiHelper, dbHelper, preferencesHelper);
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;



    }

}
