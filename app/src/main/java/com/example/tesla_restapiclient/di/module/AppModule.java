package com.example.tesla_restapiclient.di.module;


import android.app.Application;
import android.content.Context;

import androidx.databinding.library.baseAdapters.BuildConfig;
import androidx.room.Room;

import com.example.tesla_restapiclient.db.AppDatabase;
import com.example.tesla_restapiclient.db.AppDbHelper;
import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.AppPreferencesHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.di.qualifier.ApiInfo;
import com.example.tesla_restapiclient.di.qualifier.DatabaseInfo;
import com.example.tesla_restapiclient.di.qualifier.PreferenceInfo;
import com.example.tesla_restapiclient.network.ApiHelper;
import com.example.tesla_restapiclient.network.AppApiHelper;
import com.example.tesla_restapiclient.utils.AppConstants;
import com.example.tesla_restapiclient.utils.rx.AppSchedulerProvider;
import com.example.tesla_restapiclient.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

//    @Provides
//    @ApiInfo
//    String provideApiKey() {
//        return BuildConfig.API_KEY;
//    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }


    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }


    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }



    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }





}
