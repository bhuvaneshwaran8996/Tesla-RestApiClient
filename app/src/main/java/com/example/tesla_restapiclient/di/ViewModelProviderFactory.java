package com.example.tesla_restapiclient.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.network.ApiHelper;
import com.example.tesla_restapiclient.ui.history.HistoryViewModel;
import com.example.tesla_restapiclient.ui.rest.RestViewModel;
import com.example.tesla_restapiclient.ui.rest.fcmrequest.FcmViewModel;
import com.example.tesla_restapiclient.ui.rest.response.ResponseViewModel;
import com.example.tesla_restapiclient.ui.rest.restRequest.RestRequestProvider;
import com.example.tesla_restapiclient.ui.rest.restRequest.RestRequestViewModel;
import com.example.tesla_restapiclient.ui.splash.SplashViewModel;

import javax.inject.Inject;

import dagger.Module;

@Module
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;

    @Inject
    public ViewModelProviderFactory(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {

        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(apiHelper, dbHelper, preferencesHelper);
        } else if (modelClass.isAssignableFrom(RestViewModel.class)) {
            return (T) new RestViewModel(apiHelper, dbHelper, preferencesHelper);
        } else if (modelClass.isAssignableFrom(RestRequestViewModel.class)) {
            return (T) new RestRequestViewModel(apiHelper, dbHelper, preferencesHelper);

        }else if(modelClass.isAssignableFrom(FcmViewModel.class)){
            return (T) new FcmViewModel(apiHelper, dbHelper, preferencesHelper);
        }else if(modelClass.isAssignableFrom(ResponseViewModel.class)){
            return (T) new ResponseViewModel(apiHelper, dbHelper, preferencesHelper);
        }else if(modelClass.isAssignableFrom(HistoryViewModel.class)){
            return (T) new ResponseViewModel(apiHelper, dbHelper, preferencesHelper);
        }
        return super.create(modelClass);
    }
}
