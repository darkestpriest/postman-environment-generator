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

import static com.darkestapp.config.Constant.*;
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
        SimpleValue[] processed = processValues(values, name);
        return new Environment(
                UUID.randomUUID().toString(),
                name,
                processed,
                timestamp.getTime(),
                DEFAULT_SCOPE,
                new Date(timestamp.getTime()),
                DEFAULT_VERSION
                );
    }

    private SimpleValue[] processValues(SimpleValue[] values, String name) {
        SimpleValue[] simpleValues = processValues(values, name, DEFAULT_NEEDLE);
        String processedName = processName(name);
        return processValues(
                simpleValues,
                processedName,
                SPECIAL_NEEDLE);
    }

    private SimpleValue[] processValues(SimpleValue[] values, String name, String needle) {
        String valueString;
        String value;
        SimpleValue[] valuesToReturn = new SimpleValue[values.length];
        int index = 0;
        SimpleValue processed;
        for (SimpleValue element : values) {
            value = element.getValue();
            valueString = value.replaceAll(needle, name);
            processed = new SimpleValue();
            processed.setKey(element.getKey());
            processed.setValue(valueString);
            valuesToReturn[index] = processed;
            index++;
        }
        return valuesToReturn;
    }

    private String processName(String name) {
        String processedName = name;
        for (String charToReplace : SPECIAL_CHARS_TO_REMOVE) {
            processedName = name.replaceAll(charToReplace, CHAR_TO_INCLUDE);
        }
        return processedName;
    }
}
