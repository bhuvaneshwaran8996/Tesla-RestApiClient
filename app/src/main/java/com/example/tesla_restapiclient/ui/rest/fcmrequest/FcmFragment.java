package com.example.tesla_restapiclient.ui.rest.fcmrequest;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentFcmBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.ui.base.BaseFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjection;

public class FcmFragment extends BaseFragment<FragmentFcmBinding,FcmViewModel> implements FcmNavigator {

    FragmentFcmBinding fragmentFcmBinding;

    FcmViewModel fcmViewModel;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    public FcmFragment() {
        // Required empty public constructor
    }

    @Override
    public FcmViewModel getViewmodel() {
        fcmViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(FcmViewModel.class);
        return fcmViewModel;
    }

    @Override
    protected int getViewModel() {
        return BR.viewModel;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fcm;
    }

    public static FcmFragment newInstance() {
        FcmFragment fragment = new FcmFragment();
        Bundle args = new Bundle();


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fcmViewModel.setNavigator(this);



    }

    @Override
    public void fuckKaviya() {

    }
}