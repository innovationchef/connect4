### Connect4

Build a Backend API which plays Connect 4 with 2 users. You have a matrix of  7 columns with 6 rows. 2 coins of red and yellow colour can be dropped in any column.
The coin is dropped from above and it gets stacked in the bottommost available row. You have to build an API which validates and checks if a Valid move is made & must
show who wins whenever red or yellow connects 4 coins in a row or column or diagonally of the same colour. Yellow always goes 1st or every valid odd move.
Red always goes second or every valid even move.

Requirements:
- [ ] Write a backend which exposes an API which returns the necessary response.
- [ ] When a Request “START” is sent to the API, it must send a response of “READY” after resetting the game of Connect 4 and starting a fresh game.
- [ ] Whenever a column is sent as a request, Eg. 0, 1, 2, … 6. A coin must be dropped in that column.  response must be either “Valid” or “Invalid”.
  Every “Valid” move must be considered as a move. Every “Invalid” move should wait for the next request which is valid.

Further:
- [ ] Assign a random unique token/username every time “START” is sent to the API. & return this token/username. Parallel games must be possible with the use of this.
  Every subsequent request needs to be made along with this token/username.
- [ ] Use a Database to store all the moves associated with a token/username. A “GET” request to this API alongwith said token must fetch all the moves made up to that point.
- [ ] The API must return “Yellow wins” or “Red wins” whenever an odd or even “Valid” move connects 4 coins of the same colour in a row or column or a diagonal.

Even Further:
- [ ] Host/Deploy this API on some platform like Heroku


Note:
1. You are free to use any Request/Response format of your choice that you feel is appropriate.
2. You can play a sample Connect 4 game here https://www.mathsisfun.com/games/connect4.html
