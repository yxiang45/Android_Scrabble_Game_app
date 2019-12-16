package edu.gatech.seclass.words6300;

import java.util.Objects;
import java.util.Random;

/*
 * This is Tile class. This class store the letter and its points.
 */
public class Tile {

    private char letter;
    private int points;
    private int randValue;

    public Tile(char letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }
}
