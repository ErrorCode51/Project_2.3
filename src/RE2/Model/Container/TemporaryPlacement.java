package RE2.Model.Container;

// A container class used by AI to evaluate possible placements on the board
public class TemporaryPlacement {

    public int row;
    public int column;
    public int score;

    public TemporaryPlacement(int score) {

        this.score = score;

    }

    public TemporaryPlacement(int row, int column) {

        this.row = row;
        this.column = column;

    }

    // This constructor might be deprecated
    public TemporaryPlacement(int row, int column, int score) {

        this.row = row;
        this.column = column;
        this.score = score;

    }

    @Override
    public String toString() {
        return "[" + row + ", " + column + "]: " + score;
    }
}