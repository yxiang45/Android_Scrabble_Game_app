package edu.gatech.seclass.words6300;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LetterPool {

    private ArrayList<Tile> pool = new ArrayList<Tile>();
    private Random rand = new Random();
    private ArrayList<Integer> freqList = new ArrayList<>();

    public ArrayList<Tile> getMultipleRandomTiles(int numTiles) {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < numTiles; i++) {
            tiles.add(getRandomTile());
        }
        return tiles;
    }

    public int poolSize() {
        return pool.size();
    }

    public boolean isEmpty() {
        return pool.isEmpty();
    }

    public void swapPoolTiles(ArrayList<Tile> tilesToAdd, ArrayList<Tile> tilesToRemove) {
        addToPool(tilesToAdd);
        removePoolTiles(tilesToRemove);
    }

    public void addToPool(ArrayList<Tile> tilesToAdd) {
        for (Tile tile : tilesToAdd) {
            pool.add(tile);
        }
    }

    public void removePoolTiles(ArrayList<Tile> tilesToRemove) {
        for (Tile tile : tilesToRemove) {
            pool.remove(tile);
        }
    }

    private Tile getRandomTile() {
        return pool.remove(rand.nextInt(poolSize()));
    }

    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (Tile tile : pool) {
            tiles.add(tile);
        }
        return tiles;
    }

    public void addFreqList(ArrayList<Integer> freq) {
        for (Integer f : freq) {
            freqList.add(f);
        }
    }

    public ArrayList<Integer> getFreqList() {
        ArrayList<Integer> freq = new ArrayList<>();
        for (Integer f: freqList) {
            freq.add(f);
        }
        return freq;
    }
}