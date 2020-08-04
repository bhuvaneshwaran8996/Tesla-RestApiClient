package com.example.tesla_restapiclient.ui.rest.restRequest;

import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentRestBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.model.Body;
import com.example.tesla_restapiclient.ui.base.BaseFragment;
import com.example.tesla_restapiclient.ui.body.BodyRecyclerAdapter;
import com.example.tesla_restapiclient.ui.body.DialogBody;
import com.example.tesla_restapiclient.ui.header.HeaderFragment;
import com.example.tesla_restapiclient.ui.header.HeaderModel;
import com.example.tesla_restapiclient.ui.header.HeadersAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


public class RestFragment extends BaseFragment<FragmentRestBinding, RestRequestViewModel> implements RestResquestNavigtor {

    public static final String TAG = "RestFragment";

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
    String selectedRequestType;
    int checkSelection = 0;
    String SelectedHttp;
    String url;
    String bodyText;


    List<Body> bodyList;

    public RestFragment() {
        // Required empty public constructor
        headerModelList = new ArrayList<>();
        bodyList = new ArrayList<>();
        ;
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
        outState.putString("bodyString",binding.editBody.getText().toString().trim());


    }

    public static RestFragment newInstance() {
        RestFragment fragment = new RestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentRestBinding = getBinding();
        viewModel.setNavigator(this);

        if (savedInstanceState != null) {

            httpdata = (ArrayList<String>) savedInstanceState.get("spinnerhttp");
            spinnerdata = (ArrayList<String>) savedInstanceState.get("spinner");
            headerModelList = (List<HeaderModel>) savedInstanceState.get("headerlist");
            bodyList = (List<Body>) savedInstanceState.get("bodylist");
            checkSelection = savedInstanceState.getInt("checkOrKey");
            url = savedInstanceState.getString("url");
            bodyText = savedInstanceState.getString("bodyString");
        }
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
        if(bodyText !=null && !bodyText.isEmpty()){
            binding.editBody.setText(bodyText);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rest;
    }

    @Override
    public void fuckKaviya() { //navigator

    }

    public class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {


                case R.id.lnrfooter:


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

    private void processRequest(String selectedRequestType) {

        switch (selectedRequestType) {

            case "GET":

                if(binding.keycheckbox.isChecked()){


                }


                break;


            case "POST":

                break;
        }
    }


    public void initHeaderAdapter() {
        binding.recyclerHeader.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerHeader.setHasFixedSize(true);
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
        binding.recyclerbody.setHasFixedSize(true);
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
            if (headerModelList != null && headerModelList.size() > 0) {
                binding.recyclerHeader.setVisibility(View.VISIBLE);
                headerAdapter.setHeaderList(headerModelList);
            } else {
                binding.recyclerHeader.setVisibility(View.GONE);
            }


        }
    }
}