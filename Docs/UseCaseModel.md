# Use Case Model
**Author**: Team 102

**Version**: V1004.1

## 1 Use Case Diagram

![alt text](../../.images/use_case_diagram.png "Use Case Diagram")

## 2 Use Case Descriptions

### UC 1: Adjust Game Setting
- **Requirements:** The player should be able to adjust game settings.
- **Pre-conditions:** The player chooses to adjust game setting.
- **Post-conditions:** 
    * The game settings are updated OR
    * An error message is displayed.
- **Scenario 1:**
    * The player updates the maximum number of turns, the pool of letters information with valid data.
    * The game settings should be updated.
- **Scenario 2:**
    * The player updates the maximum number of turns and/or the pool of letters information with invalid data.
    * An error message should be displayed to inform the player that the input data is invalid.
    
### UC 2: Play a Game consisting "Play a New Game" and "Play an In-Progress Game"
- **Requirements:** The player should be able to play a game.
- **Pre-conditions:** The player chooses to play a game.
- **Post-conditions:** The game screen with a board and a rack is displayed.
- **Scenario 1:**
    * There is no in-progress game.
    * A new game including a new board and a new rack should be displayed to the player.
- **Scenario 2:**
    * There is an in-progress game.
    * The in-progress game including the existing board and rack should be displayed to the player.
    
### UC 3: Enter a Word
- **Requirements:** The player should be able to enter a word when playing a game. The word should be made up of one or more letters from the rack and one from the board. Also, the word must contain only valid letters and may not be repeated within a single game.
- **Pre-conditions:** A game is in-progress, so the board and the rack are shown.
- **Post-conditions:** 
    * The player's score is increased, and letters used in the board and rack are replaced, OR
    * An error message is displayed.
- **Scenario 1:**
    * The player enters a valid word.
    * The player's score should be increased by the total number of points for the letters in the word.
    * The letter used on the board should be replaced by a different random letter from the played word.
    * The letters used from the rack should be replaced by letters in the pool.
- **Scenario 2:**
    * The player enters a valid word.
    * The player's score should be increased by the total number of points for the letters in the word.
    * The game should end because the max number of turns is reached.
- **Scenario 3:**
    * The player enters a valid word.
    * The player's score should be increased by the total number of points for the letters in the word.
    * The game should end because the pool of letters is empty.
- **Scenario 4:**
    * The player enters an invalid or a repeated word.
    * An error message should be displayed to inform the player that the input word is invalid/repeated.

### UC 4: Swap Letters
- **Requirements:** The player should be able to swap 1-7 letters from the rack with letters from the pool of letters.
- **Pre-conditions:** A game is in-progress and the rack is shown.
- **Post-conditions:** 
    * The rack shows new letters which are swapped from the pool of letters, OR
    * An error message should be displayed.
- **Scenario 1:**
    * The player chooses  to swap letters.
    * The letters in the rack should be swapped.
- **Scenario 2:**
    * The player chooses  to swap letters.
    * An error message should be displayed to inform the player that there is no more letters in the pool to be swapped.

### UC 5: Leave a Game
- **Requirements:** The player should be able to leave a game.
- **Pre-conditions:** A game is in-progress.
- **Post-conditions:** The player leaves the game.
- **Scenario 1:**
    * The player is playing a game and chooses to leave the game.
    * The home screen should be displayed to the player.

### UC 6: View Score Statistics
- **Requirements:** The player should be able to view game score statistics.
- **Pre-conditions:** The player chooses to view game score statistics.
- **Post-conditions:** Game score statistics are shown. 
- **Scenario 1:**
    * The list of scores including final game score, the number of turns in that game and the average score per turn should be displayed in descending order by final game score.
    * The player selects a game score.
    * The settings for that game including maximum number of turns, letter distribution, and letter points should be displayed.

### UC 7: View Letter Statistics
- **Requirements:** The player should be able to view letter statistics.
- **Pre-conditions:** The player chooses to view letter statistics.
- **Post-conditions:** Letter statistics are shown.
- **Scenario 1:**
    A list of letters, in ascending order by number of times played, should display:
    * The total number of times that letter has been played in a word.
    * The total number of times that letter has been traded back into the pool.
    * The percentage of times that the letter is used in a word, out of the total number of times it has been drawn.

### UC 8: View Word Bank
- **Requirements:** The player should be able to view word bank.
- **Pre-conditions:** The player chooses to view word bank.
- **Post-conditions:** Word bank is shown.
- **Scenario 1:**
    A list of words, starting from the most recently played, should display:
    * The word.
    * The number of times the word has been played.

<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE4MzQ2MjczNF19
-->
