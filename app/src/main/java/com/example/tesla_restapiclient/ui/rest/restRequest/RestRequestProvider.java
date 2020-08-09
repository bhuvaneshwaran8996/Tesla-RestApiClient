package com.example.tesla_restapiclient.ui.rest.restRequest;

import com.example.tesla_restapiclient.ui.rest.RestActivityModule;
import com.example.tesla_restapiclient.ui.rest.fcmrequest.FcmFragment;
import com.example.tesla_restapiclient.ui.rest.response.ResponseFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RestRequestProvider {

    @ContributesAndroidInjector
    public abstract RestFragment provideRestRequest();

    @ContributesAndroidInjector
    public abstract ResponseFragment provideresponsefrgment();

    @ContributesAndroidInjector
    public abstract FcmFragment providefcmfrgment();





}
