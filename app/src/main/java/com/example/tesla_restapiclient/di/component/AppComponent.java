package com.example.tesla_restapiclient.di.component;

import android.app.Application;

import com.example.tesla_restapiclient.application.RestApplication;
import com.example.tesla_restapiclient.di.builder.ActivityBuilder;
import com.example.tesla_restapiclient.di.module.AppModule;
import com.example.tesla_restapiclient.ui.rest.restRequest.RestRetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class,  RestRetrofitModule.class})
public interface AppComponent {

    void inject(RestApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
