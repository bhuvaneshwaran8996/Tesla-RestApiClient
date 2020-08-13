package com.example.tesla_restapiclient.ui.rest.restRequest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentRestBinding;
import com.example.tesla_restapiclient.db.AppDatabase;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.di.qualifier.bodyResponse;
import com.example.tesla_restapiclient.di.qualifier.headerResponse;
import com.example.tesla_restapiclient.model.Body;
import com.example.tesla_restapiclient.model.History;
import com.example.tesla_restapiclient.ui.base.BaseFragment;
import com.example.tesla_restapiclient.ui.body.BodyRecyclerAdapter;
import com.example.tesla_restapiclient.ui.body.DialogBody;
import com.example.tesla_restapiclient.ui.header.HeaderFragment;
import com.example.tesla_restapiclient.ui.header.HeaderModel;
import com.example.tesla_restapiclient.ui.header.HeadersAdapter;
import com.example.tesla_restapiclient.ui.rest.RestActivity;
import com.example.tesla_restapiclient.utils.CommonUtils;
import com.example.tesla_restapiclient.utils.NetworkUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import javax.inject.Inject;


public class RestFragment extends BaseFragment<FragmentRestBinding, RestRequestViewModel> implements RestResquestNavigtor {

    public static final String TAG = "RestFragment";

    @Inject
    AppDatabase appDatabase;
    @Inject
    RestActivity restActivity;
    public ArrayAdapter<String> arrayAdapter;
    public ArrayAdapter<String> arrayAdapterhttp;
    List<String> spinnerdata;
    List<String> httpdata;
    List<HeaderModel> headerModelList;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    FragmentRestBinding fragmentRestBinding;
    RestRequestViewModel viewModel;
    @Inject
    HeadersAdapter headerAdapter;

    @Inject
    BodyRecyclerAdapter bodyRecyclerAdapter;
    Map<String, String> headermap = new HashMap<>();
    List<String> headerList = new ArrayList<>();
    String selectedRequestType = "GET";
    int checkSelection = 0;
    String SelectedHttp;
    String url;
    String bodyText;

    String body;
    String header;
    int postion;

    List<Body> bodyList;

    public RestFragment() {
        // Required empty public constructor
        headerModelList = new ArrayList<>();
        bodyList = new ArrayList<>();

    }

    @Override
    protected int getViewModel() {
        return BR.viewModel;
    }


    @Override
    public RestRequestViewModel getViewmodel() {
        viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(RestRequestViewModel.class);
        return viewModel;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("spinner", (ArrayList<String>) spinnerdata);
        outState.putStringArrayList("spinnerhttp", (ArrayList<String>) httpdata);
        outState.putSerializable("headerlist", (Serializable) headerModelList);
        outState.putSerializable("bodylist", (Serializable) bodyList);
        outState.putInt("checkOrKey", checkSelection);
        outState.putString("url", binding.url.getText().toString().trim());
        outState.putString("bodyString", binding.editBody.getText().toString().trim());
        outState.putString("body", body);
        outState.putString("header", header);


    }

    public static RestFragment newInstance() {
        RestFragment fragment = new RestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRestBinding = getBinding();
        viewModel.setNavigator(this);
        setRetainInstance(true);
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mMessageReceiver,
                new IntentFilter("history"));


