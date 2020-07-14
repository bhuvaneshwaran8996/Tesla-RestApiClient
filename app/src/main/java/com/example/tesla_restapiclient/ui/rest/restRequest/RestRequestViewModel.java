package com.example.tesla_restapiclient.ui.rest.restRequest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.network.ApiHelper;
import com.example.tesla_restapiclient.ui.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class RestRequestViewModel extends BaseViewModel<RestResquestNavigtor> {
    public RestRequestViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
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

    @Override
    public void setNavigator(RestResquestNavigtor navigator) {
        super.setNavigator(navigator);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    @NonNull
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public RestResquestNavigtor getNavigator() {
        return super.getNavigator();
    }
}
