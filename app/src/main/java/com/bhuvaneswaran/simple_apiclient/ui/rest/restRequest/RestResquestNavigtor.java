package com.bhuvaneswaran.simple_apiclient.ui.rest.restRequest;

public interface  RestResquestNavigtor {
    void processSuccessResult(String body, String header, String requestCode, String requestTime);

    void processErrorResult(String message, String requestCode, String requestTime);
}
