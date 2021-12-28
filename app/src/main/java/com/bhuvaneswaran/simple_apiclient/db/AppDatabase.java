package com.bhuvaneswaran.simple_apiclient.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.bhuvaneswaran.simple_apiclient.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_apiclient.model.History;

@Database(entities = {History.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract HistoryDao historyDao();


}
