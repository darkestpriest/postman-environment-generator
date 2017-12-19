package com.darkestapp.processor;

import com.darkestapp.exceptions.PostmanEnvironmentGeneratorException;
import com.darkestapp.model.Environment;
import com.darkestapp.parser.JsonMapParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Perez P. (darkpriestrelative@gmail.com) on 18/12/17.
 */
public class EnvironmentFileProcessor {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.mmm'Z'";
    private static final String SEPARATOR = "/";
    private static final String FILE_NAME_SUFFIX = ".postman_environment.json";

    public List<String> createEnvironmentFiles(String path, String jsonString)
            throws PostmanEnvironmentGeneratorException {
        EnvironmentProcessor processor = new EnvironmentProcessor();
        List<String> files = new ArrayList<>();
        List<Environment> environments = processor.parse(new JsonMapParser(), jsonString);
        for (Environment environment : environments) {
            createFile(path, environment);
            files.add(path);
        }
        return files;
    }

    private String createFile(String path, Environment environment)
            throws PostmanEnvironmentGeneratorException {
        String environmentJson = environmentToJson(environment);
        String realPath = getRealPath(path, environment);
        checkPath(realPath);
        String filePath = path + SEPARATOR + environment.getName() + FILE_NAME_SUFFIX;
        try(FileWriter file = new FileWriter(filePath)) {
            file.write(environmentJson);
            file.close();
            return filePath;
        } catch (IOException e) {
            throw new PostmanEnvironmentGeneratorException(
                    "Error persisting environment into a file",
                    e,
                    EnvironmentFileProcessor.class.getSimpleName(),
                    "Cannot parse " + environment);
        }
    }

    private String getRealPath(String path, Environment environment) {
        String environmentName = environment.getName();
        if(environmentName.contains(SEPARATOR)) {
            StringBuilder builder = new StringBuilder(path);
            String[] detachedName = environmentName.split(SEPARATOR);
            for(int index = 0; index < detachedName.length-1; index++) {
                builder
                        .append(SEPARATOR)
                        .append(detachedName[index]);
            }
            return builder.toString();
        } else {
            return path;
        }
    }

    private void checkPath(String path) {
        File file = new File(path);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    private String environmentToJson(Environment environment)
            throws PostmanEnvironmentGeneratorException {
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat(DEFAULT_DATE_FORMAT)
                    .setPrettyPrinting()
                    .create();
            return gson.toJson(environment);
        } catch (Exception e) {
            throw new PostmanEnvironmentGeneratorException(
                    "Error parsing environment",
                    e,
                    EnvironmentFileProcessor.class.getSimpleName(),
                    "Cannot parse " + environment);
        }
    }
}