        if (savedInstanceState != null) {

            httpdata = (ArrayList<String>) savedInstanceState.get("spinnerhttp");
            spinnerdata = (ArrayList<String>) savedInstanceState.get("spinner");
            headerModelList = (List<HeaderModel>) savedInstanceState.get("headerlist");
            bodyList = (List<Body>) savedInstanceState.get("bodylist");
            checkSelection = savedInstanceState.getInt("checkOrKey");
            url = savedInstanceState.getString("url");
            bodyText = savedInstanceState.getString("bodyString");
            body = savedInstanceState.getString("body");
            header = savedInstanceState.getString("header");

        }

    }


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String requestUrl;
            History historyModel = (History) intent.getSerializableExtra("historyModel");
            Log.d("receiver", "Got message: " + historyModel);
            if (historyModel != null) {

                if(!historyModel.requestUrl.equalsIgnoreCase("")){
                    boolean containshttp = historyModel.requestUrl.contains("http://");
                    boolean containshttps = historyModel.requestUrl.contains("https://");
                    if(containshttp){

                         requestUrl = historyModel.requestUrl.split("http://")[1];
                         binding.spinnerHttp.setSelection(0);
                         binding.url.setText(requestUrl);

                    }else{
                        requestUrl = historyModel.requestUrl.split("https://")[1];
                        binding.spinnerHttp.setSelection(1);
                        binding.url.setText(requestUrl);
                    }


                }
                boolean contains = spinnerdata.contains(historyModel.requestType);
                if (contains) {
                    int i = spinnerdata.indexOf(historyModel.requestType);
                    binding.spinner.setSelection(i);

                }
                if (!historyModel.requestType.equalsIgnoreCase("GET") || !historyModel.requestType.equalsIgnoreCase("DELETE")) {

                    if (historyModel.keyOrraw!=null&&historyModel.keyOrraw.equalsIgnoreCase("raw")) {

                        binding.rawcheckbox.performClick();
//                       binding.keycheckbox.setChecked(false);
                        if (!historyModel.rawBody.equalsIgnoreCase("")) {
                            binding.editBody.setText(historyModel.rawBody);
                        }

                    } else {
//                       binding.rawcheckbox.setChecked(false);
                        List<Body> bodyAdapterList = new ArrayList<>();
                        binding.keycheckbox.performClick();
                        String keyBody = historyModel.keyBody;
                        if (bodyRecyclerAdapter != null) {

                            binding.recyclerbody.setVisibility(View.VISIBLE);
                            try {
                                Map<String, String> stringStringHashMap = jsonToMap(keyBody);
                                Iterator<Map.Entry<String, String>> iterator = stringStringHashMap.entrySet().iterator();
//                                while (iterator.hasNext()) {
////                               bodyAdapterList.add(iterator.next().getValue());
////                               bodyRecyclerAdapter.setNewList(bodyAdapterList);
//
//                                    bodyAdapterList.add(new Body(iterator.next().getKey(), iterator.next().getValue()));
//
//                                }
                                for(Map.Entry<String, String> entry : stringStringHashMap.entrySet()) {
                                    String key = entry.getKey();
                                    String value = entry.getValue();
                                    bodyAdapterList.add(new Body(key,value));


                                }
                                bodyRecyclerAdapter.setNewList(bodyAdapterList);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {


                            bodyRecyclerAdapter.emptyBody();
                            binding.recyclerbody.setVisibility(View.GONE);
                        }


                    }
                }

                if (!historyModel.header.equalsIgnoreCase("")) {

                    try {
                        List<HeaderModel> headerModelList = new ArrayList<>();
                        Map<String, String> stringStringMap = jsonToMap(historyModel.header);
                     // Iterator<Map.Entry<String, String>> stringStringHashMap = stringStringMap.entrySet().iterator();
//                        while (iterator.hasNext()) {
////                               bodyAdapterList.add(iterator.next().getValue());
////                               bodyRecyclerAdapter.setNewList(bodyAdapterList);
//
//                            headerModelList.add(new HeaderModel(iterator.next().getKey(), iterator.next().getValue()));
//                            if (headerAdapter != null) {
//                                headerAdapter.setHeaderList(headerModelList);
//                            }
//
//                        }
                        for(Map.Entry<String, String> entry : stringStringMap.entrySet()) {
                            String key = entry.getKey();
                            String value = entry.getValue();
                            headerModelList.add(new HeaderModel(key,value));


                        }
                        binding.recyclerHeader.setVisibility(View.VISIBLE);
                        headerAdapter.setHeaderList(headerModelList);
                    } catch (JSONException e) {

                        e.printStackTrace();
                    }

                } else {

                    if (headerAdapter != null) {
                        headerAdapter.emptyHeader();
                        binding.recyclerHeader.setVisibility(View.GONE);
                    }
                }

            }

        }
    };

    public Map<String, String> jsonToMap(String t) throws JSONException {
        Map<String, String> map = new HashMap<String, String>();
        if(t != null) {

            JSONObject jObject = new JSONObject(t);
            Iterator<?> keys = jObject.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                String value = jObject.getString(key);
                map.put(key, value);

            }

            System.out.println("json : " + jObject);
            System.out.println("map : " + map);

        }
        return map;
    }

