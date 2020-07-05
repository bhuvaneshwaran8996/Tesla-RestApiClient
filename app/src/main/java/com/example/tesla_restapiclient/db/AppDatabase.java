package com.example.tesla_restapiclient.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tesla_restapiclient.db.room.dao.HistoryDao;
import com.example.tesla_restapiclient.model.History;

@Database(entities = {History.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();


}
