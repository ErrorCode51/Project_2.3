package RE2.Model;

import java.util.ArrayList;

public class ArtificialPlayer extends Player {

    public static final byte ID = 0;
    public char identifier;

    public String name = "Genichiro";

    public ArtificialPlayer(char identifier) {

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
    public String toString() {
        return name;
    }

    private char getOpposingIdentifier() {
        return (identifier == 'X') ? 'O' : 'X';
    }

    public TemporaryPlacement placeStone(Board board) {
        byte counter = 0;
        TemporaryPlacement placement = this.getBestPlacement(board, identifier, counter);
        board.set(new TicTacToeStone((byte) placement.row, (byte) placement.column, identifier));
        System.out.println("" + identifier + ": " + placement);
        return placement;
    }

    TemporaryPlacement getBestPlacement(Board board, char player, byte counter) {
        char winner = board.gameOver(board);
        if (winner == identifier) {
            return new TemporaryPlacement(10);
        } else if (winner == 'D') {
            return new TemporaryPlacement(0);
        } else if (winner == getOpposingIdentifier()) {
            return new TemporaryPlacement(-10);
        }
        ArrayList<TemporaryPlacement> placements = new ArrayList<>();
        counter += 1;
        for (byte row = 0; row < board.getSize(); row++) {
            for (byte column = 0; column < board.getSize(); column++) {
                if (board.get(row, column) == null) {
                    createTemporaryPlacement(board, player, counter, placements, row, column);
                }
                if (board.get(row, column).getClass() == Empty.class) {
                    createTemporaryPlacement(board, player, counter, placements, row, column);
                }
            }
        }
        TemporaryPlacement bestPlacement = null;
        if (player == identifier) {
            int bestScore = -10;
            for (TemporaryPlacement placement : placements) {
                if (placement.score > bestScore) {
                    bestPlacement = placement;
                    bestScore = placement.score;
                }
            }
        } else {
            int bestScore = 10;
            for (TemporaryPlacement placement : placements) {
                if (placement.score < bestScore) {
                    bestPlacement = placement;
                    bestScore = placement.score;
                }
            }
        }
        return bestPlacement;
    }

    private void createTemporaryPlacement(Board board, char player, byte counter, ArrayList<TemporaryPlacement> placements, byte row, byte column) {
        TemporaryPlacement placement = new TemporaryPlacement(row, column);
        board.set(new TicTacToeStone(row, column, player));
        if (player == identifier) {
            placement.score = getBestPlacement(board, getOpposingIdentifier(), counter).score - counter;
        } else {
            placement.score = counter + getBestPlacement(board, identifier, counter).score;
        }
        board.remove(row, column);
        placements.add(placement);
    }

}
