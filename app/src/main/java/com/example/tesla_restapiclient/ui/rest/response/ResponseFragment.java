package com.example.tesla_restapiclient.ui.rest.response;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentResponseBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.ui.base.BaseFragment;
import com.example.tesla_restapiclient.ui.header.HeaderFragment;

import javax.inject.Inject;


public class ResponseFragment extends BaseFragment<FragmentResponseBinding,ResponseViewModel> implements ResponseNavigator{



    FragmentResponseBinding fragmentResponseBinding;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    ResponseViewModel responseViewModel;

    @Override
    public ResponseViewModel getViewmodel() {
        responseViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(ResponseViewModel.class);

        return responseViewModel;
    }

    public ResponseFragment() {

    }


    public static ResponseFragment newInstance() {
        ResponseFragment fragment = new ResponseFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentResponseBinding = getBinding();

    }

    @Override
    protected int getViewModel() {
        return BR.viewModel;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_response;
    }

    @Override
    public void responseNavigator() {

    }
}