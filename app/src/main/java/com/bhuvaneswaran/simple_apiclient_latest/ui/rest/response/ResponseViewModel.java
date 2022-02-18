package com.bhuvaneswaran.simple_apiclient_latest.ui.rest.response;

import androidx.databinding.ObservableBoolean;

import com.bhuvaneswaran.simple_apiclient_latest.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient_latest.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient_latest.network.ApiHelper;
import com.bhuvaneswaran.simple_apiclient_latest.ui.base.BaseViewModel;

public class ResponseViewModel  extends BaseViewModel<ResponseNavigator> {

    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;
    public ObservableBoolean isLoading = new ObservableBoolean(false);



    public ResponseViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        super(apiHelper, dbHelper, preferencesHelper);
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;



    }



}
