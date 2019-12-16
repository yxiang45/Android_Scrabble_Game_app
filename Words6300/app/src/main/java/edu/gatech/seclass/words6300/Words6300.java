package edu.gatech.seclass.words6300;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Words6300 extends AppCompatActivity implements View.OnClickListener {
    // Home page buttons
    private Button newGameButton;
    private Button continueGameButton;
    private Button gameSettingsButton;
    private Button wordStatsButton;
    private Button letterStatsButton;
    private Button scoreStatsButton;

    private Intent intent;
    private Bundle b;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // If gameSettings file does not exists, create the default file
        // File in the internal storage /data/user/0/<package>/files
        File path = new File(Words6300.this.getFilesDir(), "");
        File gameSettingsFile = new File(path, "gameSettings.txt");
        System.out.println("checking for initial game settings");
        if (!gameSettingsFile.exists()) {
            System.out.println("did not find game setting file");
            createDefaultGameSettingsFile(gameSettingsFile);
        }


//        newGameButton = findViewById(R.id.newGameButton);
//        newGameButton.setOnClickListener(this);

        continueGameButton = findViewById(R.id.continueGameButton);
        continueGameButton.setOnClickListener(this);

        gameSettingsButton = findViewById(R.id.gameSettingsButton);
        gameSettingsButton.setOnClickListener(this);

        wordStatsButton = findViewById(R.id.wordStatsButton);
        wordStatsButton.setOnClickListener(this);

        letterStatsButton = findViewById(R.id.letterStatsButton);
        letterStatsButton.setOnClickListener(this);

        scoreStatsButton = findViewById(R.id.scoreStatsButton);
        scoreStatsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
//            case R.id.newGameButton:
//
//                intent = new Intent(this, WordGameActivity.class);
//                b = new Bundle();
//                b.putBoolean("newGame", true); //Your id
//                intent.putExtras(b); //Put your id to your next Intent
//                startActivity(intent);
//                break;

            case R.id.continueGameButton:
                intent = new Intent(this, WordGameActivity.class);
                b = new Bundle();
                b.putBoolean("newGame", false); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
                break;

            case R.id.gameSettingsButton:
                intent = new Intent(this, GameSettingsActivity.class);
                startActivity(intent);
                break;

            case R.id.wordStatsButton:
                intent = new Intent(this, WordBankActivity.class);
                startActivity(intent);
                break;

            case R.id.letterStatsButton:
                intent = new Intent(this, LetterStatisticsActivity.class);
                startActivity(intent);
                break;

            case R.id.scoreStatsButton:
                intent = new Intent(this, ScoreStatisticsActivity.class);
                startActivity(intent);
                break;
        }
    }

    // Create gameSettings file with default values
    private void createDefaultGameSettingsFile(File gameSettingsFile) {
        try
        {
            FileWriter writer = new FileWriter(gameSettingsFile);
            // Default max number of turns is 10
            writer.append("10\n");
            // Default letter pool matching English Scrabble distribution
            writer.append("A,1,9\n");
            writer.append("B,3,2\n");
            writer.append("C,3,2\n");
            writer.append("D,2,4\n");
            writer.append("E,1,12\n");
            writer.append("F,4,2\n");
            writer.append("G,2,3\n");
            writer.append("H,4,2\n");
            writer.append("I,1,9\n");
            writer.append("J,8,1\n");
            writer.append("K,5,1\n");
            writer.append("L,1,4\n");
            writer.append("M,3,2\n");
            writer.append("N,1,6\n");
            writer.append("O,1,8\n");
            writer.append("P,3,2\n");
            writer.append("Q,10,1\n");
            writer.append("R,1,6\n");
            writer.append("S,1,4\n");
            writer.append("T,1,6\n");
            writer.append("U,1,4\n");
            writer.append("V,4,2\n");
            writer.append("W,4,2\n");
            writer.append("X,8,1\n");
            writer.append("Y,4,2\n");
            writer.append("Z,10,1");
            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

//    private void destroyAllTheFiles() {
//        File path = new File(Words6300.this.getFilesDir(), "");
//        File file = new File(path, "currentGameWords.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentMaxTurns.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "wordBank.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "scoreStatistics.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "gameSettings.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "letterStatistics.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentBoard.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentRack.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentGame.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentScore.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentMaxTurns.txt");
//        if (file.exists()) { file.delete(); }
//        file = new File(path, "currentGame.txt");
//        if (file.exists()) { file.delete(); }
//    }
}
