package com.darkestapp.processor;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class PostmanEnvironmentGeneratorTest {

    private static final long EXPECTED_SIZE = 2L;

    @Test
    public void generateTest() throws Exception {

        PostmanEnvironmentGenerator postmanEnvironmentGenerator = new PostmanEnvironmentGenerator();
        List<String> generated = postmanEnvironmentGenerator
                .generate("environment/file", "environment");
        assertNotNull(generated);
        assertEquals(EXPECTED_SIZE, generated.size());
    }

    @Test(expected = PostmanEnvironmentGeneratorException.class)
    public void generateNonExistingFileTest() throws Exception {

        PostmanEnvironmentGenerator postmanEnvironmentGenerator = new PostmanEnvironmentGenerator();
        postmanEnvironmentGenerator
                .generate("environment", "environment");
    }
}