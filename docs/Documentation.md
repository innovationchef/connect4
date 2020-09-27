# Connect4 Game Backend

### Features
1. If a game is over(i.e., either player wins), that token cannot be used any longer
2. Until a valid move by player A is made, player B cannot take his move
3. Moves outside the grid are treated as invalid. Next player won't get a chance until the current player takes his valid move
4. If all moves are exhausted - 6 * 7 = 42, game is over with neither player winning


### Customisations supported
1. User can update the DB connection to connect to a permanent DB. Default is set to H2 database
2. File rollover log appender is provided in the dev profile which can be enabled
3. Grid size for the game can be updated along with number of coins to consider a win (default is row-6 || col-7 || num-4)

### DB Design
Game uses a single table to save all game and states in 1 entry. 
```
CREATE TABLE CONNECT4_DATA (
    TID INT,
    SESSION_ID VARCHAR,
    DATA BLOB,
    NEXT_PLAYER INT,
    MAX_MOVES INT,
    CURR_MOVE INT,
    IS_SESSION_OVER VARCHAR,
    PRIMARY KEY (TID)
);
```
1. SESSION_ID - stores the game's session id
2. DATA - stores the game grid in[][] as a blob
3. NEXT_PLAYER - next player who's move is due
4. MAX_MOVES - Grid size metadata to store max moves possible on the grid
5. CURR_MOVE - Counter to keep track of every valid move any player makes
6. IS_SESSION_OVER - Persist if the token can be used further or not.