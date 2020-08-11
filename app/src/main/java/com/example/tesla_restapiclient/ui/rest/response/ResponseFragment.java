package com.example.tesla_restapiclient.ui.rest.response;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentResponseBinding;
import com.example.tesla_restapiclient.db.AppDatabase;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.di.qualifier.bodyResponse;
import com.example.tesla_restapiclient.di.qualifier.headerResponse;
import com.example.tesla_restapiclient.model.History;
import com.example.tesla_restapiclient.ui.base.BaseFragment;
import com.example.tesla_restapiclient.ui.header.HeaderFragment;
import com.example.tesla_restapiclient.ui.rest.RestActivity;

import java.util.List;

import javax.inject.Inject;


public class ResponseFragment extends BaseFragment<FragmentResponseBinding,ResponseViewModel> implements ResponseNavigator{



    FragmentResponseBinding fragmentResponseBinding;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    ResponseViewModel responseViewModel;
    @Inject
    @com.example.tesla_restapiclient.di.qualifier.bodyResponse
    String bodyResponse;
    @Inject
    @com.example.tesla_restapiclient.di.qualifier.headerResponse
    String headerResponse;
    String body;
    String header;
    @Inject
    AppDatabase appDatabase;

    @Inject
    RestActivity restActivity;

    @Override
    public ResponseViewModel getViewmodel() {
        responseViewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(ResponseViewModel.class);

        return responseViewModel;

    }

    public ResponseFragment() {

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public static ResponseFragment newInstance() {
        ResponseFragment fragment = new ResponseFragment();

        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentResponseBinding = getBinding();



        fragmentResponseBinding.bodyText.setText(restActivity.bodyResponse);
        fragmentResponseBinding.headerText.setText(restActivity.headerResponse);
        fragmentResponseBinding.txtResponsetime.setText(restActivity.requesttime);
        fragmentResponseBinding.txtRequestcode.setText(restActivity.requestCode);
        fragmentResponseBinding.header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fragmentResponseBinding.headerScroll.getVisibility() == View.GONE){
                    fragmentResponseBinding.headerScroll.setVisibility(View.VISIBLE);

                }else{
                    fragmentResponseBinding.headerScroll.setVisibility(View.GONE);
                }
            }
        });

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
    public void fuckKaviya() {

    }

    public void setRestSuccessResults(String body, String headers, String requestCod, String requestTime) {
       // if(fragmentResponseBinding!=null &&  fragmentResponseBinding.bodyText!=null && fragmentResponseBinding.headerText!=null){

            fragmentResponseBinding.bodyText.setText(body);
            fragmentResponseBinding.headerText.setText(headers);
            fragmentResponseBinding.txtRequestcode.setText(requestCod);
            fragmentResponseBinding.txtResponsetime.setText(requestTime);




       // }


    }
}