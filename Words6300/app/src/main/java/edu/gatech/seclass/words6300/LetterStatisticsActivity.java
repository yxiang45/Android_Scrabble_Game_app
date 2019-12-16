package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LetterStatisticsActivity extends AppCompatActivity {

    private ArrayList<String> letters = new ArrayList<>();
    private ArrayList<String> merged = new ArrayList<>();
    private ArrayList<String> sorted = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_statistics);

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        letters = new ArrayList<String>(Arrays.asList(alphabet.split("")));

        int[] played = new int[26];
        try {
            played = readNumTimesFromLetterStatisticsFile("played");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] traded = new int[26];
        try {
            traded = readNumTimesFromLetterStatisticsFile("traded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int[] drawn = new int[26];
        try {
            drawn = readNumTimesFromLetterStatisticsFile("drawn");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for(Integer i=0; i<26; i++){
            Integer col1 = played[i];
            Integer col2 = drawn[i];
            Integer col3 = traded[i];
            Float usedPercentage = ((float)col1/col2)*100 ;
            DecimalFormat df = new DecimalFormat("#.##");
            usedPercentage =Float.valueOf(df.format(usedPercentage));

            //add played Time before letter for sorting purpose
            String temp = String.format("%20s %4s %21s %25s %25s",
                    col1.toString(),letters.get(i),col1.toString(), col3.toString(),usedPercentage.toString());

            merged.add(temp);
        }

        Collections.sort(merged);

        //remove played Times in first column that was added for sorting.
        for(String str:merged){
            String temp = str.substring(20);
            sorted.add(temp);
        }

        ArrayAdapter percentageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sorted);
        ListView percentageListView = (ListView) findViewById(R.id.percentageListView);
        percentageListView.setAdapter(percentageAdapter);
    }

    public void handleClick(View view) {
        if (view.getId() == R.id.backHomeFromLetterStatsButton)
            startActivity(new Intent(this, Words6300.class));
    }

    // Read number of times each letter played/traded/drawn. Action has to be played, traded or drawn.
    public int[] readNumTimesFromLetterStatisticsFile(String action) throws FileNotFoundException {
        File path = new File(LetterStatisticsActivity.this.getFilesDir(), "");
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
