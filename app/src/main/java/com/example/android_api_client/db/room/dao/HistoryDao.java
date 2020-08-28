package com.example.android_api_client.db.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_api_client.model.History;

import java.util.List;


@Dao
public interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(History history);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<History> options);

    @Query("SELECT * FROM history")
    LiveData<List<History>> loadAll();

    @Delete
    void delete(History history);

    @Update
    void update(History history);




//    @Query("SELECT * FROM options WHERE question_id = :questionId")
//    Single<List<History>> loadAllByQuestionId(Long questionId);
}
