package com.example.tesla_restapiclient.application;

import android.app.Activity;
import android.app.Application;

import com.example.tesla_restapiclient.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class RestApplication extends Application implements HasActivityInjector {


    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;



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
}
