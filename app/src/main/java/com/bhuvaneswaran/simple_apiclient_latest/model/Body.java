package com.bhuvaneswaran.simple_apiclient_latest.model;

import java.io.Serializable;

public class Body  implements Serializable {
    public String key;
    public String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Body(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
