package com.bhuvaneswaran.simple_apiclient.ui.rest.response;

import androidx.databinding.ObservableBoolean;

import com.bhuvaneswaran.simple_apiclient.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient.network.ApiHelper;
import com.bhuvaneswaran.simple_apiclient.ui.base.BaseViewModel;

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
