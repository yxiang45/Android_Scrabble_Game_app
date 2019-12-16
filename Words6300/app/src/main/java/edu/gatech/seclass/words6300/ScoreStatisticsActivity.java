package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreStatisticsActivity extends AppCompatActivity {

    private ArrayList<String> games = new ArrayList<>();
    private ArrayList<String> gameScores = new ArrayList<>();
    private ArrayList<String> numOfTurns = new ArrayList<>();
    private ArrayList<String> avgScorePerTurn = new ArrayList<>();
    private String[][] gameSettings;
    private ArrayList<String> maxTurns = new ArrayList<>();
    private ArrayList<LetterPool> pools = new ArrayList<>();
    private ArrayList<String> freq = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_statistics);

        // Read info of games from the scoreStatistics file and set statistics values
        try {
            games = readScoreStatisticsFile();
            // Sort the games in descending order by final game score
            Collections.sort(games, new Comparator<String>() {
                public int compare(String a, String b) {
                    return Integer.parseInt(b.split(",")[0]) - Integer.parseInt(a.split(",")[0]);
                }
            });

            setStatisticsValues();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> rows = new ArrayList<>();
        for (int i = 0; i < gameScores.size(); ++i) {
            rows.add(String.format("%-30s %-30s %-30s",gameScores.get(i), numOfTurns.get(i),avgScorePerTurn.get(i)));
        }

        ArrayAdapter scoreAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rows);
        ListView scoreListView = (ListView) findViewById(R.id.scoreListView ); //set  id in layout
        scoreListView.setAdapter(scoreAdapter);

        // To display game setting details when user click game scores
        scoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                AlertDialog.Builder dialog  = new AlertDialog.Builder(ScoreStatisticsActivity.this);
                dialog.setTitle("Game Settings");
                String settings = "";
                settings += "Max number of turns: " + maxTurns.get(position) + ".\n";
                settings += "Letter Distribution & Points:\n";
                String lettersAndPoints = "";
                ArrayList<Tile> tiles = pools.get(position).getTiles();
                ArrayList<Integer> freqL = pools.get(position).getFreqList();
                for (int i = 0; i < tiles.size(); ++i) {
                    settings += freqL.get(i) + " x " + tiles.get(i).getLetter() + ", " + tiles.get(i).getPoints() + " points\n";
                    // settings += tiles.get(i).getLetter() + ", " + freqL.get(i) + ", " + tiles.get(i).getPoints() + " points each\n";
                }
                dialog.setMessage(settings);
                dialog.setPositiveButton("Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {}
                        });
                dialog.setCancelable(true);
                dialog.create().show();
            }
        });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.backHomeFromScoreStatsButton)
            startActivity(new Intent(this, Words6300.class));
    }

    /* Each line in the scoreStatistics file stores info of one game including final score, number of turns, settings (max number of turns, letter pool)
     * This method return a list of games.
     * Note:
     * Each game can be split by "," to get the detailed info: String[] info = game.split(",");
     * Ex: game = "100,15,50,A,2,3,B,2,3,...,Z,1,1"
     * Final score: info[0] = 100; number of turns: info[1] = 15
     * Settings:
     * Max number of turns: info[2] = 10
     * Letter Pool:
     * info[3] to info[5] = A,2,3
     * info[6] to info[8] = B,2,3
     * ....
     * info[78] to info[80] = Z,1,1
     */
    public ArrayList<String> readScoreStatisticsFile() throws FileNotFoundException {
        File path = new File(ScoreStatisticsActivity.this.getFilesDir(), "");
        File scoreStatisticsFile = new File(path, "scoreStatistics.txt");
        Scanner scanner = new Scanner(new File(scoreStatisticsFile.toString()));
        ArrayList<String> games = new ArrayList<>();
        while (scanner.hasNextLine()) {
            games.add(scanner.nextLine());
        }
        return games;
    }

    private void setStatisticsValues() {
        String[] info;
        for (String game: games) {
            info = game.split(",");
            gameScores.add(info[0]);
            numOfTurns.add(info[1]);
            Double avgScore = Double.parseDouble(info[0])/Double.parseDouble(info[1]);
            DecimalFormat df = new DecimalFormat("#.##");
            avgScorePerTurn.add(Double.valueOf(df.format(avgScore)).toString());
            maxTurns.add(info[2]);
            int start = 3;
            ArrayList<Tile> tiles = new ArrayList<>();
            ArrayList<Integer> freqList = new ArrayList<>();
            LetterPool pool = new LetterPool();
            for (int i = start; i < 81; i += 3) {
                tiles.add(new Tile(info[i].charAt(0), Integer.parseInt(info[i+1])));
                freqList.add(Integer.parseInt(info[i+2]));
            }
            pool.addToPool(tiles);
            pool.addFreqList(freqList);
            pools.add(pool);
        }
    }
}