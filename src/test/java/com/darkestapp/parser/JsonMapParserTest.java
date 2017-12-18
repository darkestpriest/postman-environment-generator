package com.darkestapp.parser;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import com.darkestapp.model.ParseMap;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class JsonMapParserTest {

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
    private static final String MALFORMED_JSON_STRING = "{\n" +
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
            "  ]\n";
    private static final long EXPECTED_ARRAY_LENGTH = 2L;

    @Test
    public void parseTest() throws Exception {

        JsonMapParser mapParser = new JsonMapParser();
        ParseMap map = mapParser.parse(JSON_STRING);
        assertNotNull(map);
        assertEquals(EXPECTED_ARRAY_LENGTH, map.getNames().length);
        assertEquals(EXPECTED_ARRAY_LENGTH, map.getValues().length);
    }

    @Test(expected = PostmanEnvironmentGeneratorException.class)
    public void parseMalformedJsonStringTest() throws Exception {
        JsonMapParser mapParser = new JsonMapParser();
        mapParser.parse(MALFORMED_JSON_STRING);
    }
}