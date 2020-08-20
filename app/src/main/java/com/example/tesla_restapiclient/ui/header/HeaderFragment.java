package com.example.tesla_restapiclient.ui.header;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.load.Encoder;
import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.FragmentHeaderBinding;
import com.example.tesla_restapiclient.utils.AppLogger;

import java.io.ObjectInput;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.tesla_restapiclient.ui.rest.restRequest.RestFragment.TAG;

public class HeaderFragment extends DialogFragment {



    public FragmentHeaderBinding binding;
    public ArrayAdapter<String> arrayAdapter;
    public List<String> requestlist;
    IHeaderCallback iHeaderCallback;
    String type, value,from;
    int pos;
    int selectedPos;
    public HeaderModel headerModel;
    public EditHeaderCallback editHeaderCallback;
    @Inject
    FragmentManager fragmentManager;
    public HeaderFragment() {
        // Required empty public constructor
    }

    public static HeaderFragment newInstance(IHeaderCallback iHeaderCallback, String from) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();
        args.putSerializable("callback",  iHeaderCallback);
        args.putString("from",from);
        fragment.setArguments(args);
        return fragment;
    }
    public static HeaderFragment newInstance( String from, String type,HeaderModel headerModel, EditHeaderCallback editHeaderCallback) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();
        args.putSerializable("callbackedit",  editHeaderCallback);
        args.putString("from",from);
        args.putString("type",type);
        args.putSerializable("model",headerModel);
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
           pos = savedInstanceState.getInt("position",0);
           type = savedInstanceState.getString("type",type);



       }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Spinner spinner =   view.findViewById(R.id.spinner);

        if(getArguments()!=null){
          iHeaderCallback = (IHeaderCallback)  getArguments().getSerializable("callback");
          from =(String) getArguments().getSerializable("from");
          type = (String)getArguments().getSerializable("type");
          headerModel = (HeaderModel)getArguments().getSerializable("model");
          editHeaderCallback = (EditHeaderCallback) getArguments().getSerializable("callbackedit");


        }
        if(requestlist == null) {
            requestlist = new ArrayList<>();
            requestlist.add("Content-Type");
            requestlist.add("Accept");
            requestlist.add("Authorization");
            requestlist.add("Authorization(Basic)");
            requestlist.add("Authorization(Bearer)");
            requestlist.add("Custom");

        }


        arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,requestlist);
        spinner.setAdapter(arrayAdapter);
        binding.spinner.setSelection(0);
        if(from.equalsIgnoreCase("editHeader")){

            int i = requestlist.indexOf(type);
            if(i<0){
                spinner.setSelection(5);
            }else{
                spinner.setSelection(i);
            }


            if(type.equalsIgnoreCase(requestlist.get(0).toString())){


                binding.editValue.setText(headerModel.value);

            }else if(type.equalsIgnoreCase(requestlist.get(1).toString())){
                binding.editValue.setText(headerModel.value);

            }else if(type.equalsIgnoreCase(requestlist.get(2).toString())){
                binding.editToken.setText(headerModel.value);

            }else if(type.equalsIgnoreCase(requestlist.get(3).toString())){
                Log.d(TAG, "onViewCreated: "+headerModel.value);
                String[] basic_s = headerModel.value.split("Basic ");

                byte[] data = Base64.decode(basic_s[1], Base64.DEFAULT);
                try {
                    String text = new String(data, "UTF-8");
                    String[] split = text.split(":");
                    binding.editUsername.setText(split[0]);
                    binding.editPassword.setText(split[1]);

                    Log.d(TAG, "onViewCreated: "+text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }else if(type.equalsIgnoreCase(requestlist.get(4).toString())){
                String[] bearer_s = headerModel.value.split("Bearer ");
                binding.editToken.setText(bearer_s[1]);

            }else {
                binding.editKey.setText(headerModel.title);
                binding.editValue.setText(headerModel.value);


            }

        }else{
            spinner.setSelection(5);
        }


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView)view;
                if(textView!=null)
                    textView.setTextColor(getResources().getColor(R.color.orange));


                if(position == 0){
                pos = position;
                    goneAll();
                    binding.lnrValue.setVisibility(View.VISIBLE);

                }else if(position == 1){
                    pos = position;
                    goneAll();
                    binding.lnrValue.setVisibility(View.VISIBLE);

                }else if(position == 2 || position ==4){
                    pos = position;
                    goneAll();
                    binding.lnrToken.setVisibility(View.VISIBLE);

                }else if(position == 5){ // custom
                    pos = position;
                    goneAll();
                    binding.lnrKey.setVisibility(View.VISIBLE);
                    binding.lnrValue.setVisibility(View.VISIBLE);

                }else if(position == 3){ // basic auth
                    pos = position;
                    goneAll();
                    binding.lnrUsername.setVisibility(View.VISIBLE);
                    binding.lnrPassword.setVisibility(View.VISIBLE);

                }
                selectedPos = pos;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.cancel.setOnClickListener(new OnClick());
        binding.ok.setOnClickListener(new OnClick());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList("spinner", (ArrayList<String>) requestlist);
        outState.putInt("position",pos);

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
                case R.id.ok:
                    if(selectedPos == 0){

                        type = requestlist.get(selectedPos);
                        value = binding.editValue.getText().toString();

                    }else if(selectedPos == 1){


                        type = requestlist.get(selectedPos);
                        value = binding.editValue.getText().toString();


                    }else if(selectedPos == 2 || selectedPos ==4){

                        if(selectedPos == 2){
                            type = requestlist.get(selectedPos);
                            value = binding.editToken.getText().toString();
                        }else{
                            type = requestlist.get(selectedPos);
                            value = "Bearer "+binding.editToken.getText().toString();
                        }


                    }else if(selectedPos == 5){ // custom

                        type = binding.editKey.getText().toString();
                        value = binding.editValue.getText().toString();



                    }else if(selectedPos == 3){ // basic auth


                        String credentials = binding.editUsername.getText().toString() + ":" + binding.editPassword.getText().toString();
                        byte[] data = new byte[0];
                        try {
                            data = credentials.getBytes();
                            String base64 = Base64.encodeToString(data, Base64.NO_WRAP);
                            type = requestlist.get(selectedPos);
                             value = "Basic "+base64;
                            Log.d(TAG, "onClick: "+value);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }




                    }
                    if(from.equalsIgnoreCase("")){
                        iHeaderCallback.headerDetails(type,value);
                    }else{
                        editHeaderCallback.editHeader(type,value);
                    }

                    dismiss();
                    break;

            }
        }
    }
    public interface IHeaderCallback extends Serializable{

         void headerDetails(String type, String value);
    }
    public interface  EditHeaderCallback extends Serializable{
        void editHeader(String type, String value);
    }
}