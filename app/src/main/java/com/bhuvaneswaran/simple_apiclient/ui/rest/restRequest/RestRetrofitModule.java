package com.bhuvaneswaran.simple_apiclient.ui.rest.restRequest;

import com.bhuvaneswaran.simple_apiclient.network.RestService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RestRetrofitModule {

    @Provides
    @Singleton
    RestService provideRestService(Retrofit retrofit){
        return  retrofit.create(RestService.class);
    }

}
