//package RE2.Model.Player;
//
//import RE2.Model.Board.Board;
//import RE2.Model.Container.TemporaryPlacement;
//import RE2.Model.Rules.Rules;
//import RE2.Model.Stone.OthelloStone;
//import RE2.Model.Stone.Stone;
//import RE2.Model.Stone.TicTacToeStone;
//
//import java.util.ArrayList;
//
//public class ArtificialBackupPlayer implements Player {
//
//    public char identifier;
//
//    public ArtificialBackupPlayer(char identifier) {
//
//        this.identifier = identifier;
//
//    }
//
//    @Override
//    public String toString() {
//        return "Genichiro";
//    }
//
//    @Override
//    public char getIdentifier() {
//        return identifier;
//    }
//
//    @Override
//    public byte[] placeStone(Board board, Rules rules) {
//        byte counter = 0;
//        TemporaryPlacement temporaryPlacement = this.findBestPlacement(board, rules, getIdentifier(), counter);
//        byte[] placement = {(byte) temporaryPlacement.row, (byte) temporaryPlacement.column};
//        return placement;
//    }
//
//    TemporaryPlacement findBestPlacement(Board board, Rules rules, char identifier, byte counter) {
//        char winner = rules.gameOver(board);
//        if (winner == getIdentifier()) {
//            return new TemporaryPlacement(10);
//        } else if (winner == 'D') {
//            return new TemporaryPlacement(0);
//        } else if (winner == getOpposingIdentifier()) {
//            return new TemporaryPlacement(-10);
//        }
//        ArrayList<TemporaryPlacement> placements = new ArrayList<>();
//        counter += 1;
//        for (byte row = 0; row < board.getSize(); row++) {
//            for (byte column = 0; column < board.getSize(); column++) {
//                if (board.isEmpty(row, column)) {
//                    createTemporaryPlacement(board, rules, identifier, counter, placements, row, column);
//                }
//            }
//        }
//        byte bestPlacement = 0;
//        if (identifier == getIdentifier()) {
//            int bestScore = -10;
//            for (byte index = 0; index < placements.size(); index++)
//                if (placements.get(index).score > bestScore) {
//                    bestPlacement = index;
//                    bestScore = placements.get(index).score;
//                }
//        } else {
//            int bestScore = 10;
//            for (byte index = 0; index < placements.size(); index++)
//                if (placements.get(index).score < bestScore) {
//                    bestPlacement = index;
//                    bestScore = placements.get(index).score;
//                }
//        }
//        return placements.get(bestPlacement);
//    }
//
//    // Fills the ArrayList<TemporaryPlacement> with possible placements. Only called by AI.
//    private void createTemporaryPlacement(Board board, Rules rules, char identifier, byte counter,
//                                          ArrayList<TemporaryPlacement> placements, byte row, byte column) {
//        TemporaryPlacement placement = new TemporaryPlacement(row, column);
//        board.set(createStone(row, column, identifier));
//        if (identifier == getIdentifier()) {
//            placement.score = findBestPlacement(board, rules, getOpposingIdentifier(), counter).score - counter;
//        } else {
//            placement.score = counter + findBestPlacement(board, rules, getIdentifier(), counter).score;
//        }
//        board.remove(row, column);
//        placements.add(placement);
//    }
//
//    // Returns the opposing identifier. Only used by AI.
//    private char getOpposingIdentifier() {
//        // Unsure if this method is needed in both TicTacToe and Othello, made if future-proof non the less
//        char opponent = ' ';
//        switch (identifier) {
//            case 'X':
//                opponent = 'O';
//                break;
//            case 'O':
//                opponent = 'X';
//                break;
//            case 'B':
//                opponent = 'W';
//                break;
//            case 'W':
//                opponent = 'B';
//                break;
//        }
//        return opponent;
//    }
//
//    private Stone createStone(byte row, byte column, char identifier) {
//        if (this.identifier == 'X' || this.identifier == 'O') {
//            return new TicTacToeStone(row, column, identifier);
//        } else {
//            return new OthelloStone(row, column, identifier);
//        }
//    }
//
//}
