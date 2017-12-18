package com.darkestapp.model;

import com.darkestapp.model.enums.Type;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class SimpleValue extends Value {

    public boolean getEnabled() {
        return true;
    }

    public String getType() {
        return Type.STRING.getCode();
    }
}
