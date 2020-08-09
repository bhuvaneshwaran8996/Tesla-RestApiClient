package com.example.tesla_restapiclient.ui.rest.restRequest;

public interface  RestResquestNavigtor {
    void processSuccessResult(String body, String header, String requestCode, String requestTime);

    void processErrorResult(String message, String requestCode, String requestTime);
}
