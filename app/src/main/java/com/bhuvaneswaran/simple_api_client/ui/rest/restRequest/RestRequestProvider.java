package com.bhuvaneswaran.simple_api_client.ui.rest.restRequest;

import com.bhuvaneswaran.simple_api_client.ui.history.HistoryFragment;
import com.bhuvaneswaran.simple_api_client.ui.rest.fcmrequest.FcmFragment;
import com.bhuvaneswaran.simple_api_client.ui.rest.response.ResponseFragment;

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
