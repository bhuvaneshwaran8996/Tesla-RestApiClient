package com.example.tesla_restapiclient.ui.rest.response;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.MalformedJsonException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
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
               binding.bodyText.setTextColorNumber(Color.parseColor(getString(R.color.yellow)));
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