package com.example.tesla_restapiclient.db;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Single<Object> cacheRequests() {
        return null;
    }
}
