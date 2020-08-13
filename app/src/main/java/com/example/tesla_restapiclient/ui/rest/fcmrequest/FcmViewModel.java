package com.example.tesla_restapiclient.ui.rest.fcmrequest;

import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.network.ApiHelper;
import com.example.tesla_restapiclient.ui.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class FcmViewModel extends BaseViewModel<FcmNavigator> {


    public FcmViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        super(apiHelper, dbHelper, preferencesHelper);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return super.getCompositeDisposable();
    }

    public void testnavigae(){
        getNavigator().fucmNavigatoe();
    }
}
