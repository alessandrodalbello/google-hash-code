# Google Hash Code 2020
![build](https://github.com/alessandrodalbello/google-hash-code-2020/workflows/Google%20Hash%20Code%202020%20-%20Java%20CI/badge.svg?branch=master&event=push)
![java: jdk11](https://img.shields.io/badge/java-JDK%2011-red)
[![license: MIT](https://img.shields.io/badge/license-MIT-green.svg)](https://opensource.org/licenses/MIT)

Solutions for all the rounds of Google Hash Code 2020 competition.

## How to

 - Build and package all the modules (from project root):
    
    ```shell script
    $ mvn clean package
    ```

 - Run from executable JAR (from module):

    ```shell script
    $ java -jar target/<MODULE_ARTIFACT_NAME>-exec.jar <ARGUMENTS>
    ```

   for instance

    ```shell script
    $ java -jar target/hashcode-2020-practice-exec.jar a b c
    ```

 - Zip source code into an archive (from module):

    ```shell script
    $ mvn clean assembly:single@zip-source-code
    ```