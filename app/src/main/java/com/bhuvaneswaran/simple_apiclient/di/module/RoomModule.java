package com.bhuvaneswaran.simple_apiclient.di.module;

import android.content.Context;

import androidx.room.Room;

import com.bhuvaneswaran.simple_apiclient.db.AppDatabase;
import com.bhuvaneswaran.simple_apiclient.db.room.dao.HistoryDao;
import com.bhuvaneswaran.simple_apiclient.di.qualifier.DatabaseInfo;

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
