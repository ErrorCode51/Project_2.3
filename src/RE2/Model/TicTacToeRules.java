package RE2.Model;

public class TicTacToeRules implements Rules {

    public static char DRAW = 'D';
    public static char ONGOING = 'N';

    @Override
    public char gameOver(Board board) {
        // Horizontal
        for (byte row = 0; row < board.size; ++row) {
            if (!board.isEmpty(row, (byte) 0) && !board.isEmpty(row, (byte) 1) &&
                    board.get(row, (byte) 0).getIdentifier() == board.get(row, (byte) 1).getIdentifier() &&
                    board.get(row, (byte) 1).getIdentifier() == board.get(row, (byte) 2).getIdentifier()) {
                if (board.get(row, (byte) 0).getIdentifier() == 'X') {
                    return 'X';
                } else {
                    return 'O';
                }
            }
        }
        // Vertical
        for (byte column = 0; column < board.size; ++column) {
            if (!board.isEmpty((byte) 0, column) && !board.isEmpty((byte) 1, column) &&
                    board.get((byte) 0, column).getIdentifier() == board.get((byte) 1, column).getIdentifier() &&
                    board.get((byte) 1, column).getIdentifier() == board.get((byte) 2, column).getIdentifier()) {
                if (board.get((byte) 0, column).getIdentifier() == 'X') {
                    return 'X';
                } else {
                    return 'O';
                }
            }
        }
        // Diagonal left to right
        if (!board.isEmpty((byte) 0, (byte) 0) && !board.isEmpty((byte) 1, (byte) 1) &&
                board.get((byte) 0, (byte) 0).getIdentifier() == board.get((byte) 1, (byte) 1).getIdentifier() &&
                board.get((byte) 1, (byte) 1).getIdentifier() == board.get((byte) 2, (byte) 2).getIdentifier()) {
            if (board.get((byte) 0, (byte) 0).getIdentifier() == 'X') {
                return 'X';
            } else {
                return 'O';
            }
        }
        // Diagonal right to left
        if (!board.isEmpty((byte) 0, (byte) 2) && !board.isEmpty((byte) 1, (byte) 1) &&
                board.get((byte) 0, (byte) 2).getIdentifier() == board.get((byte) 1, (byte) 1).getIdentifier() &&
                board.get((byte) 1, (byte) 1).getIdentifier() == board.get((byte) 2, (byte) 0).getIdentifier()) {
            if (board.get((byte) 0, (byte) 2).getIdentifier() == 'X') {
                return 'X';
            } else {
                return 'O';
            }
        }
        boolean draw = true;
        // Draw
        for (byte row = 0; row < board.size; ++row) {
            for (byte column = 0; column < board.size; column++) {
                if (board.get(row, column) == null) {
                    draw = false;
                    break;
                }
                if (board.get(row, column).getClass() == Empty.class) {
                    draw = false;
                    break;
                }
            }
            if (draw == false) {
                break;
            }
        }
        return (draw) ? DRAW : ONGOING;
    }

}

