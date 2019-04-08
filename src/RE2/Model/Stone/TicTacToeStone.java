package RE2.Model.Stone;

import RE2.Model.Exception.InvalidIdentifierException;

public class TicTacToeStone extends Stone {

    private char identifier;

    public TicTacToeStone(byte x, byte y, char identifier) {

        super(x, y);
        if (identifier == 'X' || identifier == 'O') {
            this.identifier = identifier;
        } else {
            throw new InvalidIdentifierException("Invalid identifier provided. Only 'X' or 'O' are allowed");
        }

    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "] = " + identifier;
    }

    public char getIdentifier() {
        return identifier;
    }

    @Override
    public void turnOver() {

    }
}
