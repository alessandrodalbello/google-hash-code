# Google Hash Code 2020

Solutions for all the rounds of Google Hash Code 2020 competition.

## How to

 - Build and install all the modules into local Maven repository (from project root):
    
    ```shell script
    $ mvn clean install
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