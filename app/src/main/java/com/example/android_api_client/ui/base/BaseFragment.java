package com.example.android_api_client.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import dagger.android.support.DaggerFragment;


public abstract  class BaseFragment<T extends ViewDataBinding, N extends ViewModel> extends DaggerFragment {

    public T binding;
    public N viewmodel;
    public BaseActivity baseActivity;
    public View rootView;



    public T getBinding() {
        return binding;
    }

    public void setBinding(T binding) {
        this.binding = binding;
    }

    public abstract N getViewmodel() ;


    public void setViewmodel(N viewmodel) {
        this.viewmodel = viewmodel;
    }

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        //performDependencyInjection();
        super.onAttach(context);
        if(context instanceof  BaseActivity){
            baseActivity = (BaseActivity)context;
            baseActivity.onFragmentAttached();

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
      //  performDependencyInjection();
        super.onCreate(savedInstanceState);

       viewmodel = getViewmodel();
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setVariable(getViewModel(),viewmodel);
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }


    protected abstract int getViewModel();






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false);
        rootView = binding.getRoot();
        return rootView;

    }


    public BaseActivity getBaseActivity(){
        return baseActivity;
    }

    protected abstract int getLayoutId();

    public void hideKeyboard() throws NullPointerException {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission)  throws NullPointerException{
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
               getActivity().checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }



    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        baseActivity = null;

    }
}