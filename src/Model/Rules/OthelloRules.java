package Model.Rules;

import Model.Board.Board;
import Model.Stone.Empty;
import Model.Stone.OthelloStone;
import Model.Stone.Stone;

import java.util.ArrayList;

public class OthelloRules implements Rules {

    private static final char DRAW = 'D';
    private static final char ONGOING = 'N';

    @Override
    public char gameOver(Board board) {
        boolean gameOver = true;
        for (byte row = 0; row < board.getSize(); row++) {
            for (byte column = 0; column < board.getSize(); column++) {
                OthelloStone temporaryStone = new OthelloStone(row, column, 'B');
                if (testForLegal(board, temporaryStone, false)) {
                    gameOver = false;
                    // If a legal turn has been found the game cannot be over
                    break;
                } else {
                    temporaryStone.turnOver();
                    if (testForLegal(board, temporaryStone, false)) {
                        gameOver = false;
                        // Again if a legal turn has been found the game cannot be over
                        break;
                    }
                }
            }
        }
        if (!gameOver) {
            // Return ONGOING if it is still possible to place a legal stone
            return (byte) ONGOING;
        } else {
            // Todo: There's probably a better solution for this but for now it works.
            byte[] scores = {getScore(board, 'B'), getScore(board, 'W')};
            // Footnote:
            // Due to the fact that the board is crawled twice this seems ineffective.
            if (scores[0] == scores[1]) {
                // Return DRAW if both scores are equal
                return DRAW;
            }
            if (scores[0] > scores[1]) {
                // Return identifier of winning player
                return 'B';
            } else {
                return 'W';
            }
        }
    }

    public byte getScore(Board board, char identifier) {
        byte score = 0;
        for (byte row = 0; row < board.getSize(); row++) {
            for (byte column = 0; column < board.getSize(); column++) {
                if (!board.isEmpty(row, column)) {
                    if (board.get(row, column).getIdentifier() == identifier) {
                        score += 1;
                    }
                }
            }
        }
        return score;
    }

    public boolean testForLegal(Board board, Stone stone, boolean turnOver) {
        // Todo: remove local variable
        // System.out.println("testForLegal received: " + stone);
        boolean legal = false;
        for (byte row = -1; row <= 1; row++) {
            for (byte column = -1; column <= 1; column++) {
                if (testForLegal(board, stone, row, column, 0, turnOver)) {
                    legal = true;
                }
            }
        }
        return legal;
    }

    public boolean testForLegal(Board board, Stone stone, byte row, byte column, int counter, boolean turnOver) {
        // System.out.print(row + ", " + column + ": ");
        byte x = stone.getX();
        byte y = stone.getY();
        // System.out.println("Original: " + x + ", " + y);
        char identifier = stone.getIdentifier();
        // System.out.println("[" + x + ", " + y +"]: " + identifier + "Testing: [" + row + ", " + column +"]");
        // Check if location is empty
        if (!board.isEmpty(x, y)) {
            // System.out.println("Not empty");
            return false;
        }
        // Temporary fix
        // if (row == 0 && column == 0 && board.get(x, y).getClass() != Empty.class) {
        if (row == 0 && column == 0) {
            // System.out.println("Current position\n");
            return false;
        }
        // Check if location next to stone is out of bounds
        if (x + row < 0 || x + row > 7 || y + column < 0 || y + column > 7) {
            // System.out.print("Out of bounds\n");
            return false;
        }
        Stone stoned = board.get((byte) (x + row), (byte) (y + column));
        // Check if location next to stone is empty
        if (board.get((byte) (x + row), (byte) (y + column)).getClass() == Empty.class) {
            // System.out.print("Empty\n");
            return false;
        }
        if (board.get((byte) (x + row), (byte) (y + column)) == stone) {
            // System.out.print("Current stone\n");
            return false;
        }
        if (board.get((byte) (x + row), (byte) (y + column)).equals(identifier)) {
            // System.out.print("Own stone\n");
            return counter > 0;

        } else {
            // System.out.print("Opposing stone found. ");
            if (row > 0) {
                row++;
            }
            if (row < 0) {
                row--;
            }
            if (column > 0) {
                column++;
            }
            if (column < 0) {
                column--;
            }
            if (testForLegal(board, stone, row, column, counter + 1, turnOver)) {
                if (turnOver) {
                    stoned.turnOver();
                }
                return true;
            } else {
                // System.out.print("End of method\n");
                return false;
            }
        }
    }

    // Method might be deprecated
    public ArrayList<Stone> findAllLegal(Board board, char identifier) {
        ArrayList<Stone> legalPlacements = new ArrayList<>();
        for (byte row = 0; row < board.getSize(); row++) {
            for (byte column = 0; column < board.getSize(); column++) {
                Stone stoned = new OthelloStone(row, column, identifier);
                if (testForLegal(board, stoned, false)) {
                    legalPlacements.add(stoned);
                }
            }
        }
        return legalPlacements;
    }

}
