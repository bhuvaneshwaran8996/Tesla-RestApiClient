package com.bhuvaneswaran.simple_apiclient.ui.rest.fcmrequest;

import android.os.Bundle;

import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import com.bhuvaneswaran.simple_apiclient.R;
import com.bhuvaneswaran.simple_apiclient.databinding.FragmentFcmBinding;
import com.bhuvaneswaran.simple_apiclient.di.ViewModelProviderFactory;
import com.bhuvaneswaran.simple_apiclient.ui.base.BaseFragment;

import javax.inject.Inject;

public class FcmFragment extends BaseFragment<FragmentFcmBinding, FcmViewModel> implements FcmNavigator {

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
    public void fucmNavigatoe() {

    }
}