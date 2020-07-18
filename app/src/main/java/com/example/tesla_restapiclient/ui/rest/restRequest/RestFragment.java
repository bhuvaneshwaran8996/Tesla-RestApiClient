package com.example.tesla_restapiclient.ui.rest.restRequest;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentRestBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.ui.base.BaseFragment;
import com.example.tesla_restapiclient.ui.body.DialogBody;
import com.example.tesla_restapiclient.ui.header.HeaderFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;


public class RestFragment extends BaseFragment<FragmentRestBinding,RestRequestViewModel> implements RestResquestNavigtor{

    public static final String TAG = "RestFragment";

    public ArrayAdapter<String> arrayAdapter;
    public ArrayAdapter<String> arrayAdapterhttp;
    List<String> spinnerdata;
    List<String> httpdata;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    FragmentRestBinding fragmentRestBinding;
    RestRequestViewModel viewModel;
    public RestFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getViewModel() {
        return BR.viewModel;
    }


    @Override
    public RestRequestViewModel getViewmodel() {
        viewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(RestRequestViewModel.class);
        return viewModel;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("spinner", (ArrayList<String>) spinnerdata);
        outState.putStringArrayList("spinnerhttp",(ArrayList<String>)httpdata);

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
        if(savedInstanceState!=null){

            httpdata = (ArrayList<String>) savedInstanceState.get("spinnerhttp");
            spinnerdata = (ArrayList<String>) savedInstanceState.get("spinner");
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Spinner spinner =   view.findViewById(R.id.spinner);


        if(spinnerdata == null) {
            spinnerdata = new ArrayList<>();
            spinnerdata.add("GET");
            spinnerdata.add("POST");
            spinnerdata.add("PUT");
            spinnerdata.add("PATCH");
            spinnerdata.add("DELETE");

        }
        if(httpdata == null){
            httpdata = new ArrayList<>();
            httpdata.add("http://");
            httpdata.add("https://");
        }


        arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,spinnerdata);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(1);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view;
                if(textView!=null)
                    textView.setTextColor(getResources().getColor(R.color.orange));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        arrayAdapterhttp = new ArrayAdapter<String>(getActivity(),R.layout.spinner_item,httpdata);
        binding.spinnerHttp.setAdapter(arrayAdapterhttp);
        spinner.setSelection(1);



        binding.spinnerHttp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view;
                if(textView!=null)
                    textView.setTextColor(getResources().getColor(R.color.orange));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.keycheckbox.setOnClickListener(new OnClick());
        binding.rawcheckbox.setOnClickListener(new OnClick());
        binding.txtAddheader.setOnClickListener(new OnClick());
        binding.txtAddbody.setOnClickListener(new OnClick());



    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_rest;
    }

    @Override
    public void fuckKaviya() {

    }

    public class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.txt_addbody:
                    DialogBody dialogBody = DialogBody.newInstance();
                    dialogBody.show(getChildFragmentManager(),"");
                    break;
                case R.id.txt_addheader:
                    HeaderFragment headerFragment = HeaderFragment.newInstance();
                    headerFragment.show(getChildFragmentManager(),"");
                    break;

                case R.id.rawcheckbox:
                    binding.keycheckbox.setChecked(false);
                    binding.rlyBodyedt.setVisibility(View.VISIBLE);
                    binding.rlyKey.setVisibility(View.GONE);
                    binding.recyclerKey.setVisibility(View.GONE);
                    break;

                case R.id.keycheckbox:
                    binding.rawcheckbox.setChecked(false);

                    binding.rlyBodyedt.setVisibility(View.GONE);
                    binding.rlyKey.setVisibility(View.VISIBLE);
                    binding.recyclerKey.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }
}