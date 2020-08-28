package com.example.android_api_client.ui.rest.restRequest;

import com.example.android_api_client.ui.history.HistoryFragment;
import com.example.android_api_client.ui.rest.fcmrequest.FcmFragment;
import com.example.android_api_client.ui.rest.response.ResponseFragment;

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

    @ContributesAndroidInjector
    public abstract HistoryFragment provideHistoryActivity();





}
