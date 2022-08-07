#Zopa-Lending-Service
There is a need for an application to find a quote from Zopa’s market of lenders for 36-month
loans that apply interest on a monthly basis.
Each lender in the market offers an amount of money to lend and the annual interest rate
they expect in return. 

The table below provides an example of market data:
Lender Rate Available

Jane 0.075 1200

John 0.081 350

To ensure that Zopa's quotes are competitive, select a combination of lenders’ offers which
gives the lowest possible rate. The monthly repayment and the total repayment amounts
should be shown in addition to the amount requested and the annual interest rate for the
quote.

Repayment amounts should be displayed to two decimal places and the annual interest rate
displayed to one decimal place.

A quote may be requested in any £100 increment between £1000 and £15000 inclusive. If
the market does not have enough offers to fulfil the request, then the application should
output “It is not possible to provide a quote.”

##Technologies Used
* Java
* Maven
* Shell Scripting
* Docker

##Prerequisites
* Java8
* Maven3.6
* Bash Shell
* Linux

##Run

######Note : If you want to try out this application with your data, Update lenders.csv file present in
######src/main/resources/lenders.csv.

###Running Java Application directly
#####Go Inside the Project
```cd zopa-lending-service```
#####Build the Project
```mvn clean compile assembly:single```
#####Run the Solution
```java -jar target/zopa-lending-service-1.0-jar-with-dependencies.jar 1000```


###Running Java Application Via Shell Script
#####Go Inside the Project
```cd zopa-lending-service```
#####Build the Project
```mvn clean compile assembly:single```
#####Run the Solution
```bash zopa-rate.sh 1700```

###Docker way
#####Go Inside the Project
```cd zopa-lending-service```
#####Build a Image
```docker image build -t zls . ```
#####Run a docker container
```docker container run -it zls:latest /bin/bash -c cd /root/zopa-lending-service; bash zopa-rate.sh 1000```

##FootNotes
###### My System Configuration
* Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
* Maven home: /opt/maven
* Java version: 1.8.0_265, vendor: Oracle Corporation, runtime: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.265.b01-1.el7_9.x86_64/jre
* Default locale: en_US, platform encoding: ANSI_X3.4-1968
* OS name: "linux", version: "3.10.0-1160.2.2.el7.x86_64", arch: "amd64", family: "unix"