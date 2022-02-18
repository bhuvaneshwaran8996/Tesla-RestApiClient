package com.bhuvaneswaran.simple_apiclient_latest.ui.rest;


import android.content.Context;

import androidx.fragment.app.FragmentManager;

import com.bhuvaneswaran.simple_apiclient_latest.di.qualifier.BodyAdapter;
import com.bhuvaneswaran.simple_apiclient_latest.di.qualifier.HeaderAdapter;
import com.bhuvaneswaran.simple_apiclient_latest.ui.body.BodyRecyclerAdapter;
import com.bhuvaneswaran.simple_apiclient_latest.ui.header.HeadersAdapter;
import com.bhuvaneswaran.simple_apiclient_latest.ui.rest.restRequest.FcmAdpater;

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

    @Provides
    @HeaderAdapter
    public static HeadersAdapter provideHeadersAdapter(Context context){
        return new HeadersAdapter(context);
    }


    @Provides
    @BodyAdapter
    public static BodyRecyclerAdapter provideBodyAdapter(Context context){
      return  new BodyRecyclerAdapter(context);
    }


    @Provides
    public static FragmentManager provideFragmentManager(RestActivity restActivity){
        return restActivity.getSupportFragmentManager();
    }

}
