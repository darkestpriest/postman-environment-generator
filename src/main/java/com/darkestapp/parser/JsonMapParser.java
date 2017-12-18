package com.darkestapp.parser;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import com.darkestapp.model.ParseMap;
import com.google.gson.Gson;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class JsonMapParser implements Parser<ParseMap> {


    @Override
    public ParseMap parse(String jsonString) throws PostmanEnvironmentGeneratorException {

        try {
            Gson gson = new Gson();
            return gson.fromJson(jsonString, ParseMap.class);
        } catch (Exception e) {
            throw new PostmanEnvironmentGeneratorException(
                    "Error parsing json string",
                    e,
                    "Trying to parse: "+jsonString,
                    "Json String can be malformed");
        }
    }
}
