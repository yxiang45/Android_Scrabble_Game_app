# Test Plan

**Author**: Team 102

**Version**: V1011.1

## 1 Testing Strategy

### 1.1 Overall strategy

- **Unit Testing:** In this section, cases will be applied to each individual unit written by Java. Each unit should be confirmed that pass all testing case and is ready to merge into the development branch. 
- **Functional Testing:** In this section, functions (or features) are tested by feeding them input and examining the output. Functional testing ensures that the requirements are properly satisfied by the application.
- **UI Testing:** In this section, the process is implemented to test the system's User Interface of the application under test. UI testing involves checking the screens with controls like menus, buttons, icons, dialog boxes, and windows, etc. 
- **Database Testing:** In this section, it is going to check if data are stored properly.
- **Performance Testing:** In this section, the test is applied to determine the responsiveness and stability of the application on different mobile devices. 

### 1.2 Test Selection

- The black-box technique Use Case Testing will be applied to Functional and UI testings to ensure the functionalities of the application cover all the requirements.
- The black-box technique Boundary Value Analysis will be applied to test the corner cases.
- The white-box technique Condition/Decision Coverage will be applied to Unit Testing.

### 1.3 Adequacy Criterion

- The white-box testing used in each testing stage should maximize the coverage of more than 90%. 
- The black-box testing used in each testing stage should meet all requirements from customers.
- After all testings applied to the application, the system robustness should be maximized (stable and faster enough).

### 1.4 Bug Tracking

All bugs, that are found in all testings, will be tracked in an Excel file shared on the team repository.
The Excel file will record the short description, steps to reproduce, current result and expected to result for each bug.

### 1.5 Technology

The following shows the technology tools, which are utilized for each part of testing strategies:
- Unit Testing: JUnit.
- Functional Testing: Manual testing.
- UI Testing: Manual testing.
- Database Testing: JUnit, Manual testing.
- Performance Testing: JUnit.

## 2 Test Cases

| No. | Purpose | Steps to execute | Expected Result  | Actual Result  | Pass/Fail | Notes |
| --- | ------------ | ------------ | ------------ | ------ | ------ | ------ |
| 1 | Launch the game | 1. Compile the code and run the app. <br/> 2. The home page shows up. | The home page showed up after ran the app first time. | The home page shows up after run the app. | Pass | N/A |
| 2 | Adjust game settings with invalid data | 1. In the home screen, choose to adjust game settings. <br/> 2. Update the maximum number of turns, the pool of letters information with invalid data (negative number/ special characters). | An error message should be displayed to inform the player that the input data is invalid. | The app does not allow you to input invalid data in the settings page. | Pass | N/A |
| 3 | Play a new game | In the home screen, choose to play a game.<br/>  | 1. A new board consisting 4 letters and a new rack consisting 7 letters should be shown.<br/> 2. All the letters in the board and in the rack should be from the pool of letters. | The game is created properly and there is a board and rack for players to choose from. | Pass | N/A |
| 4 | Leave the game before it ends | Before the game in the previous test case ends, choose to leave the game. | The home screen should be displayed to the player. | Player can leave a current game. | Pass | N/A |
| 5 | Play an in-progress game | After the previous test case is done, choose to play a game again. | The player should be returned to the game in progress in the previous test case. | Player can continue an in progress game. | Pass | N/A |
| 6 | Enter a valid word | In the game screen, enter a valid word which is: made up of one or more letters from the rack and one from the board, must contain only valid letters, and may not repeated within the current game. | 1. The player's score should be increased by the total number of points for the letters in the word.<br/> 2. The letter used on the board should be replaced by a different random letter from the word.<br/> 3. The letters used from the rack should be replaced from the pool of letters.| The plater can enter a valid word and the word appears. | Pass | N/A |
| 7 | Enter a valid word in the last turn | Play the game until the last turn and enter a valid word. | 1. The player's score should be increased by the total number of points for the letters in the word.<br/> 2. The game should end. | A player can play a valid word at the end and the final score is shown at the end of the game. | Pass | N/A |
| 8 | Enter a valid word until the pool of letters is empty | Play the game until the pool of letters is empty.  | 1. The player's score should be increased by the total number of points for the letters in the word.<br/> 2. The game should end. | The game ends when the pool of letters is empty. | Pass | N/A |
| 9 | Enter an invalid word | Enter a word that is not made up of any letters from the rack and/or any letters from the board. | An error message should be displayed to inform the player that the word is invalid. | An error message appears. | Pass | N/A |
| 10 | Enter an invalid word | Enter a word that contains a digit or a special character. | An error message should be displayed to inform the player that the word is invalid. | A player cannot enter an invalid word with a special character. | N/A | N/A |
| 11 | Enter a repeated word | Enter a word that was already played in the current game. | An error message should be displayed to inform the player that the word is repeated. | An error message appears when attempting to repeat a word played in that game. | Pass | N/A |
| 12 | Swap letters | In the game screen, choose to swap letters.  | Letters in the rack should be swapped with letters from the pool of letters. | Letters from the rack can be swapped. | Pass | N/A |
| 13 | Swap letters when the pool of letters is empty | 1. Play the game until the pool of letters is empty.<br/> 2. Choose to swap letters.  | An error message should be displayed to inform the player that there is no more letters in the pool to be swapped. | An error message appears when trying to swap letters when there are not enough in the pool. | Pass | N/A |
| 14 | View score statistic | Choose to view score statistics.  | The list of scores including final game score, the number of turns in that game and the average score per turn should be displayed in descending order by final game score. | Player can view score stats. | Pass | N/A |
| 15 | View settings of a game | In the score statistics screen, select a game score.  | The settings for that game including maximum number of turns, letter distribution, and letter points should be displayed. | The settings are displayed correctly. | Pass | N/A |
| 16 | View letter statistics | Choose to view letter statistics.  |  A list of letters, in ascending order by number of times played, should display:<br/> 1. The total number of times that letter has been played in a word.<br/> 2. The total number of times that letter has been traded back into the pool.<br/> 3. The percentage of times that the letter is used in a word, out of the total number of times it has been drawn. | Letter statistics are diplayed correctly | Pass | N/A |
| 17 | View word bank | Choose to view word bank.  | A list of words, starting from the most recently played, should display:<br/> 1. The word.<br/> 2. The number of times the word has been played. | Word Bank is displayed correctly. | Pass | N/A |
