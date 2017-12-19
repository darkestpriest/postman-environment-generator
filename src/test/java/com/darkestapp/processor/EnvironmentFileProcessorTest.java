package com.darkestapp.processor;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class EnvironmentFileProcessorTest {

    private static final long EXPECTED_SIZE_LIST = 2L;
    private static final String JSON_STRING = "{\n" +
            "  \"names\": [\n" +
            "    \"name0\",\n" +
            "    \"name1/name2\"\n" +
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
    public void createEnvironmentFilesTest() throws Exception {
        EnvironmentFileProcessor processor = new EnvironmentFileProcessor();
        List<String> result = processor.createEnvironmentFiles("environment", JSON_STRING);
        assertNotNull(result);
        assertEquals(EXPECTED_SIZE_LIST, result.size());
    }

    @Test(expected = PostmanEnvironmentGeneratorException.class)
    public void createEnvironmentFilesBadPathTest() throws Exception {
        EnvironmentFileProcessor processor = new EnvironmentFileProcessor();
        processor.createEnvironmentFiles("///", JSON_STRING);
    }
}