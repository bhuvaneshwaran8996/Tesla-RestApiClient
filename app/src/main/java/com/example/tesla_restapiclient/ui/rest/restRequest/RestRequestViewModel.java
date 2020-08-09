package com.example.tesla_restapiclient.ui.rest.restRequest;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableBoolean;

import com.example.tesla_restapiclient.db.DbHelper;
import com.example.tesla_restapiclient.db.prefs.PreferencesHelper;
import com.example.tesla_restapiclient.di.qualifier.bodyResponse;
import com.example.tesla_restapiclient.di.qualifier.headerResponse;
import com.example.tesla_restapiclient.network.ApiHelper;
import com.example.tesla_restapiclient.ui.base.BaseViewModel;
import com.example.tesla_restapiclient.ui.rest.RestActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static com.example.tesla_restapiclient.ui.rest.restRequest.RestFragment.TAG;

public class RestRequestViewModel extends BaseViewModel<RestResquestNavigtor> {





    @Inject
    @bodyResponse
    String bodyResponse;
    @Inject
    @headerResponse
    String headerResponse;

    @Inject
    RestActivity restActivity;


   public ApiHelper apiHelper;
   public DbHelper dbHelper;
   public  PreferencesHelper preferencesHelper;
    public RestRequestViewModel(ApiHelper apiHelper, DbHelper dbHelper, PreferencesHelper preferencesHelper) {
        super(apiHelper, dbHelper, preferencesHelper);
        this.apiHelper = apiHelper;
        this.dbHelper = dbHelper;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void processGetRequest(String url, HashMap<String, String> headermap){

//        restActivity.showLoading();

        apiHelper.processGetRequest(url,headermap)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<ResponseBody>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        getCompositeDisposable().add(d);
                    }

                    @Override
                    public void onNext(Response<ResponseBody> responseBodyResponse) {


                        try {
                            ResponseBody s = responseBodyResponse.body();
                            String bodyString = s.string();
                            Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                            getNavigator().processSuccessResult(bodyString,headers.toString());

                            // restActivity.hideLoading();

                        } catch (Exception e) {
                            getNavigator().processErrorResult("No Response. Please check the url.");
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                        if(e instanceof  IllegalArgumentException ||e instanceof UnknownHostException){
                            getNavigator().processErrorResult("invalid url");
                            return;
                        }
                        Log.d(TAG, "onError: "+e.getCause());
                        getNavigator().processErrorResult(e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });



    }

    public void processBodywithRaw(String url, HashMap<String, String> headerModelList, String body){

        if (url != null && body != null) {
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


                            try {
                                if(responseBodyResponse.isSuccessful()){
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString());
                                }else{

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString());

                                    Log.d(TAG, "onNext: ");
                                }

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.");
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url");
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
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


                            try {
                                if(responseBodyResponse.isSuccessful()){
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString());
                                }else{

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString());

                                    Log.d(TAG, "onNext: ");
                                }


                                // restActivity.hideLoading();

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.");
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url");
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void processPutWithKey(String url, HashMap<String, String> headerModelList, Map<String, String> bodyList){

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


                            try {
                                if(responseBodyResponse.isSuccessful()){
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString());
                                }else{

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString());

                                    Log.d(TAG, "onNext: ");
                                }


                                // restActivity.hideLoading();

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.");
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url");
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }
    public void processPutwithRaw(String url, HashMap<String, String> headerModelList, String body) {

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


                            try {
                                if (responseBodyResponse.isSuccessful()) {
                                    ResponseBody s = responseBodyResponse.body();
                                    String bodyString = s.string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(bodyString, headers.toString());
                                } else {

                                    String errorString = responseBodyResponse.errorBody().string();
                                    Headers headers = responseBodyResponse.headers();
//                            String headerString = new Gson().toJson(headers);
                                    getNavigator().processSuccessResult(errorString, headers.toString());

                                    Log.d(TAG, "onNext: ");
                                }


                                // restActivity.hideLoading();

                            } catch (Exception e) {
                                getNavigator().processErrorResult("No Response. Please check the url.");
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onError(Throwable e) {


                            if (e instanceof IllegalArgumentException || e instanceof UnknownHostException) {
                                getNavigator().processErrorResult("invalid url");
                                return;
                            }
                            Log.d(TAG, "onError: " + e.getCause());
                            getNavigator().processErrorResult(e.getMessage());

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
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
