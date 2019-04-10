package Model.Game;

import Model.Board.OthelloBoard;

import Model.Player.ArtificialPlayer;
import Model.Player.LocalPlayer;
import Model.Player.Player;
import Model.Rules.OthelloRules;
import Model.Stone.OthelloStone;
import Model.Stone.Stone;
import View.OthelloView;

import java.util.ArrayList;
import java.util.Random;

public class Othello implements Game {

    public final Player[] players;
    private final OthelloBoard board;
    private final OthelloRules rules;
    private final OthelloView view;
    private char currentPlayer;

    public Othello(OthelloView view, boolean usingNetwork) {

        board = new OthelloBoard();
        players = new Player[2];
        this.view = view;
        this.rules = new OthelloRules();

        board.initialize(rules);
        //// Set starting positions
        // board.set(new OthelloStone((byte) 3, (byte) 3, 'W'));
        // board.set(new OthelloStone((byte) 3, (byte) 4, 'B'));
        // board.set(new OthelloStone((byte) 4, (byte) 3, 'B'));
        // board.set(new OthelloStone((byte) 4, (byte) 4, 'W'));
        //// Footnote:
        //// It appears the appropriate conversion is performed during compile-time and not during
        //// runtime, in short this means performance is not impacted.
        // Set starting positions
        board.getTiles()[3][3] = new OthelloStone((byte) 3, (byte) 3, 'W');
        board.getTiles()[3][4] = new OthelloStone((byte) 3, (byte) 4, 'B');
        board.getTiles()[4][3] = new OthelloStone((byte) 4, (byte) 3, 'B');
        board.getTiles()[4][4] = new OthelloStone((byte) 4, (byte) 4, 'W');

        players[0] = new LocalPlayer('W');
        players[1] = new LocalPlayer('B');

    }

    @Override
    public void run() {
        while (rules.gameOver(board) == 'N') {
            try {
                if (true) {
                    break;
                }
            } catch (Exception e) {
            }
        }
        currentPlayer = 'W';
        while (rules.gameOver(board) == 'N') {
            if (rules.findAllLegal(board, currentPlayer).size() < 1) {
                System.err.println(currentPlayer + " skips a turn!");
                changePlayer();
            }
            if (handlePlacement(getPlayerByIdentifier(currentPlayer))) {
                view.setBoard(board);
                changePlayer();
            }
        }
        switch (rules.gameOver(board)) {
            case 'D':
                System.out.println(rules.getScore(board, 'B') + " vs. " + rules.getScore(board, 'W'));
                System.err.println("It's a draw");
                board.printBoard();
                break;
            case 'B':
                System.out.println(rules.getScore(board, 'B') + " vs. " + rules.getScore(board, 'W'));
                System.err.println(getPlayerByIdentifier('B') + " has won!!!");
                board.printBoard();
                break;
            case 'W':
                System.out.println(rules.getScore(board, 'W') + " vs. " + rules.getScore(board, 'B'));
                System.err.println(getPlayerByIdentifier('W') + " has won!!!");
                board.printBoard();
        }
    }

    public boolean handlePlacement(Player player) {
        byte[] placement = player.placeStone(board, rules);
        try {
            if (placement[0] < board.getSize() && placement[1] < board.getSize()) {
                Stone stone = new OthelloStone(placement[0], placement[1], player.getIdentifier());
                // Todo: stop calling this method twice you idiot!
                if (!rules.testForLegal(board, stone, false)) {
                    /////////////////////
                    // SUPER DIRTY FIX //
                    /////////////////////
                    if (getPlayerByIdentifier(currentPlayer).getClass() == ArtificialPlayer.class) {
                        ArrayList<Stone> test = ((OthelloRules) rules).findAllLegal(board, currentPlayer);
                        Random random = new Random();
                        rules.testForLegal(board, test.get(random.nextInt(test.size())), true);
                        return board.set(test.get(random.nextInt(test.size())));
                    }
                    return false;
                }
                rules.testForLegal(board, stone, true);
                System.out.println(stone);
                return board.set(stone);
            }
        } catch (Exception e) {
            System.err.println("Invalid placement");
            e.printStackTrace();
        }
        return false;
    }

    public Player getPlayerByIdentifier(char identifier) {
        for (Player player : players) {
            if (player.getIdentifier() == identifier) {
                return player;
            }
        }
        // Todo: Fix this null thing.
        System.out.println("getPlayerByIdentifier will return null");
        return null;
    }

    // Change the current player to the next player
    public void changePlayer() {
        board.gameOver(board);
        currentPlayer = (currentPlayer == 'B') ? 'W' : 'B';
    }
}