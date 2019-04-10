//package Model.Player;
//
//import Model.Board.Board;
//import Model.Board.OthelloBoard;
//import Model.Container.TemporaryPlacement;
//import Model.Game.Othello;
//import Model.Rules.OthelloRules;
//import Model.Rules.Rules;
//import Model.Stone.Empty;
//import Model.Stone.OthelloStone;
//import Model.Stone.Stone;
//import Model.Stone.TicTacToeStone;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.concurrent.TimeUnit;
//
//public class BrokenArtificialPlayer implements Player {
//
//    public char identifier;
//    public LinkedList<Stone> backup = new LinkedList<>();
//    public Board originalBoard = new OthelloBoard();
//    public ArrayList<TemporaryPlacement> placements = new ArrayList<>();
//
//    public BrokenArtificialPlayer(char identifier) {
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
//    void generateBackup(Board board) {
//        originalBoard = board;
//        for (byte row = 0, rows = board.getSize(); row < rows; row++) {
//            for (byte column = 0, columns = board.getSize(); column < columns; column++ ) {
//                backup.add(board.get(row, column));
//            }
//        }
//    }
//
//    Board generateTemporaryBoard(Rules rules) {
//        Board temporaryBoard = new OthelloBoard();
//        temporaryBoard.initialize(rules);
//        byte index = 0;
//        for (byte column = 0; column < 8; column++) {
//            for (byte row = 0; row < 8; row++) {
//                if (backup.get(index).getClass() != Empty.class) {
//                    temporaryBoard.set(new OthelloStone(backup.get(index).getX(), backup.get(index).getY(), backup.get(index).getIdentifier()));
//                }
//                index++;
//            }
//        }
//        return temporaryBoard;
//    }
//
//    @Override
//    public byte[] placeStone(Board board, Rules rules) {
//        byte counter = 0;
//        generateBackup(board);
//        Board temporaryBoard = generateTemporaryBoard(rules);
//        placements = new ArrayList<>();
//        TemporaryPlacement temporaryPlacement = this.findBestPlacement(temporaryBoard, rules, getIdentifier(), counter);
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
//        } else if (((OthelloRules) rules).getScore(board, getIdentifier()) > ((OthelloRules) rules).getScore(board, getOpposingIdentifier())) {
//            return new TemporaryPlacement(5);
//        }
////        ArrayList<TemporaryPlacement> placements = new ArrayList<>();
//        counter += 1;
//        for (byte row = 0; row < board.getSize(); row++) {
//            for (byte column = 0; column < board.getSize(); column++) {
//                if (rules.getClass() == OthelloRules.class) {
//                    ArrayList<Stone> test = ((OthelloRules) rules).findAllLegal(board, this.identifier);
//                    // System.out.println(test);
//                    // System.out.println("Othello reached");
//                    for (Stone stone : test) {
//                        if (stone.getX() == row && stone.getY() == column) {
//                            // System.out.println("Legal at: " + row + ", " + column);
//                            createTemporaryPlacement(board, rules, identifier, counter, placements, row, column);
//                        }
//                    }
//
//                } else if (board.isEmpty(row, column)) {
//                    // System.out.println("TicTacToe reached");
//                    createTemporaryPlacement(board, rules, identifier, counter, placements, row, column);
//
//                }
//            }
//        }
//        byte bestPlacement = 0;
//        if (identifier == getIdentifier()) {
//            int bestScore = -1;
//            for (byte index = 0; index < placements.size(); index++)
//                if (placements.get(index).score > bestScore) {
//                    bestPlacement = index;
//                    bestScore = placements.get(index).score;
//                }
//        } else {
//            int bestScore = 1;
//            for (byte index = 0; index < placements.size(); index++)
//                if (placements.get(index).score < bestScore) {
//                    bestPlacement = index;
//                    bestScore = placements.get(index).score;
//                }
//        }
//        // System.out.println(bestPlacement);
//        return placements.get(bestPlacement);
//    }
//
//    // Fills the ArrayList<TemporaryPlacement> with possible placements. Only called by AI.
////    private void createTemporaryPlacement(Board board, Rules rules, char identifier, byte counter,
////                                          ArrayList<TemporaryPlacement> placements, byte row, byte column) {
////        TemporaryPlacement placement = new TemporaryPlacement(row, column);
////        System.out.println(placement);
////        ((OthelloRules) rules).testForLegal(board, createStone(row, column, identifier), true);
////        board.set(createStone(row, column, identifier));
////        if (identifier == getIdentifier()) {
////            placement.score = findBestPlacement(board, rules, getOpposingIdentifier(), counter).score - counter;
////        } else {
////            placement.score = counter + findBestPlacement(board, rules, getIdentifier(), counter).score;
////        }
////        board.remove(row, column);
////        placements.add(placement);
////    }
//
//    private void createTemporaryPlacement(Board board, Rules rules, char identifier, byte counter,
//                                          ArrayList<TemporaryPlacement> placements, byte row, byte column) {
//        TemporaryPlacement placement = new TemporaryPlacement(row, column);
//        // System.out.println(placements.size());
//        ((OthelloRules) rules).testForLegal(board, createStone(row, column, identifier), true);
//        board.set(createStone(row, column, identifier));
//        if (identifier == getIdentifier()) {
//            placement.score = findBestPlacement(board, rules, getOpposingIdentifier(), counter).score - counter;
//        } else {
//            placement.score = counter + findBestPlacement(board, rules, getIdentifier(), counter).score;
//        }
//        // System.out.println(placement);
//        placements.add(placement);
//        System.out.println(placements);
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
