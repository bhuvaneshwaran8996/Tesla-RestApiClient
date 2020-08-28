package com.example.android_api_client.di.builder;

import com.example.android_api_client.ui.rest.RestActivity;
import com.example.android_api_client.ui.rest.RestActivityModule;
import com.example.android_api_client.ui.rest.restRequest.RestRequestProvider;
import com.example.android_api_client.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {



    @ContributesAndroidInjector(modules = {RestRequestProvider.class, RestActivityModule.class})
    abstract RestActivity bindrestActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

}
