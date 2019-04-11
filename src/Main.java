import Controller.NetworkConfigurator;
import Model.Board.OthelloBoard;
import Model.Player.ArtificialOthello;
import Model.Rules.OthelloRules;
import Model.Stone.OthelloStone;
import View.Homescreen;
import View.TicTacToeView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        NetworkConfigurator.loadPropFile();

         launch(Homescreen.class, args);

//        OthelloBoard board = new OthelloBoard();
//        OthelloRules rules = new OthelloRules();

//        board.initialize(rules);

//        board.set(new OthelloStone((byte) 0, (byte) 7, 'B'));
//        board.set(new OthelloStone((byte) 0, (byte) 6, 'B'));
//        board.set(new OthelloStone((byte) 1, (byte) 7, 'B'));
//        board.set(new OthelloStone((byte) 1, (byte) 6, 'B'));
//        board.set(new OthelloStone((byte) 2, (byte) 5, 'W'));

//        board.set(new OthelloStone((byte) 7, (byte) 0, 'B'));
//        board.set(new OthelloStone((byte) 6, (byte) 0, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 6, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 5, (byte) 2, 'W'));

//        System.out.println(rules.gameOver(board));

//        System.out.println(rules.findAllLegal(board, 'W'));
//        System.out.println(rules.findAllLegal(board, 'B'));

//        board.set(new OthelloStone((byte) 6, (byte) 0, 'W'));
//        board.set(new OthelloStone((byte) 6, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 1, 'B'));

//        System.out.println(board);

//        ArtificialOthello test = new ArtificialOthello('W');
//        test.placeStone(board, rules);

    }
}