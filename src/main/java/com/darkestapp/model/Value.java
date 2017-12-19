package com.darkestapp.model;

import com.darkestapp.model.enums.Type;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class Value {

    private boolean enabled = true;
    private String key;
    private String value;
    private String type = Type.STRING.getCode();

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
