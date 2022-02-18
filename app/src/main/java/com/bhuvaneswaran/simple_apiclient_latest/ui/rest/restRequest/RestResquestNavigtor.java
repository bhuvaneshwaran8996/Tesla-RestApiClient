package com.bhuvaneswaran.simple_apiclient_latest.ui.rest.restRequest;

public interface  RestResquestNavigtor {
    void processSuccessResult(String body, String header, String requestCode, String requestTime);

    void processErrorResult(String message, String requestCode, String requestTime);
}
