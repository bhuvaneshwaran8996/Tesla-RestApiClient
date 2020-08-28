package com.example.android_api_client.ui.body;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_api_client.R;
import com.example.android_api_client.databinding.FragmentDialogBodyBinding;

import java.io.Serializable;

import javax.inject.Inject;


public class DialogBody extends DialogFragment {


    FragmentDialogBodyBinding binding;
    @Inject
    Context context;
    String type;
    DialogBodyCallback dialogBodyCallback;
    EditBodyCallback editBodyCallback;
    String key, value;
    public DialogBody() {
        // Required empty public constructor
    }


    public static DialogBody newInstance(String type,String key, String value, EditBodyCallback editBodyCallback) {
        DialogBody fragment = new DialogBody();
        Bundle args = new Bundle();
        args.putString("type",type);
        args.putString("key",key);
        args.putString("value",value);
        args.putSerializable("editBodyCallback",editBodyCallback);
        fragment.setArguments(args);
        return fragment;
    }


    public static DialogBody newInstance(String type, DialogBodyCallback dialogBodyCallback) {
        DialogBody fragment = new DialogBody();
        Bundle args = new Bundle();
        args.putString("type",type);
        args.putSerializable("dialogBodyCallback",dialogBodyCallback);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_dialog_body,container,false);
        if(getArguments()!=null){
         type =  getArguments().getString("type");
         key = getArguments().getString("key");
         value = getArguments().getString("value");
         dialogBodyCallback =(DialogBodyCallback) getArguments().getSerializable("dialogBodyCallback");
         editBodyCallback = (EditBodyCallback)getArguments().getSerializable("editBodyCallback");
        }

        if(type!=null && type.equalsIgnoreCase("editBody")){
            binding.editKey.setText(key);
            binding.editValue.setText(value);
        }
        binding.cancel.setOnClickListener(new OnClick());
        binding.ok.setOnClickListener(new OnClick());
        return binding.getRoot();
    }


    public class OnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.cancel:
                    dismiss();
                    break;

                case R.id.ok:
                    if(type!=null&&type.equalsIgnoreCase("editBody")){
                        editBodyCallback.editBodyCallback(binding.editKey.getText().toString(),binding.editValue.getText().toString());

                    }else{
                        dialogBodyCallback.dialogBodyCallback(binding.editKey.getText().toString(),binding.editValue.getText().toString());
                    }

                    dismiss();
                    break;
            }
        }
    }
    public interface  DialogBodyCallback extends Serializable{

        void dialogBodyCallback(String key, String value);
    }
    public interface EditBodyCallback extends Serializable{
        void editBodyCallback(String key, String value);
    }
}