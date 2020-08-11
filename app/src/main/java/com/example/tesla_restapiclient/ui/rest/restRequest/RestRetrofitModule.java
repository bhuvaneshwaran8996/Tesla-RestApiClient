package com.example.tesla_restapiclient.ui.rest.restRequest;

import android.content.Context;

import androidx.room.Room;

import com.example.tesla_restapiclient.db.AppDatabase;
import com.example.tesla_restapiclient.db.room.dao.HistoryDao;
import com.example.tesla_restapiclient.di.qualifier.DatabaseInfo;
import com.example.tesla_restapiclient.network.RestService;

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
