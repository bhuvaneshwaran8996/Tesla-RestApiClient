package com.bhuvaneswaran.simple_apiclient.di.builder;

import com.bhuvaneswaran.simple_apiclient.ui.rest.RestActivity;
import com.bhuvaneswaran.simple_apiclient.ui.rest.RestActivityModule;
import com.bhuvaneswaran.simple_apiclient.ui.rest.restRequest.RestRequestProvider;
//import com.bhuvaneswaran.simple_api_client.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {



    @ContributesAndroidInjector(modules = {RestRequestProvider.class, RestActivityModule.class})
    abstract RestActivity bindrestActivity();

   /* @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();*/

}
