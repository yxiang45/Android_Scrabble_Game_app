package edu.gatech.seclass.words6300;

import java.util.ArrayList;

public class Board {

    private ArrayList<Tile> board = new ArrayList<Tile>();

    public void addBoardTiles(ArrayList<Tile> newTiles) {
        for (Tile tile : newTiles) {
            addBoardTile(tile);
        }
    }

    public void addBoardTile(Tile newBoardTile) {
        board.add(newBoardTile);
    }

    public void removeBoardTiles(ArrayList<Tile> tilesToRemove) {
        for (Tile tile : tilesToRemove) {
            board.remove(tile);
        }
    }

    public ArrayList<Character> getBoardLetters() {
        ArrayList<Character> boardLetters = new ArrayList<Character>();
        for (Tile tile : board) {
            boardLetters.add(tile.getLetter());
        }

        return boardLetters;
    }

    public ArrayList<Tile> getBoardTiles() {
        return board;
    }

    public Tile getBoardTile(int pos) {
        return board.get(pos);
    }
}
