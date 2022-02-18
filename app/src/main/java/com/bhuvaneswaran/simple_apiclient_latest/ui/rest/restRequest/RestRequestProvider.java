package com.bhuvaneswaran.simple_apiclient_latest.ui.rest.restRequest;

import com.bhuvaneswaran.simple_apiclient_latest.ui.history.HistoryFragment;
import com.bhuvaneswaran.simple_apiclient_latest.ui.rest.fcmrequest.FcmFragment;
import com.bhuvaneswaran.simple_apiclient_latest.ui.rest.response.ResponseFragment;

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
