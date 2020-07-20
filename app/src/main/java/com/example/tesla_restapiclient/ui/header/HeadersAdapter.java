package com.example.tesla_restapiclient.ui.header;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.HeaderInflateBinding;

import javax.inject.Inject;

public class HeadersAdapter extends RecyclerView.Adapter<HeadersAdapter.HeadersViewHolder>{


    public Context context;
    public HeaderInflateBinding binding;
    @Inject
    public HeadersAdapter(Context context){
    }
    @NonNull
    @Override
    public HeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      binding =   DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.header_inflate,null,false);

      return  new HeadersViewHolder(binding);
    }

    public void setList(){

    }
    @Override
    public void onBindViewHolder(@NonNull HeadersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HeadersViewHolder extends RecyclerView.ViewHolder{
        public HeaderInflateBinding headerInflateBinding;
        public HeadersViewHolder(@NonNull  HeaderInflateBinding binding) {
            super(binding.getRoot());
            this.headerInflateBinding = binding;
        }
    }
}
