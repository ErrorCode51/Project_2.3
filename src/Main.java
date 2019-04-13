import Controller.NetworkConfigurator;

import Model.Board.OthelloBoard;
import Model.Player.AlphaBetaReworkAI;
import Model.Player.ArtificialOthello;
//import Model.Player.ReworkAI;
import Model.Rules.OthelloRules;
import Model.Stone.OthelloStone;

import Controller.ServerController;

import View.Homescreen;
import View.TicTacToeView;

import java.util.ArrayList;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        NetworkConfigurator.loadPropFile();

        // FOR TESTING todo: REMOVE WHEN DONE TESTING FFS
//        ServerController.createPersistentServerController();
//
//
        launch(Homescreen.class, args);

//        OthelloBoard board = new OthelloBoard();
//        OthelloRules rules = new OthelloRules();
//
//        board.initialize(rules);
//
//        board.set(new OthelloStone((byte) 3, (byte) 3, 'W'));
//        board.set(new OthelloStone((byte) 3, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 4, (byte) 3, 'B'));
//        board.set(new OthelloStone((byte) 4, (byte) 4, 'W'));

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


//        ArtificialOthello test = new ArtificialOthello('W');
//        test.placeStone(board, rules);

//        OthelloStone a = new OthelloStone((byte) 0, (byte) 0, 'B');
//        OthelloStone b = new OthelloStone((byte) 0, (byte) 0, 'B');

//        ArrayList<OthelloStone> list = new ArrayList<>();
//        list.add(a);
//        System.out.println(list);
//        System.out.println(list.contains(b));

//        OthelloStone stone = new OthelloStone((byte) 1, (byte) 6);
//        System.out.println(board.isEmpty((stone.getX() == 1) ? (byte) 0 : 7, (stone.getY() == 1) ? (byte) 0 : 7));

//        board.set(new OthelloStone((byte) 0, (byte) 0, 'B'));
//        board.set(new OthelloStone((byte) 0, (byte) 7, 'W'));
//        board.set(new OthelloStone((byte) 7, (byte) 7, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 0, 'B'));

//        board.set(new OthelloStone((byte) 2, (byte) 5, 'B'));
//        board.set(new OthelloStone((byte) 3, (byte) 4, 'W'));
//        board.set(new OthelloStone((byte) 5, (byte) 2, 'B'));
//        board.set(new OthelloStone((byte) 4, (byte) 3, 'W'));

//        board.set(new OthelloStone((byte) 0, (byte) 0, 'B'));
//        board.set(new OthelloStone((byte) 1, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 2, (byte) 2, 'B'));
//        board.set(new OthelloStone((byte) 3, (byte) 3, 'B'));
//        board.set(new OthelloStone((byte) 0, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 1, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 2, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 3, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 5, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 5, (byte) 2, 'B'));
//        board.set(new OthelloStone((byte) 5, (byte) 3, 'B'));
//        board.set(new OthelloStone((byte) 5, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 6, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 6, (byte) 2, 'B'));
//        board.set(new OthelloStone((byte) 6, (byte) 3, 'B'));
//        board.set(new OthelloStone((byte) 6, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 1, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 2, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 3, 'B'));
//        board.set(new OthelloStone((byte) 7, (byte) 4, 'B'));
//        board.set(new OthelloStone((byte) 4, (byte) 3, 'B'));
//        board.set(new OthelloStone((byte) 4, (byte) 4, 'W'));

//        System.out.println(board);
//        System.out.println(rules.findAllLegal(board, 'W'));
//        AlphaBetaReworkAI test = new AlphaBetaReworkAI('W');
//        test.placeStone(board, rules);

    }
}