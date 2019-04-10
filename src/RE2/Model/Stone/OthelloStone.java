package RE2.Model.Stone;

import RE2.Model.Exception.InvalidIdentifierException;

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
    public boolean equals(Object object) {
        return (object instanceof OthelloStone &&
                this.x == ((OthelloStone) object).getX() &&
                this.x == ((OthelloStone) object).getY() &&
                this.identifier == ((OthelloStone) object).getIdentifier());
    }

    public void turnOver() {
        if (this.identifier == 'B') {
            this.identifier = 'W';
        } else {
            this.identifier = 'B';
        }
    }

}
