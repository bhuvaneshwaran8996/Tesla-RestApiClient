package com.example.android_api_client.ui.rest.fcmrequest;

import com.example.android_api_client.db.DbHelper;
import com.example.android_api_client.db.prefs.PreferencesHelper;
import com.example.android_api_client.network.ApiHelper;
import com.example.android_api_client.ui.base.BaseViewModel;

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
