package com.darkestapp.model.enums;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public enum Version {

    V532 ("Postman/5.3.2")
    ;

    private final String code;

    Version(String version){
        this.code = version;
    }

    public String getCode() {
        return code;
    }
}
