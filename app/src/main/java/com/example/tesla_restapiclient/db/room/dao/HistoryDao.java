package com.example.tesla_restapiclient.db.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tesla_restapiclient.model.History;

import java.util.List;

import io.reactivex.Single;



@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(History history);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<History> options);

    @Query("SELECT * FROM history")
    Single<List<History>> loadAll();

//    @Query("SELECT * FROM options WHERE question_id = :questionId")
//    Single<List<History>> loadAllByQuestionId(Long questionId);
}

