package com.example.tesla_restapiclient.ui.splash;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tesla_restapiclient.BR;
import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.ActivitySplashBinding;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.ui.base.BaseActivity;
import com.example.tesla_restapiclient.ui.base.BaseViewModel;
import com.example.tesla_restapiclient.ui.rest.RestActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> implements SplashNavigator {

    @Inject
    Context context;
    public SplashViewModel splashViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if(splashViewModel!=null){

            splashViewModel.setNavigator(this);
            splashViewModel.waitInSplash();
        }

    }
//
//    @Override
//    protected void performDependencyInjection() {
//
//        AndroidInjection.inject(this);
//    }

    @Override
    protected int getViewModelVariable() {
        return BR.viewModel;
    }

    @Override
    protected SplashViewModel getViewModel() {
        splashViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(SplashViewModel.class);
        return splashViewModel;
    }



    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void navigateToRestActivity() {

       startActivity(new Intent(SplashActivity.this, RestActivity.class));
       finish();
    }


}