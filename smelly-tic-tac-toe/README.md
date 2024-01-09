# Smelly TicTacToe
Link to original Smelly TicTacToe : https://github.com/AgileTechPraxis/CodeSmells

## My Observations
### Hardcoding
- Board is hardcoded to be of 3x3 and TicTacToe can extend to 20x20.
- All the logic of finding winner is also confined to 3x3 based board.
- board is hardcoded inside Game implementation but it should be injected as dependency.

### Over-engineering
- Board can be simple 2D array than a complex POJO object.
- Unnecessary `_plays` list which can be removed.

### Code Conventions
- None of java's code conventions were followed
  - Variable names
  - Method names

### Exceptional Scenarios
- All are covered with checked exceptions but some areas should be
  covered with unchecked exceptions.

### Functional Bugs
- It only checks for rows for winner and misses columns and diagonals.

### Code Smells
- Tile is not immutable class thus state of Tile can be mutated easily.
- Message chain code smell
  - `_board.TileAt(0, 0).Symbol` - can be wrapped into one method.
  - `_board.TileAt` - used excessively in Game class, an example of feature envy.
- Object calisthenics violation
  - One level of indentation is violated in Winner() method
  - Wrap all primitives, In Game class symbol is not wrapped.
  - `_board.TileAt(0, 1).Symbol` - One dot per line
  - No classes with more than 2 instance variables
    - Not sure about Tile class
  - Duplicated Code in Winner() - can be simplified.
- Bloaters
  - Game is Large class more than 50 lines
  - Winner is Long method
  - Triadic Method Play(), can pass Tile as an argument.
- Data Clumps

### Shotgun Surgery

###