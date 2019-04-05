package RE2.Model;

import RE2.Model.Rules.Rules;
import RE2.Model.Stone.Empty;
import RE2.Model.Stone.Stone;

public class Board {

    public static byte size;

    private Stone[][] tiles;
    private Rules rules;

    public Board(byte size) {

        this.size = size;
        this.tiles = new Stone[size][size];

    }

    public boolean isEmpty(byte row, byte column) {
        return (tiles[row][column].getClass() == Empty.class);
    }

    public Stone get(byte row, byte column) {
        return tiles[row][column];
    }

    public boolean set(Stone stone) {
        if (tiles[stone.getX()][stone.getY()].getClass() != Empty.class) {
            return false;
        } else {
            tiles[stone.getX()][stone.getY()] = stone;
            return true;
        }
    }

    public void remove(byte row, byte column) {
        tiles[row][column] = new Empty();
    }

    public byte getSize() {
        return size;
    }

    public void initialize(Rules rules) {
        this.rules = rules;
        for (byte row = 0; row < size; row++){
            for (byte column = 0; column < size; column++) {
                tiles[row][column] = new Empty();
            }
        }
    }

    public char gameOver(Board board) {
        return rules.gameOver(board);
    }

    public void printBoard() {
        for (byte row = 0; row < size; row++) {
            String line = "";
            for (byte column = 0; column < size; column++) {
                if (tiles[row][column] == null) {
                    line += "[ ]";
                } else {
                    line += "[" + tiles[row][column].getIdentifier() + "]";
                }
            }
            System.out.print(line + "\n");
        }
    }

}
