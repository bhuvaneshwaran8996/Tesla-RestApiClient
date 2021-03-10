package com.bhuvaneswaran.simple_api_client.ui.rest.restRequest;

import com.bhuvaneswaran.simple_api_client.network.RestService;

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
