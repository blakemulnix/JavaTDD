# Basic Java Project Setup Using Maven

This is a guide for setting up a basic Java Maven project in VSCode.

### Install Java Extension Pack

1. Install VSCode Extension Pack For Java
2. Ensure Java is installed with 'java -version'
3. Ensure Maven is installed with 'mvn -version'

### Create Basic Maven Project

Run this command to generate a basic Maven project:

```
 mvn archetype:generate 
    -DgroupId=com.tdd.app 
    -DartifactId=java-tdd 
    -DarchetypeArtifactId=maven-archetype-quickstart 
    -DarchetypeVersion=1.4 
    -DinteractiveMode=false
 ```

 ### Run the tests

 Navigate to the project root (`java-tdd`) and run the following to kick off the tests for the project:
 
 `mvn test` 
 
