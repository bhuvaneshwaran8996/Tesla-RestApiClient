package com.example.tesla_restapiclient.ui.rest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.ActivityRestBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.ui.base.BaseActivity;
import com.example.tesla_restapiclient.ui.splash.SplashViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RestActivity extends BaseActivity<ActivityRestBinding, RestViewModel> implements RestNavigator {

    public RestViewModel restViewModel;

    @Inject
    ViewModelProviderFactory factory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(restViewModel != null){



        }

    }

    @Override
    protected void performDependencyInjection() {

        AndroidInjection.inject(this);
    }

    @Override
    protected int getViewModelVariable() {

        return BR.viewModel;
    }

    @Override
    protected RestViewModel getViewModel() {
        restViewModel = ViewModelProviders.of(this,factory).get(RestViewModel.class);
        restViewModel.setNavigator(this);
        return restViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_rest;
    }

    @Override
    public void fuckKaviya() {


    }
}