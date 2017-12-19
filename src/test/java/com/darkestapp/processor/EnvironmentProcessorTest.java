package com.darkestapp.processor;

import com.darkestapp.model.Environment;
import com.darkestapp.parser.JsonMapParser;
import org.junit.Test;

import java.util.List;

import static com.darkestapp.config.Constant.DEFAULT_NEEDLE;
import static com.darkestapp.config.Constant.SPECIAL_NEEDLE;
import static org.junit.Assert.*;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class EnvironmentProcessorTest {

    private static final String JSON_STRING = "{\n" +
            "  \"names\": [\n" +
            "    \"name0\",\n" +
            "    \"/prev/name1\"\n" +
            "  ],\n" +
            "  \"values\": [\n" +
            "    {\n" +
            "      \"key\": \"key0\",\n" +
            "      \"value\": \""+DEFAULT_NEEDLE+"\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"key\": \"key1\",\n" +
            "      \"value\": \""+SPECIAL_NEEDLE+"\"\n" +
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