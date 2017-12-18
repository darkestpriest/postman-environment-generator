package com.darkestapp.processor;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import com.darkestapp.model.Environment;
import com.darkestapp.model.ParseMap;
import com.darkestapp.model.SimpleValue;
import com.darkestapp.model.enums.Version;
import com.darkestapp.parser.Parser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.darkestapp.model.enums.Version.V532;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class EnvironmentProcessor {

    private static final String DEFAULT_SCOPE = "environment";
    private static final Version DEFAULT_VERSION = V532;

    public List<Environment> parse(Parser parser, String jsonString) throws
            PostmanEnvironmentGeneratorException {
        ParseMap map = parser.parse(jsonString);
        List<Environment> environments = new ArrayList<>();
        for (String name : map.getNames()) {
            environments.add(createEnvironment(
                    name,
                    map.getValues()));
        }
        return environments;
    }

    private Environment createEnvironment(
            String name,
            SimpleValue[] values) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return new Environment(
                UUID.randomUUID().toString(),
                name,
                values,
                timestamp.getTime(),
                DEFAULT_SCOPE,
                new Date(timestamp.getTime()),
                DEFAULT_VERSION
                );
    }
}
