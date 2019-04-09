package RE2.Model.Game;

import RE2.Model.Board.OthelloBoard;

import RE2.Model.Player.LocalPlayer;
import RE2.Model.Player.Player;
import RE2.Model.Rules.OthelloRules;
import RE2.Model.Stone.OthelloStone;
import RE2.Model.Stone.Stone;
import RE2.View.OthelloView;

public class Othello implements Game {

    public final Player[] players;
    private final OthelloBoard board;
    private final OthelloRules rules;
    private final OthelloView view;
    private char currentPlayer;

    public Othello(OthelloView view) {

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
                break;
            case 'B':
                System.out.println(rules.getScore(board, 'B') + " vs. " + rules.getScore(board, 'W'));
                System.err.println(getPlayerByIdentifier('B') + " has won!!!");
                break;
            case 'W':
                System.out.println(rules.getScore(board, 'W') + " vs. " + rules.getScore(board, 'B'));
                System.err.println(getPlayerByIdentifier('W') + " has won!!!");
        }
    }

    public boolean handlePlacement(Player player) {
        byte[] placement = player.placeStone(board, rules);
        try {
            if (placement[0] < board.getSize() && placement[1] < board.getSize()) {
                Stone stone = new OthelloStone(placement[0], placement[1], player.getIdentifier());
                // Todo: stop calling this method twice you idiot!
                if (!rules.testForLegal(board, stone, false)) {
                    return false;
                }
                rules.testForLegal(board, stone, true);
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
