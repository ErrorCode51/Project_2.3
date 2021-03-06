package Model.Board;

import Model.Rules.OthelloRules;
import Model.Rules.Rules;
import Model.Stone.Empty;
import Model.Stone.Stone;

public abstract class Board {

    // Since the board is always square a single value suffices
    public static byte size;
    // A board consists of a two dimensional array containing stones
    protected final Stone[][] tiles;
    protected Rules rules;

    public Board(byte size) {

        Board.size = size;
        this.tiles = new Stone[size][size];

    }

    // Returns true when given tile is empty
    public boolean isEmpty(byte row, byte column) {
        return (get(row, column).getClass() == Empty.class);
    }

    // Returns the stone occupying given tile
    public Stone get(byte row, byte column) {
        return tiles[row][column];
    }

    // Is used to place a stone on the board. Returns true stone is successfully placed on a empty tile
    public boolean set(Stone stone) {
        if (get(stone.getX(), stone.getY()).getClass() != Empty.class) {
            return false;
        } else {
            tiles[stone.getX()][stone.getY()] = stone;
            return true;
        }
    }

    public Stone[][] getTiles() {
        return this.tiles.clone();
    }

    // Removes a stone from the board. Only called by AI
    public void remove(byte row, byte column) {
        tiles[row][column] = new Empty();
    }

    // Returns the size of the board
    public byte getSize() {
        return size;
    }

    // Used to bind the game rules to the board
    public void initialize(Rules rules) {
        this.rules = rules;
        for (byte row = 0; row < size; row++) {
            for (byte column = 0; column < size; column++) {
                tiles[row][column] = new Empty();
            }
        }
    }

    // Called the gameOver() method from the rules bound to the board
    public char gameOver(Board board) {
        return rules.gameOver(board);
    }

    @Override
    public String toString() {
        String string = "";
        for (byte row = 0; row < size; row++) {
            String line = "";
            for (byte column = 0; column < size; column++) {
                if (isEmpty(row, column)) {
                    line += "[ ]";
                } else {
                    line += "[" + get(row, column).getIdentifier() + "]";
                }
            }
            string += line + "\n";
        }
        return string;
    }

}
