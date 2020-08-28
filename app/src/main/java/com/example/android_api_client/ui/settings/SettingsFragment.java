package com.example.android_api_client.ui.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android_api_client.R;

import java.util.Objects;

import javax.inject.Inject;

public class SettingsFragment extends DialogFragment {


    @Inject Context context;
    int conenction;
    EditText editText;
    public SettingsFragment() {
        // Required empty public constructor
    }



    public static SettingsFragment newInstance(int connection) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putInt("conenction",connection);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_settings2, container, false);
        if(getArguments()!=null){
            conenction = getArguments().getInt("conenction", 60);
        }


        editText =   view.findViewById(R.id.timeedt);
        editText.setText(String.valueOf(conenction));
        view.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    SharedPreferences restPreference = Objects.requireNonNull(getActivity()).getSharedPreferences("RestPreference", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = restPreference.edit();
                    edit.putInt("timeout",Integer.parseInt(editText.getText().toString().trim()));
                    edit.apply();
                    edit.commit();

                }catch (Exception e){

                e.printStackTrace();


                }

                dismiss();


            }

        });
       view.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dismiss();
           }
       });
        return view.getRootView();
    }


}