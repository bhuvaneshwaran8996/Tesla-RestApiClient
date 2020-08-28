package com.example.android_api_client.db;

import androidx.lifecycle.LiveData;

import com.example.android_api_client.model.History;

import java.util.List;

public interface DbHelper {


    LiveData<List<History>>  getAllHistories();

    void insert(History history);
}
