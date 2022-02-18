package com.bhuvaneswaran.simple_apiclient_latest.db;

import androidx.lifecycle.LiveData;

import com.bhuvaneswaran.simple_apiclient_latest.model.History;

import java.util.List;

public interface DbHelper {


    LiveData<List<History>>  getAllHistories();

    void insert(History history);
}
