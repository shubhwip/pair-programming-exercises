# Pair Programming Exercises
You can refer to this repository as collection of machine coding or pair programming exercises.
The goal of this repository to provide some real life problems which can be solved in time bounded fashion with limited features to show your machine coding and pair programming capabilities.
Many of these problems have been asked in interview rounds.

## Structure
Each Subproject has a README.md file which details out the problem statement. There are few refactoring exercises as well with names starting from refactoring-exercise-*.
Now there is a solution available in each of the subproject and it is written in java and related frameworks.

## How to approach ?
- Read the Problem statment
- Start a timer for 2 hours and create a boiler plate project in your favourite language
- Find a buddy if possible to pair program
- Make some assumptions if needed and write them out
- Write down the initial approach
- Start with writing few simpler test cases
- Start the implementation
- Refactor and repeat

## What happens aftermath ?
Now once your timer has stopped and you're done with something real in your hand. Go ahead and ask yourself below questions
- How far this solution is away from production grade ?
- Given more time, what could be done for better ?
- Did you overengineer your solution by any chance ?
- Were your intial assumptions wrong ?
Keep in mind that there is nothing like best solution, you make some tradeoffs while designing a solution 
and nobody is expecting production grade solution in 1 or 2 hours. It is just a matter of convesation and driving discussion
around important parts of your solution and all choices you made.

## Best Practices
- Use Design Patterns and SOLID Principles everywhere in your project
- Add Logging and exception handling
- Remove Verbosity as much as possible
- Add Dockerfile to run your solution in isolation
- Add README.md file and document everything
    - Prerequisites
    - Configuration
    - How to Run the Project
    - Design Decisions
    - Future Support
    - Footnotes
- Make your work loosely coupled and highly cohesive
- Care for thread safety