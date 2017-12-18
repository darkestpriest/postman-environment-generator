package com.darkestapp.model.enums;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public enum Type {

    STRING ("string")
    ;

    private final String code;

    Type(String type) {
        this.code = type;
    }

    public String getCode() {
        return code;
    }
}
