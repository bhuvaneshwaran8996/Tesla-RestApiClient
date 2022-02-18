package com.bhuvaneswaran.simple_apiclient_latest.network;

import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;

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
