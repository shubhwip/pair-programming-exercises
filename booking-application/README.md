## Problem Statement
Write a very simplified version of booking system. In fact it can be 
written as a single class which should
- return a list of booked hours.
- not allow a particular hour to be double booked
- deal in a sensible manner with illegal values (provided as input parameters)
### Constraints - The system
- has only one resource that can be booked(e.g. a classroom, a lawn mover, a restaurant table or
anything that makes sense to you)
- has a notion of days, or to put it differently, it assumes all reservations 
are for today.
- should only permit booking of regular whole clock-hours(e.g. it should not allow 
a booking from 4:30 PM to 5:30 PM)
- is not required to remember any additional information concerning the reservation
(who booked it, when etc.).

## Problem Reference
Practical Unit Testing with Junit and Mockito by Tomek Kaczanowski.