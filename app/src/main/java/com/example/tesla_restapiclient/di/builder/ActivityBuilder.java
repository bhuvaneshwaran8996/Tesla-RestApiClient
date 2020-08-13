package com.example.tesla_restapiclient.di.builder;

import com.example.tesla_restapiclient.ui.rest.RestActivity;
import com.example.tesla_restapiclient.ui.rest.RestActivityModule;
import com.example.tesla_restapiclient.ui.rest.restRequest.RestRequestProvider;
import com.example.tesla_restapiclient.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {



    @ContributesAndroidInjector(modules = {RestRequestProvider.class, RestActivityModule.class})
    abstract RestActivity bindrestActivity();

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

}
