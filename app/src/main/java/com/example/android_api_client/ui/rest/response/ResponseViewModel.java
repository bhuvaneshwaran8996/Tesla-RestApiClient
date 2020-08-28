package com.example.android_api_client.ui.rest.response;

import androidx.databinding.ObservableBoolean;

import com.example.android_api_client.db.DbHelper;
import com.example.android_api_client.db.prefs.PreferencesHelper;
import com.example.android_api_client.network.ApiHelper;
import com.example.android_api_client.ui.base.BaseViewModel;

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
