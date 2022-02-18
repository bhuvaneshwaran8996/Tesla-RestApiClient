package com.bhuvaneswaran.simple_apiclient_latest.ui.header;

import java.io.Serializable;

public class HeaderModel implements Serializable {

    public String title;
    public String value;

    public HeaderModel(String title, String value) {
        this.title = title;
        this.value = value;
    }
}

