package com.bhuvaneswaran.simple_api_client.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bhuvaneswaran.simple_api_client.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_api_client.model.History;

@Database(entities = {History.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();


}
