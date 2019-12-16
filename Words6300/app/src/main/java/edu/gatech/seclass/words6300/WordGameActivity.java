package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pools;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordGameActivity extends AppCompatActivity implements View.OnClickListener {

    private int NUMLETTERSONBOARD = 4;
    private int MAXNUMLETTERSONRACK = 7;
    private int EMPTYPOOLBONUSSCORE = 10;

    private int score;
    private int currentTurn;
    private int maxTurns;
    private ArrayList<Tile> boardTilesPicked = new ArrayList<Tile>();
    private ArrayList<Tile> rackTilesPicked = new ArrayList<Tile>();
    private ArrayList<Tile> tilesPicked = new ArrayList<Tile>();
    private ArrayList<String> wordsPlayed = new ArrayList<String>();
    private String currentWord = "";

    private Board board;
    private Rack rack;
    private LetterPool pool;

    private Random rand = new Random();
    private static File DATA_PATH;
    private boolean createNewGame;

    Button playWordButton;
    Button swapLettersButton;
    Button backToHomeFromGameButton;
    TextView boardTile0;
    TextView boardTile1;
    TextView boardTile2;
    TextView boardTile3;
    TextView rackTile0;
    TextView rackTile1;
    TextView rackTile2;
    TextView rackTile3;
    TextView rackTile4;
    TextView rackTile5;
    TextView rackTile6;

    Toast toast;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);// Add THIS LINE

        setContentView(R.layout.activity_word_game);

        createNewGame = true;
        Bundle b = getIntent().getExtras();
        if(b != null) {
            System.out.println(b.getBoolean("newGame"));
            createNewGame = b.getBoolean("newGame");
        }

        playWordButton = (Button) findViewById(R.id.playWordButton);
        swapLettersButton = (Button) findViewById(R.id.swapLettersButton);
        backToHomeFromGameButton = (Button) findViewById(R.id.backToHomeFromGameButton);

        boardTile0 = findViewById(R.id.board_letter_0);
        boardTile1 = findViewById(R.id.board_letter_1);
        boardTile2 = findViewById(R.id.board_letter_2);
        boardTile3 = findViewById(R.id.board_letter_3);

        rackTile0 = findViewById(R.id.rack_letter_0);
        rackTile1 = findViewById(R.id.rack_letter_1);
        rackTile2 = findViewById(R.id.rack_letter_2);
        rackTile3 = findViewById(R.id.rack_letter_3);
        rackTile4 = findViewById(R.id.rack_letter_4);
        rackTile5 = findViewById(R.id.rack_letter_5);
        rackTile6 = findViewById(R.id.rack_letter_6);

        playWordButton.setOnClickListener(this);
        swapLettersButton.setOnClickListener(this);
        backToHomeFromGameButton.setOnClickListener(this);

        DATA_PATH=WordGameActivity.this.getFilesDir();

        try {
            if (createNewGame) {
                startNewGame();
                updateUI();
            } else {
                continueGame();
                updateUI();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        clearErrors();

        try {
            if(v==playWordButton){
                playWord();
            }

            else if(v==swapLettersButton){
                swapLetters();
            }

            else if(v==backToHomeFromGameButton){
                Intent intentApp = new Intent(this, Words6300.class);
                startActivity(intentApp);
            }

            else if(v==boardTile0){ boardTileClicked(board.getBoardTile(0)); }
            else if(v==boardTile1){ boardTileClicked(board.getBoardTile(1)); }
            else if(v==boardTile2){ boardTileClicked(board.getBoardTile(2)); }
            else if(v==boardTile3){ boardTileClicked(board.getBoardTile(3)); }
            else if(v==rackTile0){ rackTileClicked(rack.getRackTile(0)); }
            else if(v==rackTile1){ rackTileClicked(rack.getRackTile(1)); }
            else if(v==rackTile2){ rackTileClicked(rack.getRackTile(2)); }
            else if(v==rackTile3){ rackTileClicked(rack.getRackTile(3)); }
            else if(v==rackTile4){ rackTileClicked(rack.getRackTile(4)); }
            else if(v==rackTile5){ rackTileClicked(rack.getRackTile(5)); }
            else if(v==rackTile6){ rackTileClicked(rack.getRackTile(6)); }


            updateUI();
            saveCurrentGame();
            checkGameOver();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void startNewGame() throws FileNotFoundException {
        clearErrors();
        clearTilesPicked();

        // Initialize a new letter pool, board and rack
        pool = initializeLetterPoolFromGameSettingsFile();

        // Initialize a new board
        board = new Board();
        ArrayList<Tile> newBoardTiles = pool.getMultipleRandomTiles(NUMLETTERSONBOARD);
        board.addBoardTiles(newBoardTiles);

        // update letter statistics with letters drawn
        for(Tile tile : newBoardTiles ) { updateLetterStatisticsFile(tile.getLetter(),"drawn"); }

        // Initialize a new rack
        rack = new Rack();
        ArrayList<Tile> newRackTiles = pool.getMultipleRandomTiles(MAXNUMLETTERSONRACK);
        rack.addRackTiles(newRackTiles);

        // update letter statistics with letters drawn
        for(Tile tile : newRackTiles) { updateLetterStatisticsFile(tile.getLetter(),"drawn"); }

        // Initialize max number of turns
        maxTurns = readMaxTurnsFromGameSettingsFile();
        score = 0;
        currentTurn = 0;

        // Initialize current words played
        wordsPlayed = new ArrayList<String>();

        saveCurrentGame();
    }

    public void continueGame() throws FileNotFoundException {
        clearErrors();
        clearTilesPicked();

        try {
            // Initialize a new letter pool, board and rack
            pool = readLetterPoolFromCurrentGameFile();
            board = readCurrentBoardFile();
            rack = readCurrentRackFile();

            // Initialize max number of turns, current turn, and score
            maxTurns = readCurrentMaxTurns();
            currentTurn = readCurrTurnsFromCurrentGameFile();
            score = readCurrentScoreFromCurrentScoreFile();

            wordsPlayed = readCurrentGameWordsFile();

            saveCurrentGame();
        }
        catch (Exception e) {
            startNewGame();
        }
    }

    public void playWord() throws FileNotFoundException {
        if (boardTilesPicked.size() == 0) {
            playWordButton.setError("Must pick a board tile");
            showErrorMessage("Must pick a board tile");
            return;
        }
        if (boardTilesPicked.size() > 1) {
            playWordButton.setError("Must pick only one board tile");
            showErrorMessage("Must pick only one board tile");
            return;
        }
        else if (rackTilesPicked.size() == 0) {
            playWordButton.setError("Must pick at lest one rack tile");
            showErrorMessage("Must pick at lest one rack tile");
            return;
        }
        else if (wordsPlayed.contains(currentWord)) {
            playWordButton.setError("Word has already been played");
            showErrorMessage("Word has already been played");
            return;
        }

        // Add word to words played
        wordsPlayed.add(currentWord);

        // Play rack tiles
        for (Tile tile : rackTilesPicked) { addToScore(tile.getPoints()); }
        rack.removeRackTiles(rackTilesPicked);

        // Play board tile
        for (Tile tile : boardTilesPicked) { addToScore(tile.getPoints()); }
        board.removeBoardTiles(boardTilesPicked);

        // Add random rack tile to the board
        Tile newBoardTile = rackTilesPicked.get(rand.nextInt(rackTilesPicked.size()));
        board.addBoardTile(newBoardTile);

        // Replenish rack letters
        int numLettersToReplace = Math.min(pool.poolSize(), MAXNUMLETTERSONRACK - rack.rackSize());
        ArrayList<Tile> newRackTiles = pool.getMultipleRandomTiles(numLettersToReplace);
        rack.addRackTiles(newRackTiles);
        pool.removePoolTiles(newRackTiles);

        // update letter statistics with letters drawn
        for(Tile tile : newRackTiles ) { updateLetterStatisticsFile(tile.getLetter(),"drawn"); }

        // update letter statistics with letters played
        for(Tile tile : tilesPicked) { updateLetterStatisticsFile(tile.getLetter(),"played"); }

        increaseTurn();
        addToWordBank(currentWord);
        clearTilesPicked();
    }

    public void swapLetters() throws FileNotFoundException {
        if (boardTilesPicked.size() > 0) {
            swapLettersButton.setError("Cannot pick any board letters to swap");
            showErrorMessage("Cannot pick any board letters to swap");
            return;
        }
        else if (rackTilesPicked.size() == 0) {
            swapLettersButton.setError("Must pick at least one rack letter to swap");
            showErrorMessage("Must pick at least one rack letter to swap");
            return;
        }
        else if (rackTilesPicked.size() > pool.poolSize()) {
            swapLettersButton.setError("Not enough tiles in the pool to swap with");
            showErrorMessage("Not enough tiles in pool to swap with");
            return;
        }

        ArrayList<Tile> poolTilesToSwap = pool.getMultipleRandomTiles(rackTilesPicked.size());

        rack.swapRackTiles(poolTilesToSwap, rackTilesPicked);
        pool.swapPoolTiles(rackTilesPicked, poolTilesToSwap);

        // update letter statistics with letters swapped
        for(Tile tile : rackTilesPicked) { updateLetterStatisticsFile(tile.getLetter(),"traded"); }
        for(Tile tile : poolTilesToSwap) { updateLetterStatisticsFile(tile.getLetter(),"drawn"); }

        increaseTurn();
        clearTilesPicked();
    }

    private void boardTileClicked(Tile boardTile) {
        if (boardTilesPicked.contains(boardTile)) { boardTilesPicked.remove(boardTile); }
        else { boardTilesPicked.add(boardTile); }
        tileClicked(boardTile);
    }

    private void rackTileClicked(Tile rackTile) {
        if (rackTilesPicked.contains(rackTile)) { rackTilesPicked.remove(rackTile); }
        else { rackTilesPicked.add(rackTile); }
        tileClicked(rackTile);
    }

    private void tileClicked(Tile tileClicked) {
        if (tilesPicked.contains(tileClicked)) { tilesPicked.remove(tileClicked); }
        else { tilesPicked.add(tileClicked); }
        updateWord();
    }

    private void clearTilesPicked() {
        boardTilesPicked = new ArrayList<>();
        rackTilesPicked = new ArrayList<>();
        tilesPicked = new ArrayList<>();
        updateWord();
    }

    private void updateUI() {
        ArrayList<Tile> boardTiles = board.getBoardTiles();

        TextView bl0 = findViewById(R.id.board_letter_0);
        TextView bl1 = findViewById(R.id.board_letter_1);
        TextView bl2 = findViewById(R.id.board_letter_2);
        TextView bl3 = findViewById(R.id.board_letter_3);

        setLetterInUi(boardTiles, boardTilesPicked, bl0, 0);
        setLetterInUi(boardTiles, boardTilesPicked, bl1, 1);
        setLetterInUi(boardTiles, boardTilesPicked, bl2, 2);
        setLetterInUi(boardTiles, boardTilesPicked, bl3, 3);

        ArrayList<Tile> rackTiles = rack.getRackTiles();

        TextView rl0 = findViewById(R.id.rack_letter_0);
        TextView rl1 = findViewById(R.id.rack_letter_1);
        TextView rl2 = findViewById(R.id.rack_letter_2);
        TextView rl3 = findViewById(R.id.rack_letter_3);
        TextView rl4 = findViewById(R.id.rack_letter_4);
        TextView rl5 = findViewById(R.id.rack_letter_5);
        TextView rl6 = findViewById(R.id.rack_letter_6);

        setLetterInUi(rackTiles, rackTilesPicked, rl0, 0);
        setLetterInUi(rackTiles, rackTilesPicked, rl1, 1);
        setLetterInUi(rackTiles, rackTilesPicked, rl2, 2);
        setLetterInUi(rackTiles, rackTilesPicked, rl3, 3);
        setLetterInUi(rackTiles, rackTilesPicked, rl4, 4);
        setLetterInUi(rackTiles, rackTilesPicked, rl5, 5);
        setLetterInUi(rackTiles, rackTilesPicked, rl6, 6);

        TextView scoreText = findViewById(R.id.score_value);
        scoreText.setText(Integer.toString(score));

        TextView turnsRemaining = findViewById(R.id.turns_remaining_value);
        turnsRemaining.setText(Integer.toString(maxTurns - currentTurn));

        TextView poolLettersRemaining = findViewById(R.id.pool_letters_left_value);
        poolLettersRemaining.setText(Integer.toString(pool.poolSize()));

        updateWord();
    }

    private void showErrorMessage(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private void setLetterInUi(ArrayList<Tile> tiles, ArrayList<Tile> tilesPicked, TextView textUI, int index) {
        Tile tile = tiles.get(index);
        if (tilesPicked.contains(tile)) {
            textUI.setText(String.valueOf(tile.getLetter()));
            textUI.setTextColor(Color.GRAY);
        }
        else {
            String html = "<b>" + tile.getLetter() + "</b>";
            textUI.setText(Html.fromHtml(html));
            textUI.setTextColor(Color.BLACK);
        }
    }

    private void updateWord() {
        currentWord = "";
        for (Tile tile : tilesPicked) { currentWord += tile.getLetter(); }

        TextView poolLettersRemaining = findViewById(R.id.current_word_value);
        poolLettersRemaining.setText(currentWord);
    }

    private void clearErrors () {
        playWordButton.setError(null);
        swapLettersButton.setError(null);
        backToHomeFromGameButton.setError(null);
        try { toast.cancel(); }
        catch (Exception e) {}
    }

    private void saveCurrentGame() {
        saveCurrentBoardFile(board);
        saveCurrentRackFile(rack);
        saveCurrentTurnsAndLetterPoolToCurrentGameFile(currentTurn, pool);
        saveCurrentGameScore(score);
        saveCurrentGameWordsFile(wordsPlayed);
        saveCurrentMaxTurns(maxTurns);
    }

    private void addToScore(int addition) {
        score += addition;
    }

    private void increaseTurn() {
        currentTurn ++;
    }

    private void checkGameOver() throws FileNotFoundException {
        if (currentTurn >= maxTurns || pool.isEmpty()) {
            if (pool.isEmpty()) { addToScore(EMPTYPOOLBONUSSCORE); }

            String game = collectGameInfo();
            updateScoreStatisticsFile(game);

            // Save final score for next page
            Intent intent = new Intent(this, GameOverActivity.class);
            Bundle b = new Bundle();
            b.putInt("finalScore", score);
            intent.putExtras(b);

            startNewGame();

            startActivity(intent);
        }
    }

    public LetterPool initializeLetterPoolFromGameSettingsFile() throws FileNotFoundException {
        return readLetterPoolFromFile("gameSettings.txt");
    }

    // fileName can be gameSettings or currentGame.txt
    public LetterPool readLetterPoolFromFile(String fileName) throws FileNotFoundException {
        File file = new File(DATA_PATH, fileName);
        Scanner scanner = new Scanner(new FileReader(file.toString()));
        scanner.nextLine(); // maxNumOfTurns or currentTurns
        LetterPool pool = new LetterPool();
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 26; ++i) {
            String line = scanner.nextLine();
            String[] letterPointsFreq = line.split(",");
            for (int j = 0; j < Integer.parseInt(letterPointsFreq[2]); j++) {
                tiles.add(new Tile(letterPointsFreq[0].charAt(0), Integer.parseInt(letterPointsFreq[1])));
            }
        }
        pool.addToPool(tiles);
        return pool;
    }

    public int readMaxTurnsFromGameSettingsFile() throws FileNotFoundException {
        return readTurnsFromFile("gameSettings.txt");
    }

    public int readTurnsFromFile(String fileName) throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File file = new File(path, fileName);
        Scanner scanner = new Scanner(new File(file.toString()));
        return Integer.parseInt(scanner.nextLine());
    }

    public void saveCurrentBoardFile(Board cBoard) {
        File path = new File(DATA_PATH, "");
        File currentBoardFile = new File(path, "currentBoard.txt");
        if (currentBoardFile.exists()) {
            currentBoardFile.delete();
        }

        try
        {
            FileWriter writer = new FileWriter(currentBoardFile);
            for (Tile t: cBoard.getBoardTiles()) {
                writer.append(t.getLetter() + "," + t.getPoints() + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Board readCurrentBoardFile() throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File currentBoardFile = new File(path, "currentBoard.txt");
        Scanner scanner = new Scanner(new File(currentBoardFile.toString()));
        Board cBoard = new Board();
        while  (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tile = line.split(",");
            cBoard.addBoardTile(new Tile(tile[0].charAt(0), Integer.parseInt(tile[1])));
        }
        return cBoard;
    }

    public void saveCurrentRackFile(Rack cRack) {
        File path = new File(DATA_PATH, "");
        File currentRackFile = new File(path, "currentRack.txt");
        if (currentRackFile.exists()) {
            currentRackFile.delete();
        }

        try
        {
            FileWriter writer = new FileWriter(currentRackFile);
            for (Tile t: cRack.getRackTiles()) {
                writer.append(t.getLetter() + "," + t.getPoints() + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public Rack readCurrentRackFile() throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File currentRackFile = new File(path, "currentRack.txt");
        Scanner scanner = new Scanner(new File(currentRackFile.toString()));
        Rack cRack = new Rack();
        ArrayList<Tile> tiles = new ArrayList<>();
        while(scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] tile = line.split(",");
            tiles.add(new Tile(tile[0].charAt(0), Integer.parseInt(tile[1])));
        }
        cRack.addRackTiles(tiles);
        return cRack;
    }

    public void saveCurrentTurnsAndLetterPoolToCurrentGameFile(int currentTurn, LetterPool pool) {
        File path = new File(DATA_PATH, "");
        File currentGameFile = new File(path, "currentGame.txt");
        if (currentGameFile.exists()) {
            currentGameFile.delete();
        }

        try
        {
            FileWriter writer = new FileWriter(currentGameFile);
            writer.append(currentTurn + "\n");
            for (Tile tile : pool.getTiles()) {
                writer.append(tile.getLetter() + "," + tile.getPoints() + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public int readCurrTurnsFromCurrentGameFile() throws FileNotFoundException {
        return readTurnsFromFile("currentGame.txt");
    }

    public LetterPool readLetterPoolFromCurrentGameFile() throws FileNotFoundException {
        File file = new File(DATA_PATH, "currentGame.txt");
        Scanner scanner = new Scanner(new FileReader(file.toString()));
        scanner.nextLine(); // maxNumOfTurns or currentTurns
        LetterPool pool = new LetterPool();
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lettersPoints = line.split(",");
            tiles.add(new Tile(lettersPoints[0].charAt(0), Integer.parseInt(lettersPoints[1])));
        }
        pool.addToPool(tiles);
        return pool;
    }

    public void saveCurrentGameScore(int currentScore) {
        File path = new File(DATA_PATH, "");
        File currentScoreFile = new File(path, "currentScore.txt");
        if (currentScoreFile.exists()) {
            currentScoreFile.delete();
        }

        try
        {
            FileWriter writer = new FileWriter(currentScoreFile);
            writer.append(currentScore + "\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public int readCurrentScoreFromCurrentScoreFile() throws FileNotFoundException {
        File file = new File(DATA_PATH, "currentScore.txt");
        Scanner scanner = new Scanner(new FileReader(file.toString()));
        int currentScore = Integer.parseInt(scanner.nextLine()); // maxNumOfTurns or currentTurns
        return currentScore;
    }

    public void saveCurrentMaxTurns(int currentMaxTurns) {
        File path = new File(DATA_PATH, "");
        File currentScoreFile = new File(path, "currentMaxTurns.txt");
        if (currentScoreFile.exists()) {
            currentScoreFile.delete();
        }

        try
        {
            FileWriter writer = new FileWriter(currentScoreFile);
            writer.append(currentMaxTurns + "\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public int readCurrentMaxTurns() throws FileNotFoundException {
        File file = new File(DATA_PATH, "currentMaxTurns.txt");
        Scanner scanner = new Scanner(new FileReader(file.toString()));
        int maxTurns = Integer.parseInt(scanner.nextLine()); // maxNumOfTurns or currentTurns
        return maxTurns;
    }

    public void saveCurrentGameWordsFile(ArrayList<String> wordsPlayed) {
        File path = new File(DATA_PATH, "");
        File currentWordsPlayedFile = new File(path, "currentGameWords.txt");
        if (currentWordsPlayedFile.exists()) {
            currentWordsPlayedFile.delete();
        }

        try
        {
            FileWriter writer = new FileWriter(currentWordsPlayedFile);
            for (String word: wordsPlayed) {
                writer.append(word + "\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readCurrentGameWordsFile() throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File currentGameWordsFile = new File(path, "currentGameWords.txt");
        Scanner scanner = new Scanner(new File(currentGameWordsFile.toString()));
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNextLine()) {
            words.add(scanner.nextLine());
        }
        return words;
    }

    public void addToWordBank(String word) {
        File path = new File(DATA_PATH, "");
        File wordBankFile = new File(path, "wordBank.txt");

        try
        {
            FileWriter writer = new FileWriter(wordBankFile, true);
            writer.append(word + "\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * This creates a string of game info including final score, number of turns, settings (max number of turns, letter pool) to pass to updateScoreStatisticsFile(String)
     * Ex: "100,15,50,A,2,3,B,2,3,C,8,10,...,Z,1,1"
     * Final score: 100; number of turns: 15
     * Settings: Max number of turns: 10; Letter Pool: A,2,3,B,2,3,C,8,10,...,Z,1,1
     */
    public String collectGameInfo() throws FileNotFoundException {
        return score + "," + currentTurn + "," + maxTurns + "," + getLetterPointsFreqFromGameSettingsFile();
    }

    // This method adds a new line of a finished game info to the scoreStatistics file including final score, number of turns, settings (max number of turns, letter pool)
    public void updateScoreStatisticsFile(String newGame) {
        File path = new File(DATA_PATH, "");
        File scoreStatisticsFile = new File(path, "scoreStatistics.txt");
        try
        {
            FileWriter writer = new FileWriter(scoreStatisticsFile, true);
            writer.append(newGame + "\n");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    // Combine letter, points and freq of all letters in one string. Ex: A,2,3,B,2,3,C,8,10,...,Z,1,1
    public String getLetterPointsFreqFromGameSettingsFile() throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File file = new File(path, "gameSettings.txt");
        Scanner scanner = new Scanner(new File(file.toString()));
        scanner.nextLine(); // maxNumOfTurns
        String poolStr = "";
        for (int i = 0; i < 25; ++i) {
            poolStr += scanner.nextLine() + ",";
        }
        poolStr += scanner.nextLine();
        return poolStr;
    }

    // Update the letterStatistics file to increase the number of times the letter is played, traded or drawn.
    public void updateLetterStatisticsFile(char letter, String action) throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File letterStatisticsFile = new File(path, "letterStatistics.txt");
        char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        int[] totalLetterPlayed = new int[26];
        int[] totalLetterTraded = new int[26];
        int[] totalLetterDrawn = new int[26];

        // If the file exists, read existing data
        if (letterStatisticsFile.exists()) {
            totalLetterPlayed = readNumTimesFromLetterStatisticsFile("played");
            totalLetterTraded = readNumTimesFromLetterStatisticsFile("traded");
            totalLetterDrawn = readNumTimesFromLetterStatisticsFile("drawn");
        }
        // Overwrite the file with updated values
        try
        {
            FileWriter writer = new FileWriter(letterStatisticsFile, false);
            for (int i = 0; i < 26; ++i) {
                if (letters[i] == letter) {
                    switch (action) {
                        case "played":
                            totalLetterPlayed[i]++;
                            break;
                        case "traded":
                            totalLetterTraded[i]++;
                            break;
                        case "drawn":
                            totalLetterDrawn[i]++;
                            break;
                    }
                }
                writer.append(letters[i] + "," + totalLetterPlayed[i] + "," + totalLetterTraded[i] + "," + totalLetterDrawn[i]);
                writer.append("\n");
            }
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    // Read number of times each letter played/traded/drawn. Action has to be played, traded or drawn.
    public int[] readNumTimesFromLetterStatisticsFile(String action) throws FileNotFoundException {
        File path = new File(DATA_PATH, "");
        File letterStatisticsFile = new File(path, "letterStatistics.txt");
        Scanner scanner = new Scanner(new File(letterStatisticsFile.toString()));
        int[] numTimes = new int[26];
        for (int i = 0; i < 26; ++i) {
            String line = scanner.nextLine();
            String[] strings = line.split(",");
            switch(action) {
                case "played":
                    numTimes[i] = Integer.parseInt(strings[1]);
                    break;
                case "traded":
                    numTimes[i] = Integer.parseInt(strings[2]);
                    break;
                case "drawn":
                    numTimes[i] = Integer.parseInt(strings[3]);
                    break;
            }
        }
        scanner.close();
        return numTimes;
    }
}
