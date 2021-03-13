package com.bhuvaneswaran.simple_api_client.application;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;


import com.bhuvaneswaran.simple_api_client.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;

public class RestApplication extends Application implements HasFragmentInjector,HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;



    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder().application(this)
                .build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }


    @Override
    public AndroidInjector<Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
