package com.bhuvaneswaran.simple_apiclient_latest.network;



import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface RestService {

    @GET
    Observable<Response<ResponseBody>> processGetRequest(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap);

    @POST
    Observable<Response<ResponseBody>> processPostKey(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap, @Body Map<String, String> bodyList);
    @POST
    Observable<Response<ResponseBody>> processPostRaw(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap, @Body JsonObject bodyList);

    @PUT
    Observable<Response<ResponseBody>> processPutKey(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap, @Body Map<String, String> bodyList);

    @PUT
    Observable<Response<ResponseBody>> processPutRaw(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap, @Body JsonObject bodyList);

    @PATCH
    Observable<Response<ResponseBody>> processPatchKey(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap, @Body Map<String, String> bodyList);

    @PATCH
    Observable<Response<ResponseBody>> processPatchRaw(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap, @Body JsonObject bodyList);

    @DELETE
    Observable<Response<ResponseBody>> processDeleteRequest(@Url String urlPath, @HeaderMap HashMap<String, String> heafermap);


}
