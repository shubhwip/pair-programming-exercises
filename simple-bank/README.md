#Ballys Coding Test
Write a class Account that offers the following methods 

- void deposit(int) 
- void withdraw(int) 
- String printStatement()

An example statement would be:

```Date        Amount  Balance
24.12.2015   +500      500
23.8.2016    -100      400
```
#### Some Clarifying Questions for Initial Implementation 
- Support for Single User
- How large value of amount can be ?

#### Things I would do in more time
- Making it more extensible and robust for future use cases
  - Adding Separate Design for printing/downloading transaction statements(in pdf, excel and csv etc.)
    - Adding Support for summary for weekly/monthly/fiscal year/time bounded statements
  - Bring Immutability to avoid security breaches like Making Transaction class immutable
  - Also Adding support for source of transaction like how deposit/withdrawl has been done(like from ATM/BANK/CHEQUE/TRANSFER)
- Exception Handling - InsufficientAmountException, 
- More Tests to cover edge cases - integer overflow, 
- Validations to check transaction
- Logging for error tracking and control flow of the program
- Adding Support for concurrent users to deposit and withdraw operation
- BigDecimal for floating point accuracy
- Easy Deployment of Account Application with Dockerfile.
  
