package com.bhuvaneswaran.simple_api_client.db;

import androidx.lifecycle.LiveData;

import com.bhuvaneswaran.simple_api_client.model.History;

import java.util.List;

public interface DbHelper {


    LiveData<List<History>>  getAllHistories();

    void insert(History history);
}
