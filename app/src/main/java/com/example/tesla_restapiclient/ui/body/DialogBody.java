package com.example.tesla_restapiclient.ui.body;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tesla_restapiclient.R;


public class DialogBody extends DialogFragment {



    public DialogBody() {
        // Required empty public constructor
    }



    public static DialogBody newInstance() {
        DialogBody fragment = new DialogBody();
        Bundle args = new Bundle();

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
        return inflater.inflate(R.layout.fragment_dialog_body, container, false);
    }
}