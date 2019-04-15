package Model.Stone;

import Model.Exception.InvalidIdentifierException;
import Model.Game.Othello;

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
            throw new InvalidIdentifierException("Invalid identifier provided. Only 'B' or 'W' are allowed");
        }

    }

    public OthelloStone(OthelloStone stone) {

        super.x = stone.getX();
        super.y = stone.getY();
        this.identifier = stone.getIdentifier();

    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "] = " + identifier;
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


    public boolean equals(char identifier) {
        return (this.identifier == identifier);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OthelloStone) {
            OthelloStone stone = (OthelloStone) obj;
            if (this.x == stone.x && this.y == stone.y) {
                return true;
            }
        }
        return false;
    }

    public void turnOver() {
        if (this.identifier == 'B') {
            this.identifier = 'W';
        } else {
            this.identifier = 'B';
        }
    }

}
