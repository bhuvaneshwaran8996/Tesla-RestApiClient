package com.bhuvaneswaran.simple_api_client.di.component;

import android.app.Application;

import com.bhuvaneswaran.simple_api_client.application.RestApplication;
import com.bhuvaneswaran.simple_api_client.di.builder.ActivityBuilder;
import com.bhuvaneswaran.simple_api_client.di.module.AppModule;
import com.bhuvaneswaran.simple_api_client.di.module.RoomModule;
import com.bhuvaneswaran.simple_api_client.ui.rest.restRequest.RestRetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, RoomModule.class, AppModule.class, ActivityBuilder.class,  RestRetrofitModule.class})
public interface AppComponent {

    void inject(RestApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
