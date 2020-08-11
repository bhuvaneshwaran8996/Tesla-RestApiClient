package com.example.tesla_restapiclient.ui.history;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.tesla_restapiclient.R;
import com.example.tesla_restapiclient.controls.TextViewMedium;
import com.example.tesla_restapiclient.controls.TextViewRegular;
import com.example.tesla_restapiclient.db.room.dao.HistoryDao;
import com.example.tesla_restapiclient.di.ViewModelProviderFactory;
import com.example.tesla_restapiclient.model.History;
import com.example.tesla_restapiclient.ui.rest.RestActivity;

import java.util.List;

import static android.view.View.GONE;

public class HistoryFragment extends Fragment {

    private static final String TAG = "HistoryActivity";


   // HistoryViewModel historyViewModel;


    HistoryDao historyDao;
    View view;
    RestActivity restActivity;
    RecyclerView recyclerView;
    Toolbar toolbar;


    public static HistoryFragment newInstance(){
        HistoryFragment historyFragment = new HistoryFragment();
        return historyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_history,null,false);
         toolbar = view.findViewById(R.id.toolbar_history);

       restActivity  = (RestActivity)getActivity();
       try{
           historyDao  = restActivity.getHistoryDao();
           initRecylerview();
           subscribeHistoryData();

       }catch (Exception e){
           e.printStackTrace();
       }


       restActivity.binding.toolbar.setVisibility(View.GONE);
       view.setFocusableInTouchMode(true);
       view.requestFocus();
       view.setOnKeyListener(new View.OnKeyListener() {
           @Override
           public boolean onKey(View v, int keyCode, KeyEvent event) {
               if(event.getAction() == KeyEvent.ACTION_DOWN){


//                 restActivity.binding.lnrMain.setVisibility(View.GONE);
//                 restActivity.binding.lnrViewpager.setVisibility(View.VISIBLE);
//                 restActivity.binding.toolbar.setTitle("Tesla-RestClient");
//                 restActivity.binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_dehaze_24);


                   backButton();
                   return true;
               }
               return false;
           }
       });

        Toolbar viewById =(Toolbar) view.findViewById(R.id.toolbar_history);
        viewById.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                backButton();
            }
        });



        return view.getRootView();
    }





    public void backButton(){
        restActivity. binding.lnrViewpager.setVisibility(View.VISIBLE);
        restActivity.binding.lnrMain.setVisibility(GONE);
       restActivity.binding.toolbar.setVisibility(View.VISIBLE);

    }
    public void initRecylerview(){
         recyclerView = view.findViewById(R.id.rcvhistory);
         recyclerView.setLayoutManager(new LinearLayoutManager(restActivity));
         recyclerView.setHasFixedSize(true);
         recyclerView.setAdapter(null);
    }

    public void subscribeHistoryData(){

        historyDao.loadAll().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(List<History> histories) {
                if(histories!=null){
                    view.findViewById(R.id.rlynohistories).setVisibility(View.GONE);
                    view.findViewById(R.id.rcvhistory).setVisibility(View.VISIBLE);


                    HistoryRecyclerAdapter historyRecyclerAdapter = new HistoryRecyclerAdapter(histories);
                    recyclerView.setAdapter(historyRecyclerAdapter);


                }else{
                    view.findViewById(R.id.rlynohistories).setVisibility(View.VISIBLE);
                    view.findViewById(R.id.rcvhistory).setVisibility(View.GONE);
                }
            }
        });
    }

    public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder>{

        List<History> historyList;
        public  HistoryRecyclerAdapter(List<History> historyList){
            this.historyList = historyList;
        }

        @NonNull
        @Override
        public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_inflate,null,false);
            return new HistoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {

            History history = historyList.get(position);
           if(history.isRest){
               holder.rest_request.setText("RestRequest");
           }else{
               holder.rest_request.setText("FCMRequest");
           }

           holder.requestmethod.setText(history.requestType);
           holder.datetime.setText(history.createdAt);
           holder.url.setText(history.requestUrl);


          holder.delete_history.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {




                  DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialog, int which) {
                          switch (which){
                              case DialogInterface.BUTTON_POSITIVE:

                                  //Yes button clicked

                                  new DeleteHistory(history).execute();
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
                //  new DeleteHistory(history).execute();



          });

        }

        public List<History> getHistoryList() {
            return historyList;
        }

        @Override
        public int getItemCount() {
            return historyList.size();
        }

        public class HistoryViewHolder extends RecyclerView.ViewHolder {

            TextViewMedium requestmethod;
            TextViewMedium rest_request;
            TextViewRegular url;
            TextViewRegular datetime;
            ImageView delete_history;
            public HistoryViewHolder(@NonNull View itemView) {
                super(itemView);
                requestmethod = itemView.findViewById(R.id.requestmethod);
                rest_request = itemView.findViewById(R.id.rest_request);
                url = itemView.findViewById(R.id.url);
                datetime = itemView.findViewById(R.id.datetime);
                delete_history = itemView.findViewById(R.id.delete_history);


            }
        }
    }

    public class DeleteHistory extends AsyncTask<Void, Void, Void>{


        History history;
        public DeleteHistory(History history){
            this.history = history;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            historyDao.delete(history);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
            Toast.makeText(getContext(),"History deleted ",Toast.LENGTH_LONG).show();
        }
    }



}