package com.example.tesla_restapiclient.ui.rest;


import android.content.Context;

import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import com.example.tesla_restapiclient.db.AppDatabase;
import com.example.tesla_restapiclient.db.room.dao.HistoryDao;
import com.example.tesla_restapiclient.di.qualifier.BodyAdapter;
import com.example.tesla_restapiclient.di.qualifier.DatabaseInfo;
import com.example.tesla_restapiclient.di.qualifier.HeaderAdapter;
import com.example.tesla_restapiclient.ui.body.BodyRecyclerAdapter;
import com.example.tesla_restapiclient.ui.header.HeadersAdapter;
import com.example.tesla_restapiclient.ui.rest.restRequest.FcmAdpater;

import javax.inject.Singleton;

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
