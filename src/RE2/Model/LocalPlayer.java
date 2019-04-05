package RE2.Model;

public class LocalPlayer extends Player {

    public static final byte ID = 1;
    public char identifier;

    public String name = "Sekiro";

    public LocalPlayer(char identifier) {

        super(ID);
        if (identifier == 'X' || identifier == 'O') {
            this.identifier = identifier;
        } else {
            throw new InvalidIdentifierException("Invalid identifier provided. Only 'X' or 'O' are allowed");
        }

    }

    @Override
    byte getID() {
        return ID;
    }

    @Override
    TemporaryPlacement placeStone(Board board) {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean placeStone(Board board, byte row, byte column, char identifier) {
        if (board.get(row, column) != null) {
            return false;
        } else {
            TicTacToeStone stone = new TicTacToeStone(row, column, identifier);
            board.set(stone);
            return true;
        }
    }

}