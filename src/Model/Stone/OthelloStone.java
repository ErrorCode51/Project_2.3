package Model.Stone;

import Model.Exception.InvalidIdentifierException;

public class OthelloStone extends Stone {

    private char identifier;

    public OthelloStone(byte x, byte y) {
        super(x, y);
    }

    public OthelloStone(byte x, byte y, char identifier) {

        super(x, y);
        if (identifier == 'B' || identifier == 'W') {
            this.identifier = identifier;
        } else {
            throw new InvalidIdentifierException("Invalid identifier provided. Only 'X' or 'O' are allowed");
        }

    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public char getIdentifier() {
        return identifier;
    }

    public OthelloStone getStone() {
        return this;
    }

    public boolean equals(char colour) {
        return (this.identifier == colour);
    }

    public void turnOver() {
        if (this.identifier == 'B') {
            this.identifier = 'W';
        } else {
            this.identifier = 'B';
        }
    }

}
