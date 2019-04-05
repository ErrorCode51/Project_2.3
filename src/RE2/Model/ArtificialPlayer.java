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
                if (board.isEmpty(row, column)) {
                    createTemporaryPlacement(board, player, counter, placements, row, column);
                }
            }
        }
        byte bestPlacement = 0;
        if (player == identifier) {
            int bestScore = -10;
            for (byte index = 0; index < placements.size(); index++)
                if (placements.get(index).score > bestScore) {
                    bestPlacement = index;
                    bestScore = placements.get(index).score;
                }
        } else {
            int bestScore = 10;
            for (byte index = 0; index < placements.size(); index++)
                if (placements.get(index).score < bestScore) {
                    bestPlacement = index;
                    bestScore = placements.get(index).score;
                }
        }
        return placements.get(bestPlacement);
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
