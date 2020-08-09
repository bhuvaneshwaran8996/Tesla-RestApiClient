package com.example.tesla_restapiclient.network;

import android.content.Context;

import com.google.gson.JsonObject;

import org.jetbrains.annotations.Async;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class AppApiHelper implements ApiHelper {

    RestService restService;


    @Inject
    public AppApiHelper(RestService restService){

        this.restService = restService;
    }

    @Override
    public Observable<Response<ResponseBody>> processBodyWithRaw(String urlPath, HashMap<String, String> heafermap, JsonObject bodyList) {
        return restService.processPostRaw(urlPath,heafermap,bodyList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Response<ResponseBody>> processPutWithRaw(String urlPath, HashMap<String, String> heafermap, JsonObject bodyList) {
        return restService.processPutRaw(urlPath,heafermap,bodyList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Response<ResponseBody>> processPutWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList) {
        return restService.processPutKey(url,headerModelList,bodyList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Response<ResponseBody>> processBodyWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList) {
        return restService.processPostKey(url,headerModelList,bodyList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Response<ResponseBody>> processGetRequest(String urlPath, HashMap<String, String> headermap) {

        return  restService.processGetRequest(urlPath, headermap).subscribeOn(Schedulers.io());


    }
}
