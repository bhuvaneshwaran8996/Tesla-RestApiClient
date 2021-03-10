package com.bhuvaneswaran.simple_api_client.di.module;

import android.content.Context;

import androidx.room.Room;

import com.bhuvaneswaran.simple_api_client.db.AppDatabase;
import com.bhuvaneswaran.simple_api_client.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_api_client.di.qualifier.DatabaseInfo;

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
