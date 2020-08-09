package com.example.tesla_restapiclient.network;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiHelper {


    Observable<Response<ResponseBody>> processGetRequest(String urlPath, HashMap<String, String> headermap);

    Observable<Response<ResponseBody>> processBodyWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList);

    Observable<Response<ResponseBody>> processBodyWithRaw( String urlPath,  HashMap<String, String> heafermap,  JsonObject bodyList);

    Observable<Response<ResponseBody>> processPutWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList);

    Observable<Response<ResponseBody>> processPutWithRaw( String urlPath,  HashMap<String, String> heafermap,  JsonObject bodyList);

    Observable<Response<ResponseBody>> processPatchWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList);

    Observable<Response<ResponseBody>> processPatchWithRaw( String urlPath,  HashMap<String, String> heafermap,  JsonObject bodyList);


    Observable<Response<ResponseBody>> processDeleteRequest(String urlPath, HashMap<String, String> headermap);

}
