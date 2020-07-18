package com.example.tesla_restapiclient.ui.rest;


import com.example.tesla_restapiclient.di.qualifier.fcmadapter;
import com.example.tesla_restapiclient.ui.rest.restRequest.FcmAdpater;

import dagger.Module;
import dagger.Provides;

@Module
public class RestActivityModule {


    @Provides
    public static RestFragmentAdapter provideRestFragmentAdapter(RestActivity restActivity){
        return new RestFragmentAdapter(restActivity.getSupportFragmentManager());
    }

    @Provides
    public static FcmAdpater provideFcmAdapter(RestActivity restActivity){
        return new FcmAdpater(restActivity.getSupportFragmentManager());
    }

}
