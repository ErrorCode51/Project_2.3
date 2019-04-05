package RE2.Model;

public class Board implements Cloneable {

    public static byte size;

    private Stone[][] tiles;
    private Rules rules;

    public Board(byte size) {

        this.size = size;
        this.tiles = new Stone[size][size];

    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Stone get(byte row, byte column) {
        return tiles[row][column];
    }

    public void set(Stone stone) {
        tiles[stone.x][stone.y] = stone;

    }

    public void remove(byte row, byte column) {
        tiles[row][column] = new Empty();
        //tiles[row][column] = null;
    }

    public byte getSize() {
        return size;
    }

    public void initialize(Rules rules) {
        this.rules = rules;
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
