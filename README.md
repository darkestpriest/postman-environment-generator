# Postman environment generator
The main object on this artifact is help to create complex postman environment collections, specially when it is required to create multiple environment files with the same structure.

##How to use?
To work with this library you can provide a json String to __EnvironmentFileProcessor__, together with the path where you need to store the postman environment collection. For example:
```java
    String path = "environments";
    EnvironmentFileProcessor processor = new EnvironmentFileProcessor();
    List<String> generatedFiles = processor.createEnvironmentFiles(path, jsonString);
```
The previous code will return the list of the generated files.
The json file must to use the following format:
```json
    {
        "names": [
            "name0",
            "name1"
         ],
        "values": [
            {
            "key": "key0",
            "value": "value0"
            },
            {
            "key": "key1",
            "value": "value1"
            }
        ]
    }
```
_names_ represents the environment names and _values_ the environment values. The processor will create a file for each name submitted in _names_, each file will contain all the key value submitted in _values_.

If it required to replace any value on _values_ with the environment name, do you need to use a "needle" to indicate which string must be replaced, for example in the following json string:
```json
    {
        "names": [
            "name0",
            "name1"
         ],
        "values": [
            {
            "key": "key0",
            "value": "value0"
            },
            {
            "key": "key1",
            "value": "%needle%"
            }
        ]
    }
```

Each generated environment will replace _%needle%_ with the environment name.

If the json string used as input to generate the postman environment collection is stored in a file, it is required to use __PostmanEnvironmentGenerator__, for example:_

```java
    String inputFile = "input.json";
    String outputPath = "environment";
    PostmanEnvironmentGenerator generator = new PostmanEnvironmentGenerator();
    List<String> generatedFiles = generator.generate(inputFile, outputPath);
```

##How to include in any project?

For Maven:

```
    <dependency>
        <groupId>com.darkestapp</groupId>
        <artifactId>postman-environment-generator</artifactId>
        <version>0.1.3</version>
        <type>pom</type>
    </dependency>
```


For gradle:


```
compile 'com.darkestapp:postman-environment-generator:0.1.3'
```
