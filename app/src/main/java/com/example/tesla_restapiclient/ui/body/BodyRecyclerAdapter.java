package com.example.tesla_restapiclient.ui.body;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.BodyInflateBinding;

import javax.inject.Inject;

public class BodyRecyclerAdapter extends RecyclerView.Adapter<BodyRecyclerAdapter.BodyViewHolder>{

    public BodyInflateBinding bodyInflateBinding;

    public Context context;
    @Inject
    public BodyRecyclerAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       bodyInflateBinding =  DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.body_inflate,null,false);
        return new BodyViewHolder(bodyInflateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {

    }
    public void setNewList(){

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder{
        public BodyInflateBinding bodyInflateBinding;
        public BodyViewHolder(@NonNull  BodyInflateBinding binding) {
            super(binding.getRoot());
            this.bodyInflateBinding = binding;
        }
    }
}
