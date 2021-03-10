package com.bhuvaneswaran.simple_api_client.db;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.bhuvaneswaran.simple_api_client.db.DbHelper;
import com.bhuvaneswaran.simple_api_client.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_api_client.model.History;

import java.util.List;

import javax.inject.Inject;

public class AppDbHelper implements DbHelper
{

    private final HistoryDao historyDao;

    @Inject
    public AppDbHelper(HistoryDao historyDao) {
        this.historyDao = historyDao;
    }


    @Override
    public LiveData<List<History>> getAllHistories() {


        return  historyDao.loadAll();

    }

    @Override
    public void insert(History history) {


        new InsertHistory(history).execute();

    }


    public class InsertHistory extends AsyncTask<Void,Void,Void>{

        public History history;
        public InsertHistory(History history){

            this.history  = history;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            historyDao.insert(history);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
