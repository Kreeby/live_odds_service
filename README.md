# Live Odds Services

Developed a new Live Football World Cup Scoreboard library that shows all the ongoing matches and their scores.

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Gradle build tool (optional, for building and running tests)

## Summary of Solution

Chose the TDD strategy, which TDD approach - first we write tests for the class, 
then the implementation. We move through the levels of abstraction - from the highest to the 
applied one, simultaneously breaking the application into layers-classes, 
from which we order the behavior we need, being free from a specific implementation.

Therefore, first steps of development was to create boilerplate (models and interface methods) and tests!
Below you can find all the steps of implementation:

## Steps of Implementation

1. **Create the Team Class**: The `Team` class is a class that represents the individual team. It should have a name and score

2. **Create the Match Class**: The `Match` class a class that represents the individual match between two `Team`- s. 
It has `homeTeam` and `awayTeam`, `startDate` which gives the starting date of the match 
and `isFinished` stating about the status of the match

3. **Create the Summary Class**: The `Summary` class is a class that represents the summary of the match. 
It has the match which summary is about, the total score of the match and the date of the match

4. **Create the Scoreboard Class**: The `Scoreboard` class is a class that represents all the matches. 
5. **Create the MatchHandler Class**: The `MatchHandler` class to act as an interface with all the needed methods for our purpose. 
It can start / finish / update the match, and also give the summary in a proper order
6. **Write Unit Tests**: As I go with the TDD approach after having a boilerplate, I start working on the Unit Tests. 
I tried to cover most of the edge cases and put them in `MatchHandlerTest` class
7. **Implement MatchHandler Methods**: After I wrote the unit tests and run them I see that all of them are failed. 
Now I start writing the implementation for the MatchHandler interface class.
8. **Run Tests**: At the last step, I run the tests on the written implementation and see whether all the tests successes. 
If not, I have to work on the implementation only!