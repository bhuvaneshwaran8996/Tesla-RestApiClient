package com.example.tesla_restapiclient.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "history")
public class History {

    @Expose
    @PrimaryKey
    public Long id;


    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    public String createdAt;



    @Expose
    @SerializedName("status_code")
    @ColumnInfo(name = "status_code")
    public String statusCode;


    @Expose
    @SerializedName("request_type")
    @ColumnInfo(name = "request_type")
    public String requestType;

    @Expose
    @SerializedName("request_url")
    @ColumnInfo(name = "request_url")
    public String requestUrl;















}
