package com.darkestapp.processor;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class PostmanEnvironmentGenerator {

    public List<String> generate(String inputFilePath, String outputPath)
            throws PostmanEnvironmentGeneratorException {
        String fileContent = getFileContent(inputFilePath);
        return new EnvironmentFileProcessor()
                .createEnvironmentFiles(outputPath, fileContent);
    }

    private String getFileContent(String inputFilePath)
            throws PostmanEnvironmentGeneratorException {
        try {
            JsonParser jsonParser = new JsonParser();
            FileReader reader = new FileReader(inputFilePath);
            return jsonParser
                    .parse(reader)
                    .toString();
        } catch (FileNotFoundException e) {
            throw new PostmanEnvironmentGeneratorException(
                    "Error reading environment configuration file",
                    e,
                    EnvironmentFileProcessor.class.getSimpleName(),
                    inputFilePath + " does not exist");
        }

    }
}
