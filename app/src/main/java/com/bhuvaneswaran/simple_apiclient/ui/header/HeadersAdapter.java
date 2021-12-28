package com.bhuvaneswaran.simple_apiclient.ui.header;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bhuvaneswaran.simple_apiclient.R;

import com.bhuvaneswaran.simple_apiclient.databinding.HeaderInflateBinding;
import com.bhuvaneswaran.simple_apiclient.ui.rest.RestActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class HeadersAdapter extends RecyclerView.Adapter<HeadersAdapter.HeadersViewHolder>{


    public Map<String,String> headerMap;
    public Context context;
    public HeaderInflateBinding binding;
    public List<HeaderModel> headerModelList;
    public  HeaderEditCallback headerEditCallback;

    @Inject
    RestActivity restActivity;

    @Inject
    FragmentManager fragmentManager;
    @Inject
    public HeadersAdapter(Context context){

        headerModelList = new ArrayList<>();
        headerMap = new HashMap<>();


    }
    @NonNull
    @Override
    public HeadersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      binding =   DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.header_inflate,null,false);
      return  new HeadersViewHolder(binding);

    }




    @Override
    public void onBindViewHolder(@NonNull HeadersViewHolder holder, int position) {

        holder.headerInflateBinding.content.setText(headerModelList.get(position).value);
        holder.headerInflateBinding.title.setText(headerModelList.get(position).title);
        holder.headerInflateBinding.icEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               HeaderFragment headerFragment = HeaderFragment.newInstance("editHeader", headerModelList.get(position).title, headerModelList.get(position), new HeaderFragment.EditHeaderCallback() {
                   @Override
                   public void editHeader(String type, String value) {
                       headerModelList.get(position).title = type;
                       headerModelList.get(position).value = value;
                       notifyDataSetChanged();

                   }
               });
                headerFragment.show(fragmentManager,"");
            }
        });
//        holder.headerInflateBinding.icDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialo
//            }
//        });

        holder.headerInflateBinding.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                //Yes button clicked
                                 headerModelList.remove(holder.getAdapterPosition());
                                   notifyItemRemoved(holder.getAdapterPosition());
                                   dialog.dismiss();
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

//        HeaderModel headerModel = headerModelList.get(position);
//        int i = headerModelList.indexOf(headerModel);

    }

    public Map<String,String> getHeaderModelList(){
        if(headerModelList !=null && headerModelList.size()>=0){
            headerMap.clear();
        }
        for(HeaderModel headerModel: headerModelList){
//            if(headerModel.title.equalsIgnoreCase("Authorization(Basic)") || headerModel.title.equalsIgnoreCase("Authorization(Bearer)") || headerModel.title.equalsIgnoreCase("Authorization")){
//              //  headerModel.title = "authorization";
//            }
            headerMap.put(headerModel.title,headerModel.value);

        }

        return headerMap;
    }

    @Override
    public int getItemCount() {


        if(headerModelList.size() == 0){
            return  0;
        }
        return  headerModelList.size();
    }



    public void emptyHeader(){
        this.headerModelList.clear();
        notifyDataSetChanged();
    }
    public void setHeaderList(List<HeaderModel> headerModelList) {

        this.headerModelList = headerModelList;
        notifyDataSetChanged();
    }

    public class HeadersViewHolder extends RecyclerView.ViewHolder{
        public HeaderInflateBinding headerInflateBinding;
        public HeadersViewHolder(@NonNull  HeaderInflateBinding binding) {
            super(binding.getRoot());
            this.headerInflateBinding = binding;
        }
    }
    public interface HeaderEditCallback{

        void callByPos(int pos);
    }
}
