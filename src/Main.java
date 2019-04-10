import View.OthelloView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        // Homescreen
        //launch(Homescreen.class, args);

        // TicTacToe
        //launch(TicTacToeView.class, args);

        // Othello
        launch(OthelloView.class, args);


//        OthelloBoard board = new OthelloBoard();
//        OthelloRules rules = new OthelloRules();
//        board.initialize(rules);
//        board.getTiles()[3][3] = new OthelloStone((byte) 3, (byte) 3, 'W');
//        board.getTiles()[3][4] = new OthelloStone((byte) 3, (byte) 4, 'B');
//        board.getTiles()[4][3] = new OthelloStone((byte) 4, (byte) 3, 'B');
//        board.getTiles()[4][4] = new OthelloStone((byte) 4, (byte) 4, 'W');
//
////        board.getTiles()[6][0] = new OthelloStone((byte) 6, (byte) 0, 'W');
////        board.getTiles()[6][1] = new OthelloStone((byte) 6, (byte) 1, 'W');
////        board.getTiles()[7][0] = new OthelloStone((byte) 7, (byte) 0, 'W');
////        board.getTiles()[7][1] = new OthelloStone((byte) 7, (byte) 1, 'W');
////        board.getTiles()[5][2] = new OthelloStone((byte) 5, (byte) 2, 'B');
//
//        Player test1 = new LocalPlayer('B');
//        Player test2 = new LocalPlayer('W');
//
//        OthelloStone stone1 = new OthelloStone((byte) 3, (byte) 5, 'W');
//        OthelloStone stone2 = new OthelloStone((byte) 2, (byte) 3, 'B');
//        rules.testForLegal(board, stone1, true);
//        board.set(stone1);
//
//        System.out.println("Current boardstate:\n");
//        board.printBoard();
//        System.out.println();
////
////        System.out.println("Testing for valid a specific points");
////        System.out.println(stone1 + ": " + rules.testForLegal(board, stone1, false));
////        System.out.println(stone2 + ": " + rules.testForLegal(board, stone2, false));
////        System.out.println();
////
//        System.out.println("Searching for valid moves for both colours:");
//        for (byte row = 0, rows = board.getSize(); row < rows; row++) {
//            for (byte column = 0, columns = board.getSize(); column < columns; column++) {
//                char identifier = 'W';
//                if (rules.testForLegal(board, new OthelloStone(row, column, identifier), false)) {
//                    System.out.println("Valid position for " + identifier + " at: " + row + ", " + column);
//                }
//            }
//        }
//        for (byte row = 0, rows = board.getSize(); row < rows; row++) {
//            for (byte column = 0, columns = board.getSize(); column < columns; column++) {
//                char identifier = 'B';
//                if (rules.testForLegal(board, new OthelloStone(row, column, identifier), false)) {
//                    System.out.println("Valid position for " + identifier + " at: " + row + ", " + column);
//                }
//            }
//        }
//        System.out.println();
//
//        System.out.println("Testing rules.gameOver():");
//        System.out.println(rules.gameOver(board));

    }

}