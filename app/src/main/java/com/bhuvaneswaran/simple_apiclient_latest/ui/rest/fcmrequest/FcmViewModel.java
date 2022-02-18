package com.bhuvaneswaran.simple_apiclient_latest.ui.rest.fcmrequest;

import com.bhuvaneswaran.simple_apiclient_latest.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient_latest.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient_latest.network.ApiHelper;
import com.bhuvaneswaran.simple_apiclient_latest.ui.base.BaseViewModel;

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
