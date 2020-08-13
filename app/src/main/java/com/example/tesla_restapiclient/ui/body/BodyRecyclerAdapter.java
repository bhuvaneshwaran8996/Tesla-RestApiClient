package com.example.tesla_restapiclient.ui.body;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.databinding.BodyInflateBinding;
import com.example.tesla_restapiclient.model.Body;
import com.example.tesla_restapiclient.ui.rest.RestActivity;
import com.example.tesla_restapiclient.ui.rest.restRequest.RestFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class BodyRecyclerAdapter extends RecyclerView.Adapter<BodyRecyclerAdapter.BodyViewHolder>{

    public BodyInflateBinding bodyInflateBinding;
    public Map<String, String> bodymap;

    @Inject
    RestActivity restActivity;

    public Context context;
    public List<Body> bodyList;
    @Inject
    public BodyRecyclerAdapter(Context context){
        this.context = context;
        bodymap = new HashMap<>();
        this.bodyList = new ArrayList<>();

    }
    @NonNull
    @Override
    public BodyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       bodyInflateBinding =  DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.body_inflate,null,false);
        return new BodyViewHolder(bodyInflateBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BodyViewHolder holder, int position) {

        holder.bodyInflateBinding.txtKey.setText(this.bodyList.get(position).key);
        holder.bodyInflateBinding.txtValue.setText(this.bodyList.get(position).value);
        holder.bodyInflateBinding.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Body body = bodyList.get(position);

                DialogBody dialogBody = DialogBody.newInstance("editBody",body.key,body.value, new DialogBody.EditBodyCallback() {
                    @Override
                    public void editBodyCallback(String key, String value) {

                        bodyList.get(position).key = key;
                        bodyList.get(position).value = value;
                        notifyDataSetChanged();
//                        bodyList.add(new Body(key, value));

                    }
                });
                dialogBody.show(restActivity.getSupportFragmentManager(), "");



            }
        });
        holder.bodyInflateBinding.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:


                                try{


                                        bodyList.remove(holder.getAdapterPosition() );
                                        notifyItemRemoved(holder.getAdapterPosition()  );

                                        dialog.dismiss();


                                }catch (Exception e){



                                    e.printStackTrace();
                                }
                                //Yes button clicked

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(restActivity);
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)

                        .setNegativeButton("No", dialogClickListener).show();
            }

        });

    }

    public void emptyBody(){
        this.bodyList.clear();
        notifyDataSetChanged();
    }
    public Map<String, String>  getBodyList() {
//        Collections.reverse(bodyList);
        for(Body body : bodyList){
            bodymap.put(body.key,body.value);
        }
        return bodymap;
    }

    public void setBodyList(List<Body> bodyList) {
        this.bodyList = bodyList;
    }

    public void setNewList(List<Body> bodyList){
        if(this.bodyList.size()>0){
            this.bodyList.clear();
        }
        this.bodyList = bodyList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if(bodyList.size() == 0){
            return  0;
        }
        return  bodyList.size();
    }

    public class BodyViewHolder extends RecyclerView.ViewHolder{
        public BodyInflateBinding bodyInflateBinding;
        public BodyViewHolder(@NonNull  BodyInflateBinding binding) {
            super(binding.getRoot());
            this.bodyInflateBinding = binding;
        }
    }
}
