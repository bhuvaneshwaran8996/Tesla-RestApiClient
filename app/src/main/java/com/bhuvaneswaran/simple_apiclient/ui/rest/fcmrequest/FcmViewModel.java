package com.bhuvaneswaran.simple_apiclient.ui.rest.fcmrequest;

import com.bhuvaneswaran.simple_apiclient.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient.network.ApiHelper;
import com.bhuvaneswaran.simple_apiclient.ui.base.BaseViewModel;

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
