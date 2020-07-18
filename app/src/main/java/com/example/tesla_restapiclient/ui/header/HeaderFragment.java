package com.example.tesla_restapiclient.ui.header;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentHeaderBinding;

import java.io.ObjectInput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HeaderFragment extends DialogFragment {



    public FragmentHeaderBinding binding;
    public ArrayAdapter<String> arrayAdapter;
    public List<String> requestlist;
    public HeaderFragment() {
        // Required empty public constructor
    }

    public static HeaderFragment newInstance() {
        HeaderFragment fragment = new HeaderFragment();
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
       binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_header,container,false);
       if(savedInstanceState!=null){
           requestlist = (ArrayList<String>) savedInstanceState.get("spinner");
       }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner =   view.findViewById(R.id.spinner);


        if(requestlist == null) {
            requestlist = new ArrayList<>();
            requestlist.add("Content Type");
            requestlist.add("Accept");
            requestlist.add("Authorization");
            requestlist.add("Authorization(Basic)");
            requestlist.add("Authorization(Bearer)");
            requestlist.add("Custom");

        }

        arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,requestlist);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view;
                if(textView!=null)
                    textView.setTextColor(getResources().getColor(R.color.orange));

                if(position == 0){

                    goneAll();
                    binding.lnrValue.setVisibility(View.VISIBLE);

                }else if(position == 1){
                    goneAll();
                    binding.lnrValue.setVisibility(View.VISIBLE);


                }else if(position == 2 || position ==4){
                    goneAll();
                    binding.lnrToken.setVisibility(View.VISIBLE);

                }else if(position == 5){
                    goneAll();
                    binding.lnrKey.setVisibility(View.VISIBLE);
                    binding.lnrValue.setVisibility(View.VISIBLE);

                }else if(position == 3){
                    goneAll();
                    binding.lnrUsername.setVisibility(View.VISIBLE);
                    binding.lnrPassword.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        binding.cancel.setOnClickListener(new OnClick());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("spinner", (ArrayList<String>) requestlist);

    }

    public void goneAll(){

        binding.lnrValue.setVisibility(View.GONE);
        binding.lnrKey.setVisibility(View.GONE);
        binding.lnrPassword.setVisibility(View.GONE);
      //  binding.lnrType.setVisibility(View.GONE);
        binding.lnrUsername.setVisibility(View.GONE);
        binding.lnrToken.setVisibility(View.GONE);
    }

    public class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.cancel:

                    dismiss();
                    break;

            }
        }
    }
}