@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner spinner = view.findViewById(R.id.spinner);


        if (spinnerdata == null) {
            spinnerdata = new ArrayList<>();
            spinnerdata.add("GET");
            spinnerdata.add("POST");
            spinnerdata.add("PUT");
            spinnerdata.add("PATCH");
            spinnerdata.add("DELETE");

        }
        if (httpdata == null) {
            httpdata = new ArrayList<>();
            httpdata.add("http://");
            httpdata.add("https://");
        }

        initHeaderAdapter();
        initBodyAdapter();
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, spinnerdata);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view;
                if (textView != null)
                    textView.setTextColor(getResources().getColor(R.color.orange));
                selectedRequestType = spinnerdata.get(position);
                if (selectedRequestType.equalsIgnoreCase("GET") || selectedRequestType.equalsIgnoreCase("DELETE")) {

                    binding.lnrInnerBody.setVisibility(View.GONE);
                } else {
                    binding.lnrInnerBody.setVisibility(View.VISIBLE);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        arrayAdapterhttp = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item, httpdata);
        binding.spinnerHttp.setAdapter(arrayAdapterhttp);
        spinner.setSelection(0);


        binding.spinnerHttp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view;
                if (textView != null)
                    textView.setTextColor(getResources().getColor(R.color.orange));
                SelectedHttp = httpdata.get(position);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        binding.keycheckbox.setOnClickListener(new OnClick());
        binding.rawcheckbox.setOnClickListener(new OnClick());
        binding.txtAddheader.setOnClickListener(new OnClick());
        binding.txtAddbody.setOnClickListener(new OnClick());
        binding.lnrfooter.setOnClickListener(new OnClick());
        binding.txtAddbody.setOnClickListener(new OnClick());
        if (checkSelection == 0) {
            binding.rawcheckbox.setChecked(true);
            binding.rawcheckbox.performClick();

        } else if (checkSelection == 1) {
            binding.keycheckbox.setChecked(true);
            binding.keycheckbox.performClick();
        }
        if (url != null && !url.isEmpty()) {
            binding.url.setText(url);
        }
        if (bodyText != null && !bodyText.isEmpty()) {
            binding.editBody.setText(bodyText);
        }
        if (selectedRequestType != null && selectedRequestType.equalsIgnoreCase("GET") || selectedRequestType.equalsIgnoreCase("DELETE")) {

            binding.lnrInnerBody.setVisibility(View.GONE);
        } else {
            binding.lnrInnerBody.setVisibility(View.VISIBLE);
        }
        binding.setViewModel(viewModel);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rest;
    }


    public class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {


                case R.id.lnrfooter:


                    Log.d(TAG, "onClick: " + SelectedHttp + "" + binding.url.getText().toString().trim());
                    if (bodyRecyclerAdapter != null) {
                        Map<String, String> bodyList = bodyRecyclerAdapter.getBodyList();
                        Log.d(TAG, "onClick: " + bodyList.size());


                    }
                    if (headerAdapter != null) {
                        Map<String, String> headerModelList = headerAdapter.getHeaderModelList();
                        Log.d(TAG, "onClick: " + headerModelList.size());
                    }

                    processRequest(selectedRequestType);

                    break;

                case R.id.txt_addbody:
                    DialogBody dialogBody = DialogBody.newInstance(" ", new BodyCalback());
                    dialogBody.show(getChildFragmentManager(), "");
                    break;

                case R.id.txt_addheader:
                    HeaderFragment headerFragment = HeaderFragment.newInstance(new HeaderCallback(), "");
                    headerFragment.show(getChildFragmentManager(), "");
                    break;

                case R.id.rawcheckbox:
                    binding.keycheckbox.setChecked(false);
                    binding.rawcheckbox.setChecked(true);
                    binding.rlyBodyedt.setVisibility(View.VISIBLE);
                    binding.rlyKey.setVisibility(View.GONE);
                    binding.recyclerKey.setVisibility(View.GONE);
                    checkSelection = 0;
                    break;

                case R.id.keycheckbox:
                    binding.rawcheckbox.setChecked(false);
                    binding.keycheckbox.setChecked(true);
                    binding.rlyBodyedt.setVisibility(View.GONE);
                    binding.rlyKey.setVisibility(View.VISIBLE);
                    binding.recyclerKey.setVisibility(View.VISIBLE);
                    checkSelection = 1;
                    break;
            }
        }
    }

    @Override
    public void processErrorResult(String message, String requestCode, String requestTime) {

        restActivity.bodyResponse = "";
        restActivity.headerResponse = "";
        restActivity.requesttime = requestTime;
        restActivity.requestCode = requestCode;


        restActivity.binding.viewpager.setCurrentItem(1);

        restActivity.setResponseFragmentSuccesRsult();
        restActivity.hideLoading();
        Toast.makeText(restActivity, message, Toast.LENGTH_LONG).show();


    }


    @Override
    public void processSuccessResult(String body, String header, String requestCode, String requestTime) {


        try {
            this.body = body;
            this.header = header;
            if (restActivity != null) {
                restActivity.headerResponse = header;
                restActivity.bodyResponse = body;
                restActivity.requestCode = requestCode;
                restActivity.requesttime = requestTime;
            }
            restActivity.setResponseFragmentSuccesRsult();
            restActivity.binding.viewpager.setCurrentItem(1);


            History history = new History();
            history.requestType = selectedRequestType;
            history.requestUrl = SelectedHttp + "" + binding.url.getText().toString().trim();
            history.isRest = true;
            history.createdAt = CommonUtils.getDateTime();
            if(binding.lnrInnerBody.getVisibility() == View.VISIBLE && binding.rawcheckbox.isChecked()  ){
                history.keyOrraw = "raw";
                history.keyBody = "";
                history.rawBody = binding.editBody.getText().toString();

            }else if(binding.lnrInnerBody.getVisibility() == View.VISIBLE && binding.keycheckbox.isChecked()){
                history.keyOrraw = "key";
                history.rawBody = "";
                history.keyBody = convertBodyMapToString(bodyRecyclerAdapter.getBodyList());
            }

            history.header = convertHeaderMapToString(headerAdapter.getHeaderModelList());
            viewModel.insertHistotyData(history);

        } catch (Exception e) {
            e.printStackTrace();
        }
        restActivity.hideLoading();


    }

    private String convertHeaderMapToString(Map<String, String> bodyList) {

        if(bodyList==null || bodyList.size() == 0){
            return  "";
        }
        JsonObject jsonObject = new JsonObject();
        Iterator<Map.Entry<String, String>> iterator = bodyList.entrySet().iterator();
//        while (iterator.hasNext()){
//            jsonObject.addProperty(iterator.next().getKey(),iterator.next().getValue());
//        }

        for(Map.Entry<String, String> entry : bodyList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            jsonObject.addProperty(key, value);
            // Print it
        }
        String bodyKey = new Gson().toJson(jsonObject);
        return bodyKey;


    }
    private String convertBodyMapToString(Map<String, String> bodyList) {

        if(bodyList==null || bodyList.size() == 0){
            return  "";
        }
        JsonObject jsonObject = new JsonObject();
        Iterator<Map.Entry<String, String>> iterator = bodyList.entrySet().iterator();
//        while (iterator.hasNext()){
//            jsonObject.addProperty(iterator.next().getKey(),iterator.next().getValue());
//        }

        for(Map.Entry<String, String> entry : bodyList.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            jsonObject.addProperty(key, value);
            // Print it
        }
        String bodyKey = new Gson().toJson(jsonObject);
        return bodyKey;

    }


    private void processRequest(String selectedRequestType) {

        restActivity.showLoading();
        switch (selectedRequestType) {


            case "GET":
                if (NetworkUtils.isNetworkConnected(restActivity)) {

                    viewModel.processGetRequest(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList());
                } else {
                    Toast.makeText(restActivity, "You're offline. Make sure the device is connected to the network", Toast.LENGTH_LONG).show();
                    restActivity.hideLoading();
                }

                break;


            case "POST":


                if (NetworkUtils.isNetworkConnected(restActivity)) {
                    if (binding.keycheckbox.isChecked()) {
                        if (bodyRecyclerAdapter.getBodyList() == null || bodyRecyclerAdapter.getBodyList().size() == 0) {
                            Toast.makeText(restActivity, "Please type the body Key/Value pair", Toast.LENGTH_LONG).show();
                            restActivity.hideLoading();
                            return;
                        }
                        viewModel.processBodyWithKey(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList(), bodyRecyclerAdapter.getBodyList());
                      //  restActivity.hideLoading();

                    } else {
                        if (binding.rawcheckbox.isChecked()) {
                            if (binding.editBody.getText().toString().isEmpty()) {
                                Toast.makeText(restActivity, "Please enter the raw body ", Toast.LENGTH_LONG).show();
                                restActivity.hideLoading();
                                return;
                            }
                            viewModel.processBodywithRaw(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList(), binding.editBody.getText().toString());
                          //  restActivity.hideLoading();
                        }

                    }
                } else {
                    Toast.makeText(restActivity, "You're offline. Make sure the device is connected to the network", Toast.LENGTH_LONG).show();
                    restActivity.hideLoading();
                }

                break;
            case "PUT":
                if (NetworkUtils.isNetworkConnected(restActivity)) {
                    if (binding.keycheckbox.isChecked()) {
                        if (bodyRecyclerAdapter.getBodyList() == null || bodyRecyclerAdapter.getBodyList().size() == 0) {
                            Toast.makeText(restActivity, "Please type the body Key/Value pair", Toast.LENGTH_LONG).show();
                            restActivity.hideLoading();
                            return;
                        }
                        viewModel.processPutWithKey(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList(), bodyRecyclerAdapter.getBodyList());
                     //   restActivity.hideLoading();

                    } else {
                        if (binding.rawcheckbox.isChecked()) {
                            if (binding.editBody.getText().toString().isEmpty()) {
                                Toast.makeText(restActivity, "Please enter the raw body ", Toast.LENGTH_LONG).show();
                                restActivity.hideLoading();
                                return;
                            }
                            viewModel.processPutwithRaw(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList(), binding.editBody.getText().toString());
                         //   restActivity.hideLoading();
                        }

                    }
                } else {
                    Toast.makeText(restActivity, "You're offline. Make sure the device is connected to the network", Toast.LENGTH_LONG).show();
                    restActivity.hideLoading();
                }

                break;

            case "PATCH":
                if (NetworkUtils.isNetworkConnected(restActivity)) {
                    if (binding.keycheckbox.isChecked()) {
                        if (bodyRecyclerAdapter.getBodyList() == null || bodyRecyclerAdapter.getBodyList().size() == 0) {
                            Toast.makeText(restActivity, "Please type the body Key/Value pair", Toast.LENGTH_LONG).show();
                            restActivity.hideLoading();
                            return;
                        }
                        viewModel.processPatchWithKey(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList(), bodyRecyclerAdapter.getBodyList());
                     //   restActivity.hideLoading();

                    } else {
                        if (binding.rawcheckbox.isChecked()) {
                            if (binding.editBody.getText().toString().isEmpty()) {
                                Toast.makeText(restActivity, "Please enter the raw body ", Toast.LENGTH_LONG).show();
                                restActivity.hideLoading();
                                return;
                            }
                            viewModel.processPatchwithRaw(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList(), binding.editBody.getText().toString());
                          //  restActivity.hideLoading();
                        }

                    }
                } else {
                    Toast.makeText(restActivity, "You're offline. Make sure the device is connected to the network", Toast.LENGTH_LONG).show();
                    restActivity.hideLoading();
                }
                break;

            case "DELETE":
//                if(NetworkUtils.isNetworkConnected(restActivity)){
//                    if(binding.keycheckbox.isChecked()){
//                        if(bodyRecyclerAdapter.getBodyList() == null  || bodyRecyclerAdapter.getBodyList().size() == 0){
//                            Toast.makeText(restActivity,"Please type the body Key/Value pair",Toast.LENGTH_LONG).show();
//                            restActivity.hideLoading();
//                            return;
//                        }
//                        viewModel.processDeleteRequest(SelectedHttp+""+binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList());
//                        restActivity.hideLoading();
//
//                    }else{
//                        if(binding.rawcheckbox.isChecked()){
//                            if(binding.editBody.getText().toString().isEmpty()){
//                                Toast.makeText(restActivity,"Please enter the raw body ",Toast.LENGTH_LONG).show();
//                                restActivity.hideLoading();
//                                return;
//                            }
//                            viewModel.processPatchwithRaw(SelectedHttp+""+binding.url.getText().toString().trim(),(HashMap<String, String>) headerAdapter.getHeaderModelList(),binding.editBody.getText().toString());
//                            restActivity.hideLoading();
//                        }
//
//                    }
//                }
//
//                else{
//                    Toast.makeText(restActivity,"You're offline. Make sure the device is connected to the network",Toast.LENGTH_LONG).show();
//                    restActivity.hideLoading();
//                }
                if (NetworkUtils.isNetworkConnected(restActivity)) {

                    viewModel.processDeleteRequest(SelectedHttp + "" + binding.url.getText().toString().trim(), (HashMap<String, String>) headerAdapter.getHeaderModelList());

                } else {

                    Toast.makeText(restActivity, "You're offline. Make sure the device is connected to the network", Toast.LENGTH_LONG).show();

                    restActivity.hideLoading();
                }

                break;


        }
    }


    public void initHeaderAdapter() {
        binding.recyclerHeader.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerHeader.setHasFixedSize(false);
        SpaceItemDecorator spaceItemDecorator = new SpaceItemDecorator(15);
        binding.recyclerHeader.addItemDecoration(spaceItemDecorator);
        binding.recyclerHeader.setAdapter(headerAdapter);
        if (headerModelList != null && headerModelList.size() > 0) {
            binding.recyclerHeader.setVisibility(View.VISIBLE);
            headerAdapter.setHeaderList(headerModelList);
        } else {
            binding.recyclerHeader.setVisibility(View.GONE);
        }
    }

    private void initBodyAdapter() {

        binding.recyclerbody.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerbody.setHasFixedSize(false);
        SpaceItemDecorator spaceItemDecorator = new SpaceItemDecorator(15);
        binding.recyclerbody.addItemDecoration(spaceItemDecorator);

        binding.recyclerbody.setAdapter(bodyRecyclerAdapter);
        if (bodyList != null && bodyList.size() > 0) {
            binding.recyclerbody.setVisibility(View.VISIBLE);
            bodyRecyclerAdapter.setNewList(bodyList);
        } else {
            binding.recyclerbody.setVisibility(View.GONE);
        }

    }


    public class BodyCalback implements DialogBody.DialogBodyCallback {

        @Override
        public void dialogBodyCallback(String key, String value) {
            bodyList.add(new Body(key, value));
            if (bodyList != null && bodyList.size() > 0) {
                binding.recyclerbody.setVisibility(View.VISIBLE);
                bodyRecyclerAdapter.setNewList(bodyList);
            } else {
                binding.recyclerbody.setVisibility(View.GONE);
            }


        }

    }

    public class HeaderCallback implements HeaderFragment.IHeaderCallback, Serializable {

        @Override
        public void headerDetails(String type, String value) {

//            headermap.put(type,value);

            headerModelList.add(new HeaderModel(type, value));
//            //RelativeLayout.LayoutParams layoutParams  =(RelativeLayout.LayoutParams) binding.recyclerbody.getLayoutParams();
//            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.WRAP_CONTENT);
         //   binding.recyclerbody.setLayoutParams(params);
            if (headerModelList != null && headerModelList.size() > 0) {
                binding.recyclerHeader.setVisibility(View.VISIBLE);
                headerAdapter.setHeaderList(headerModelList);
            } else {
                binding.recyclerHeader.setVisibility(View.GONE);
            }


        }
    }

    public class SpaceItemDecorator extends RecyclerView.ItemDecoration {

        private final int verticalSpacingHeight;

        public SpaceItemDecorator(int verticalSpacingHeight){
            this.verticalSpacingHeight = verticalSpacingHeight;
        }
        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.bottom = verticalSpacingHeight;
        }

    }

}