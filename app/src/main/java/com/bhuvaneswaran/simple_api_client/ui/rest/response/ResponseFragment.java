package com.bhuvaneswaran.simple_api_client.ui.rest.response;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModelProviders;

import android.util.MalformedJsonException;
import android.view.View;

import com.bhuvaneswaran.simple_api_client.R;
import com.bhuvaneswaran.simple_api_client.databinding.FragmentResponseBinding;
import com.bhuvaneswaran.simple_api_client.db.AppDatabase;
import com.bhuvaneswaran.simple_api_client.di.ViewModelProviderFactory;
import com.bhuvaneswaran.simple_api_client.ui.base.BaseFragment;
import com.bhuvaneswaran.simple_api_client.ui.rest.RestActivity;
import com.bhuvaneswaran.simple_api_client.ui.rest.response.ResponseViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Inject;


public class ResponseFragment extends BaseFragment<FragmentResponseBinding, com.bhuvaneswaran.simple_api_client.ui.rest.response.ResponseViewModel> implements ResponseNavigator{



    FragmentResponseBinding fragmentResponseBinding;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;
    ResponseViewModel responseViewModel;
    @Inject
    @com.bhuvaneswaran.simple_api_client.di.qualifier.bodyResponse
    String bodyResponse;
    @Inject
    @com.bhuvaneswaran.simple_api_client.di.qualifier.headerResponse
    String headerResponse;
    String body;
    String header;
    @Inject
    AppDatabase appDatabase;

    String  jsonText = " ";
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


//
//        fragmentResponseBinding.bodyText.setText(restActivity.bodyResponse);
//        fragmentResponseBinding.headerText.setText(restActivity.headerResponse);
//        fragmentResponseBinding.txtResponsetime.setText(restActivity.requesttime);
//        fragmentResponseBinding.txtRequestcode.setText(restActivity.requestCode);
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
    public void resoponseNavigator() {

    }

    @SuppressLint("ResourceType")
    public void setRestSuccessResults(String body, String headers, String requestCod, String requestTime) {
       // if(fragmentResponseBinding!=null &&  fragmentResponseBinding.bodyText!=null && fragmentResponseBinding.headerText!=null){


        if(body.equalsIgnoreCase("") && headers.equalsIgnoreCase("")){
            binding.bodyText.setVisibility(View.GONE);
            binding.jsonText.setVisibility(View.GONE);
        }
        else{
            try{
                binding.jsonText.setVisibility(View.GONE);
                binding.bodyText.setVisibility(View.VISIBLE);
               binding.bodyText.setTextColorString(Color.parseColor(getString(R.color.red_dark)));
               binding.bodyText.setTextColorNumber(Color.parseColor(getString(R.color.red_dark2)));
               binding.bodyText.setTextColorBool(Color.parseColor(getString(R.color.light_green)));
               binding.bodyText.setTextColorNull(Color.parseColor(getString(R.color.orange)));

                String firstChar = String.valueOf(body.charAt(0));

                if (firstChar.equalsIgnoreCase("[")) {
                    //json array
                    JSONArray jsonArray = new JSONArray(body);
                    binding.bodyText.setJson(jsonArray);
                }else if(firstChar.equalsIgnoreCase("{")){
                    //json object
                    JSONObject jsonObject = new JSONObject(body);
                    binding.bodyText.setJson(jsonObject);
                }else {

                    binding.bodyText.setVisibility(View.GONE);
                    binding.jsonText.setVisibility(View.VISIBLE);
                    binding.jsonText.setText(body);

                }
//                if(new Gson().toJsonTree(body).isJsonArray()){
//                    JSONArray jsonArray = new JSONArray(body);
//                    binding.bodyText.setJson(jsonArray);
//                }else {
//
//                    JSONObject jsonObject = new JSONObject(body);
//                    binding.bodyText.setJson(jsonObject);
//                }





            }catch (Exception e ){
                if(e instanceof  MalformedJsonException || e instanceof IllegalArgumentException ){

                //    Toast.makeText(getActivity(),body,Toast.LENGTH_SHORT).show();

                        binding.jsonText.setVisibility(View.VISIBLE);
                        binding.bodyText.setVisibility(View.GONE);
                        binding.jsonText.setText(body);


                }

            }

        }
            fragmentResponseBinding.headerText.setText(headers);
            fragmentResponseBinding.txtRequestcode.setText(requestCod);
            fragmentResponseBinding.txtResponsetime.setText(requestTime);
            restActivity.hideLoading();




       // }


    }
}