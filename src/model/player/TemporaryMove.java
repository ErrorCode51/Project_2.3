package model.player;

public class TemporaryMove {

    // Todo: Find a suitable place to store this class.

    public int row;
    public int column;
    public int score;

    public TemporaryMove(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public TemporaryMove(int score) {
        this.score = score;
    }

    public TemporaryMove(int row, int column, int score) {
        this.row = row;
        this.column = column;
        this.score = score;
    }

}
