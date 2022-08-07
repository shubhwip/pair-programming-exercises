# Cookie Analyzer
Cookie analyzer parse cookies data given in csv file and gives back required output as per customer's input filters.

## Technologies Used
* Java
* Maven
* Logback Core
* Shell Scripting
* Docker

## Prerequisites
* Java8
* Maven3.6
* Bash Shell
* Linux

## Run

##### Go Inside the Project
```cd CookieAnalyzer```
##### Build the Project
```mvn clean compile assembly:single```
##### Run the Solution
```bash cookie-analyzer.sh -f src/main/resources/data.csv -d 2018-12-09```

### Docker way
##### Go Inside the Project
```cd CookieAnalyzer```
##### Build a Image
```docker image build -t ca . ```
##### Run a docker container
```docker container run -it ca:latest /bin/bash -c cd /root/CookieAnalyzer; bash cookie-analyzer.sh -f src/main/resources/data.csv -d 2018-12-09```
## FootNotes
* Logs will be created in Project directory with the name as cookie-analyzer.log
###### My System Configuration
* Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
* Maven home: /opt/maven
* Java version: 1.8.0_265, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.265.b01-1.el7_9.x86_64/jre
* Default locale: en_US, platform encoding: ANSI_X3.4-1968
* OS name: "linux", version: "3.10.0-1160.2.2.el7.x86_64", arch: "amd64", family: "unix"