package com.example.android_api_client.ui.rest.restRequest;

import android.util.Log;

import com.example.android_api_client.db.DbHelper;
import com.example.android_api_client.db.prefs.PreferencesHelper;
import com.example.android_api_client.di.qualifier.bodyResponse;
import com.example.android_api_client.di.qualifier.headerResponse;
import com.example.android_api_client.model.History;
import com.example.android_api_client.network.ApiHelper;
import com.example.android_api_client.ui.base.BaseViewModel;
import com.example.android_api_client.ui.rest.RestActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.example.android_api_client.ui.rest.restRequest.RestFragment.TAG;

public class RestRequestViewModel extends BaseViewModel<RestResquestNavigtor> {


    @Inject
    @bodyResponse
    String bodyResponse;
    @Inject
    @headerResponse
    String headerResponse;

    @Inject
    RestActivity restActivity;
    String ms;
    String code;


    public ApiHelper apiHelper;
    public DbHelper dbHelper;
    public PreferencesHelper preferencesHelper;

    public RestRequestViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
       super(apiHelper, dbHelper, preferencesHelper);
        this.apiHelper = apiHelper;
        this.dbHelper =  dbHelper;
        this.preferencesHelper = preferencesHelper;
    }

    public void insertHistotyData(History history){
        dbHelper.insert(history);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void processGetRequest(String url, HashMap<String, String> headermap) {

//        restActivity.showLoading();


        apiHelper.processGetRequest(url, headermap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {


                        ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                        code = String.valueOf(responseBodyResponse.code());


                        Log.d("ms", "onNext: " + ms);


                        try {


                            ResponseBody s = responseBodyResponse.body();
                            String bodyString = s.string();

                           // String prettyJsonString = new JSONObject(bodyString).toString(4);

//////

//                            Gson gson = new GsonBuilder()
//
//                                    .setPrettyPrinting()
//                                    .setLenient()
//                                    .create();
//
//                            JsonParser jp = new JsonParser();
//                            JsonElement je = jp.parse(responseBodyResponse.body().string());
//                            String prettyJsonString = gson.toJson(je);

//                            Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().create();
//                            String bodyString = gson.toJson(responseBodyResponse.body());


                            Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                            getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);

                            // restActivity.hideLoading();

                        } catch (Exception e) {
                            getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                        if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                            getNavigator().processErrorResult("invalid url", code, ms);
                            return;
                        }
                        Log.d(TAG, "onError: " + e.getCause());
                        getNavigator().processErrorResult(e.getMessage(), code, ms);

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void processBodywithRaw(String url, HashMap<String, String> headerModelList, String body) {

        if (url != null && body != null) {
            try {

                JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
                apiHelper.processBodyWithRaw(url, headerModelList, jsonObject)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Response<ResponseBody>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                getCompositeDisposable().add(d);
                            }

                            @Override
                            public void onNext(Response<ResponseBody> responseBodyResponse) {

                                ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                                code = String.valueOf(responseBodyResponse.code());

                                try {
                                    if (responseBodyResponse.isSuccessful()) {
                                        ResponseBody s = responseBodyResponse.body();
                                        String bodyString = s.string();
//                                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                                        JsonParser jp = new JsonParser();
//                                        JsonElement je = jp.parse(responseBodyResponse.body().string());
//                                        String prettyJsonString = gson.toJson(je);
                                        Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                        getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);
                                    } else {

                                        String errorString = responseBodyResponse.errorBody().string();
                                        Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                        getNavigator().processSuccessResult(errorString, headers.toString(), code, ms);

                                        Log.d(TAG, "onNext: ");
                                    }

                                } catch (Exception e) {
                                    getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onError(Throwable e) {


                                if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                    getNavigator().processErrorResult("invalid url", code, ms);
                                    return;
                                }
                                Log.d(TAG, "onError: " + e.getCause());
                                getNavigator().processErrorResult(e.getMessage(), code, ms);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            } catch (JsonSyntaxException e) {
                getNavigator().processErrorResult("invalid raw body", "", "");
            }

        }


    }

    public void processBodyWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList) {

        if (url != null && bodyList != null) {


            apiHelper.processBodyWithKey(url, headerModelList, bodyList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(Response<ResponseBody> responseBodyResponse) {

                            ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                            code = String.valueOf(responseBodyResponse.code());

                            try {
                                if (responseBodyResponse.isSuccessful()) {
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();

//                                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                                    JsonParser jp = new JsonParser();
//                                    JsonElement je = jp.parse(responseBodyResponse.body().string());
//                                    String prettyJsonString = gson.toJson(je);
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);
                                } else {

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString(), code, ms);

                                    Log.d(TAG, "onNext: ");
                                }


                                // restActivity.hideLoading();

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url", code, ms);
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage(), code, ms);

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void processPutWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList) {

        if (url != null && bodyList != null) {
            apiHelper.processPutWithKey(url, headerModelList, bodyList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(Response<ResponseBody> responseBodyResponse) {

                            ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                            code = String.valueOf(responseBodyResponse.code());

                            try {
                                if (responseBodyResponse.isSuccessful()) {
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();
//                                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                                    JsonParser jp = new JsonParser();
//                                    JsonElement je = jp.parse(responseBodyResponse.body().string());
//                                    String prettyJsonString = gson.toJson(je);
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);
                                } else {

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString(), code, ms);

                                    Log.d(TAG, "onNext: ");
                                }


                                // restActivity.hideLoading();

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url", code, ms);
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage(), code, ms);

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void processPutwithRaw(String url, HashMap<String, String> headerModelList, String body) {
        try {
            if (url != null && body != null) {
                JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
                apiHelper.processPutWithRaw(url, headerModelList, jsonObject)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Response<ResponseBody>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                getCompositeDisposable().add(d);
                            }

                            @Override
                            public void onNext(Response<ResponseBody> responseBodyResponse) {


                                ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                                code = String.valueOf(responseBodyResponse.code());
                                try {
                                    if (responseBodyResponse.isSuccessful()) {
                                        ResponseBody s = responseBodyResponse.body();
                                        String bodyString = s.string();
//                                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                                        JsonParser jp = new JsonParser();
//                                        JsonElement je = jp.parse(responseBodyResponse.body().string());
//                                        String prettyJsonString = gson.toJson(je);
                                        Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                        getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);
                                        Log.d(TAG, "onNext: " + bodyString);
                                    } else {

                                        String errorString = responseBodyResponse.errorBody().string();
                                        Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                        getNavigator().processSuccessResult(errorString, headers.toString(), code, ms);

                                        Log.d(TAG, "onNext: " + errorString);
                                    }


                                    // restActivity.hideLoading();

                                } catch (Exception e) {
                                    getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onError(Throwable e) {


                                if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                    getNavigator().processErrorResult("invalid url", code, ms);
                                    return;
                                }
                                Log.d(TAG, "onError: " + e.getCause());
                                getNavigator().processErrorResult(e.getMessage(), code, ms);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        } catch (JsonSyntaxException e) {

            getNavigator().processErrorResult("invalid raw body","","");
        }

    }

    public void processPatchWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList) {

        if (url != null && bodyList != null) {
            apiHelper.processPatchWithKey(url, headerModelList, bodyList)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Response<ResponseBody>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            getCompositeDisposable().add(d);
                        }

                        @Override
                        public void onNext(Response<ResponseBody> responseBodyResponse) {

                            ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                            code = String.valueOf(responseBodyResponse.code());

                            try {
                                if (responseBodyResponse.isSuccessful()) {
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();

//                                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                                    JsonParser jp = new JsonParser();
//                                    JsonElement je = jp.parse(responseBodyResponse.body().string());
//                                    String prettyJsonString = gson.toJson(je);
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);
                                } else {

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString(), code, ms);

                                    Log.d(TAG, "onNext: ");
                                }


                                // restActivity.hideLoading();

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url", code, ms);
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage(), code, ms);

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void processPatchwithRaw(String url, HashMap<String, String> headerModelList, String body) {

        try{
            if (url != null && body != null) {
                JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
                apiHelper.processPatchWithRaw(url, headerModelList, jsonObject)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Response<ResponseBody>>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                getCompositeDisposable().add(d);
                            }

                            @Override
                            public void onNext(Response<ResponseBody> responseBodyResponse) {

                                ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                                code = String.valueOf(responseBodyResponse.code());

                                try {
                                    if (responseBodyResponse.isSuccessful()) {
                                        ResponseBody s = responseBodyResponse.body();
                                        String bodyString = s.string();

//                                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                                        JsonParser jp = new JsonParser();
//                                        JsonElement je = jp.parse(responseBodyResponse.body().string());
//                                        String prettyJsonString = gson.toJson(je);
                                        Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                        getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);
                                        Log.d(TAG, "onNext: " + bodyString);
                                    } else {

                                        String errorString = responseBodyResponse.errorBody().string();
                                        Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                        getNavigator().processSuccessResult(errorString, headers.toString(), code, ms);

                                        Log.d(TAG, "onNext: " + errorString);
                                    }


                                    // restActivity.hideLoading();

                                } catch (Exception e) {
                                    getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onError(Throwable e) {


                                if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                    getNavigator().processErrorResult("invalid url", code, ms);
                                    return;
                                }
                                Log.d(TAG, "onError: " + e.getCause());
                                getNavigator().processErrorResult(e.getMessage(), code, ms);

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        }catch (JsonSyntaxException e){
            getNavigator().processErrorResult("invalid raw body","","");
        }

    }

    public void processDeleteRequest(String url, HashMap<String, String> headermap) {

//        restActivity.showLoading();

        apiHelper.processDeleteRequest(url, headermap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {


                        ms = responseBodyResponse.raw().receivedResponseAtMillis() - responseBodyResponse.raw().sentRequestAtMillis() + " ms";
                        code = String.valueOf(responseBodyResponse.code());
                        try {
                            ResponseBody s = responseBodyResponse.body();
                            String bodyString = s.string();
//                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                            JsonParser jp = new JsonParser();
//                            JsonElement je = jp.parse(responseBodyResponse.body().string());
//                            String prettyJsonString = gson.toJson(je);
                            Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                            getNavigator().processSuccessResult(bodyString, headers.toString(), code, ms);

                            // restActivity.hideLoading();

                        } catch (Exception e) {
                            getNavigator().processErrorResult("No Response. Please check the url.", code, ms);
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                        if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                            getNavigator().processErrorResult("invalid url", code, ms);
                            return;
                        }
                        Log.d(TAG, "onError: " + e.getCause());
                        getNavigator().processErrorResult(e.getMessage(), code, ms);

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return super.getCompositeDisposable();
    }

    @Override
    public void setNavigator(RestResquestNavigtor navigator) {
        super.setNavigator(navigator);
    }


    @Override
    public RestResquestNavigtor getNavigator() {
        return super.getNavigator();
    }


}
