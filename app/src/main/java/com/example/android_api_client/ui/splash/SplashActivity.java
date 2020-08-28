package com.example.android_api_client.ui.splash;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android_api_client.BR;
import com.example.android_api_client.R;
import com.example.android_api_client.databinding.ActivitySplashBinding;
import com.example.android_api_client.di.ViewModelProviderFactory;
import com.example.android_api_client.ui.base.BaseActivity;
import com.example.android_api_client.ui.rest.RestActivity;

import javax.inject.Inject;

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