package edu.gatech.seclass.words6300;

import java.util.ArrayList;

public class Rack {

    private ArrayList<Tile> rack = new ArrayList<Tile>();

    public void addRackTiles(ArrayList<Tile> newTiles) {
        for (Tile tile : newTiles) {
            rack.add(tile);
        }
    }

    public void removeRackTiles(ArrayList<Tile> tilesToRemove) {
        for (Tile tile : tilesToRemove) {
            rack.remove(tile);
        }
    }

    public void swapRackTiles(ArrayList<Tile> tilesToAdd, ArrayList<Tile> tilesToRemove) {
        addRackTiles(tilesToAdd);
        removeRackTiles(tilesToRemove);
    }

    public ArrayList<Character> getRackLetters() {
        ArrayList<Character> rackLetters = new ArrayList<Character>();
        for (Tile tile : rack) {
            rackLetters.add(tile.getLetter());
        }

        return rackLetters;
    }

    public Tile getRackTile(int pos) {
        return rack.get(pos);
    }

    public int rackSize() {
        return rack.size();
    }

    public ArrayList<Tile> getRackTiles() {
        return rack;
    }
}
