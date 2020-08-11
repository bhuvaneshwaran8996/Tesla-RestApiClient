package com.example.tesla_restapiclient.db;

import androidx.lifecycle.LiveData;

import com.example.tesla_restapiclient.model.History;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Single;

public interface DbHelper {


    LiveData<List<History>>  getAllHistories();

    void insert(History history);
}
