package edu.gatech.seclass.words6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordBankActivity extends AppCompatActivity {

    private LinkedHashMap<String, Integer> wordBank = new LinkedHashMap<String, Integer>(); //changed to LinkedHashMap so we can display word in insertion order
    private ArrayList<String> words = new ArrayList<>();
    private ArrayList<String> wordFreq = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_bank);

        try {
            readWordBankFile();
            setWordsAndFreq();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> rows = new ArrayList<>();
        for (int i = 0; i < words.size(); ++i) {
            rows.add(String.format("%20s          x          %-50s",wordFreq.get(i), words.get(i)));
        }

        ArrayAdapter wordAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,rows);
        ListView wordListView = (ListView) findViewById(R.id.wordListView); //set wordListView id in layout
        wordListView.setAdapter(wordAdapter);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.backHomeFromWordBankButton)
            startActivity(new Intent(this, Words6300.class));
    }

    public void readWordBankFile() throws FileNotFoundException {
        File path = new File(WordBankActivity.this.getFilesDir(), "");
        File wordBankFile = new File(path, "wordBank.txt");
        Scanner scanner = new Scanner(new File(wordBankFile.toString()));
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine();
            if (wordBank.containsKey(word)) {
                wordBank.put(word, wordBank.get(word) + 1);
                words.remove(word);
                words.add(word);
            }
            else {
                wordBank.put(word, 1);
                words.add(word);
            }
        }
        Collections.reverse(words);
    }

    // Set values for words and their number of times played
    public void setWordsAndFreq() throws FileNotFoundException {
        for (String word : words) {
            wordFreq.add(Integer.toString(wordBank.get(word)));
        }
    }
}