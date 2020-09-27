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
Game uses a 2 tables.
 1. CONNECT4_GAME_DATA - To store games played and state of the grid 
 2. CONNECT4_MATCH_DATA - To keep track of the moves taken by respective players
```
CREATE TABLE CONNECT4_GAME_DATA (
    TID INT,
    SESSION_ID VARCHAR,
    DATA BLOB,
    NEXT_PLAYER INT,
    ROWN INT,
    COLN INT,
    NUM INT,
    CURR_MOVE INT,
    IS_SESSION_OVER VARCHAR,
    PRIMARY KEY (TID)
);
```
1. SESSION_ID - stores the game's session id
2. DATA - stores the game grid in[][] as a blob
3. NEXT_PLAYER - next player who's move is due
4. ROWN, COLN, NUM - These represent the structure of the connect4 grid. This is configurable in the application.
5. CURR_MOVE - Counter to keep track of every valid move any player makes
6. IS_SESSION_OVER - Persist if the token can be used further or not.

<br>

```
CREATE TABLE CONNECT4_MATCH_DATA (
    TID INT,
    SESSION_ID VARCHAR,
    ROWN INT,
    COLN INT,
    PLAYER INT,
    MOVE INT,
    PRIMARY KEY (TID)
);
```
1. SESSION_ID - stores the game's session id
2. ROWN, COLN - stores the co-ordinate of a particular match move where the dropped coin reaches
3. PLAYER - player to whom the coin belongs for the current move
4. MOVE - Current move number