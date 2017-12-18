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
}
