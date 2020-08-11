package com.example.tesla_restapiclient.di.module;

import android.content.Context;

import androidx.room.Room;

import com.example.tesla_restapiclient.db.AppDatabase;
import com.example.tesla_restapiclient.db.room.dao.HistoryDao;
import com.example.tesla_restapiclient.di.qualifier.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    HistoryDao provideHistoryDao(AppDatabase appDatabase){
        return appDatabase.historyDao();
    }


}
