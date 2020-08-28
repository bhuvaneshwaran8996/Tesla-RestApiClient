package com.example.android_api_client.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_api_client.db.room.dao.HistoryDao;
import com.example.android_api_client.model.History;

@Database(entities = {History.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();


}
