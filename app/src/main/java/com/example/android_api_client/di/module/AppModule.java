package com.example.android_api_client.di.module;


import android.app.Application;
import android.content.Context;

import com.example.android_api_client.db.AppDbHelper;
import com.example.android_api_client.db.DbHelper;
import com.example.android_api_client.db.prefs.AppPreferencesHelper;
import com.example.android_api_client.db.prefs.PreferencesHelper;
import com.example.android_api_client.di.qualifier.DatabaseInfo;
import com.example.android_api_client.di.qualifier.bodyResponse;
import com.example.android_api_client.di.qualifier.headerResponse;
import com.example.android_api_client.network.ApiHelper;
import com.example.android_api_client.network.AppApiHelper;
import com.example.android_api_client.utils.AppConstants;
import com.example.android_api_client.utils.rx.AppSchedulerProvider;
import com.example.android_api_client.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

@Module
public class AppModule {



    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }


    @Provides
    @bodyResponse
    @Singleton
    String provideBodyResponse(){
        return "";
    }
    @Provides
    @headerResponse
    @Singleton
    String provideHeaderResponse(){
        return "";
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
        return new GsonBuilder().setLenient().create();
    }

//    @Provides
//    @PreferenceInfo
//    String providePreferenceName() {
//        return AppConstants.PREF_NAME;
//    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }


    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(){

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
       return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
      return   RxJava2CallAdapterFactory.create();
    }


    @Provides
    @Singleton
     OkHttpClient provideOkHttpClient(int timeout, Gson gson){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .callTimeout(timeout, TimeUnit.SECONDS)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(httpLoggingInterceptor);
        return httpClient.build();

    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory, OkHttpClient okHttpClient,GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(okHttpClient)

                .build();
    }
    @Provides
    @Singleton
    int provideTimeoutConnection(Context context){

        int anInt = context.getSharedPreferences("RestPreference", MODE_PRIVATE).getInt("timeout", 20);
        return anInt;

    }





}