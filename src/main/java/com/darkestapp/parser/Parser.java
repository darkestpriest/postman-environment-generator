package com.darkestapp.parser;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import com.darkestapp.model.ParseMap;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public interface Parser<P extends ParseMap> {

    P parse(String jsonString) throws PostmanEnvironmentGeneratorException;
}
