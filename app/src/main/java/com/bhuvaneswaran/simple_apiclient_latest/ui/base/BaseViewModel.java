package com.bhuvaneswaran.simple_apiclient_latest.ui.base;

import androidx.lifecycle.ViewModel;

import com.bhuvaneswaran.simple_apiclient_latest.db.DbHelper;
import com.bhuvaneswaran.simple_apiclient_latest.db.prefs.PreferencesHelper;
import com.bhuvaneswaran.simple_apiclient_latest.network.ApiHelper;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<N> extends ViewModel {

    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;
    public CompositeDisposable compositeDisposable;
    public WeakReference weakReference;

    public N Navigator;
    public BaseViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper ){
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
        this.compositeDisposable = new CompositeDisposable();

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void setNavigator(N navigator) {
        this.weakReference = new WeakReference<>(navigator);

    }

    public N getNavigator() {
        return (N) this.weakReference.get();
    }
}