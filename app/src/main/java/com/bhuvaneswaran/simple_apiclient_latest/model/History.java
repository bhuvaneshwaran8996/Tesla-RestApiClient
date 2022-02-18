package com.bhuvaneswaran.simple_apiclient_latest.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "history")
public class History implements Serializable {

    @Expose
    @PrimaryKey
    public Long id;


    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    public String createdAt;



    @Expose
    @SerializedName("rest")
    @ColumnInfo(name = "rest")
    public boolean isRest;


    @Expose
    @SerializedName("request_type")
    @ColumnInfo(name = "request_type")
    public String requestType;

    @Expose
    @SerializedName("request_url")
    @ColumnInfo(name = "request_url")
    public String requestUrl;

    @Expose
    @SerializedName("rawBody")
    @ColumnInfo(name = "rawBody")
    public String rawBody;

    @Expose
    @SerializedName("keyBody")
    @ColumnInfo(name = "keyBody")
    public String keyBody;

    @Expose
    @SerializedName("header")
    @ColumnInfo(name = "header")
    public String header;

    @Expose
    @SerializedName("keyOrraw")
    @ColumnInfo(name = "keyOrraw")
    public String keyOrraw;


















}
