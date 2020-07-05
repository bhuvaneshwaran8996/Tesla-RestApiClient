package com.example.tesla_restapiclient.network;

import android.content.Context;

import javax.inject.Inject;

import io.reactivex.Single;

public class AppApiHelper implements ApiHelper {


    @Inject
    public AppApiHelper(Context context){

    }
    @Override
    public Single<Object> fuckKaviya() {
        return null;
    }
}
