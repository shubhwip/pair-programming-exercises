# SplitWise

## Technology Used
- Java 11
- Maven 3.8.5
- Lombok 1.18.24
- Junit 5
- Docker

## Workflow
- Create Users (For this application I have created five dummy users and hardcoded them)
- Send Split Commands and Get Split Amount

## Run
## Run Splitwise CLI Application Command Line
### Go Inside the Project
```$ cd splitwise-demo```

### Build the Project
```$ mvn clean compile assembly:single```

### Run the Solution
- Input format will be [userId, amount, splitType{perc/equal/abs}, splitUserIdsSepartedByCommas, splitAmountForEachUserIdSeparatedByCommas]
- #### Sample Run Commands
- ##### Percentage Based Split
```$ java -jar target/splitwise-demo-1.0-jar-with-dependencies.jar 1 107 perc 2,3,4 12,14,17```

```[main] INFO Main - Splitted Transaction Details SplitTransaction(lenderId=1, borrowers=[Borrower(lenderId=1, borrowerId=2, amount=12.84, percentageSplit=Optional[12], absoluteSplit=null), Borrower(lenderId=1, borrowerId=3, amount=14.98, percentageSplit=Optional[14], absoluteSplit=null), Borrower(lenderId=1, borrowerId=4, amount=18.19, percentageSplit=Optional[17], absoluteSplit=null)], amount=107)```

- ##### Equal Split
```$ java -jar target/splitwise-demo-1.0-jar-with-dependencies.jar 1 100 equal 2,3,4```

```[main] INFO Main - Splitted Transaction Details SplitTransaction(lenderId=1, borrowers=[Borrower(lenderId=1, borrowerId=2, amount=25, percentageSplit=null, absoluteSplit=null), Borrower(lenderId=1, borrowerId=3, amount=25, percentageSplit=null, absoluteSplit=null), Borrower(lenderId=1, borrowerId=4, amount=25, percentageSplit=null, absoluteSplit=null)], amount=100)```

- ##### Absolute Split
  ```$ java -jar target/splitwise-demo-1.0-jar-with-dependencies.jar 1 100 abs 2,3,4 20,20,30```

```[main] INFO Main - Splitted Transaction Details SplitTransaction(lenderId=1, borrowers=[Borrower(lenderId=1, borrowerId=2, amount=20, percentageSplit=null, absoluteSplit=Optional[20]), Borrower(lenderId=1, borrowerId=3, amount=20, percentageSplit=null, absoluteSplit=Optional[20]), Borrower(lenderId=1, borrowerId=4, amount=30, percentageSplit=null, absoluteSplit=Optional[30])], amount=100)```



## Design Patterns and Decisions Made

- Data Type : While starting this exercise I used int values in data models and later realized
  that we would need floating point precision here because amount at the every layer would
  be floating type in major cases and thus adopted BigDecimal which gives floating point
  accuracy. This was very critical decision because for monetary calculations' precision is must.

- Builder Pattern :
  I used Builder pattern i wanted to build many objects partially and therefore
  populate some of their values in later journey of the user experience.

- Factory Pattern :
  Factor pattern was essential here to get instance of Split Strategy based on
  User's selected split strategy.

- Test Driven Development :
  - Wrote unit tests cases for split use cases and edge cases for multi user cases

- Input Validation :
  Added input validations on several cases where
    - Sum of Percent split among users exceed 100 Percent
    - Sum of Absolute split among users exceed the original amount to split
    - When using Percent split, value of percent split is undefined for some user
    - When using Absolute split, value of absolute split is undefined for some user


## Future Support
- Multithreaded Access
- Extensive Reports Building
- Simplify Debts
- More Logging
- More Input Validation and handling exceptional conditions
- Moving towards databases
- Building REST APIs
- Simplify Inputs