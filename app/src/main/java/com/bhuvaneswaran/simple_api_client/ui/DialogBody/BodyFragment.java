package com.bhuvaneswaran.simple_api_client.ui.DialogBody;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bhuvaneswaran.simple_api_client.R;

public class BodyFragment extends DialogFragment {



    public BodyFragment() {

    }

    public static com.bhuvaneswaran.simple_api_client.ui.header.HeaderFragment newInstance() {
        com.bhuvaneswaran.simple_api_client.ui.header.HeaderFragment fragment = new com.bhuvaneswaran.simple_api_client.ui.header.HeaderFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_blank, container, false);

        if(savedInstanceState!=null){




        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }



}