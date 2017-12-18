package com.darkestapp.processor;

import com.darkestapp.model.Environment;
import com.darkestapp.parser.JsonMapParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class EnvironmentProcessorTest {

    private static final String JSON_STRING = "{\n" +
            "  \"names\": [\n" +
            "    \"name0\",\n" +
            "    \"name1\"\n" +
            "  ],\n" +
            "  \"values\": [\n" +
            "    {\n" +
            "      \"key\": \"key0\",\n" +
            "      \"value\": \"value0\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"key\": \"key1\",\n" +
            "      \"value\": \"value1\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    public void parseTest() throws Exception {
        EnvironmentProcessor processor = new EnvironmentProcessor();
        List<Environment> environments = processor.parse(new JsonMapParser(), JSON_STRING);
        System.out.println(environments.get(0));
    }
}