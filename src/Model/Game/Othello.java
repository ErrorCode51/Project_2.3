package Model.Game;

import Model.Board.Board;
import Model.Board.OthelloBoard;
import Model.Board.TicTacToeBoard;
import Model.Player.ArtificialPlayer;
import Model.Player.LocalPlayer;
import Model.Player.Player;
import Model.Rules.OthelloRules;
import Model.Rules.Rules;
import Model.Rules.TicTacToeRules;
import Model.Stone.OthelloStone;
import View.OthelloView;
import View.TicTacToeView;

public class Othello implements Game {

    public final Player[] players;
    private final OthelloBoard board;
    private final Rules rules;
    private final OthelloView view;
    private char currentPlayer;

    public Othello(OthelloView view) {

        board = new OthelloBoard();
        players = new Player[2];
        this.view = view;
        this.rules = new OthelloRules();

        board.initialize(rules);
        // Set starting positions
        board.set(new OthelloStone((byte) 3, (byte) 3, 'W'));
        board.set(new OthelloStone((byte) 3, (byte) 4, 'B'));
        board.set(new OthelloStone((byte) 4, (byte) 3, 'B'));
        board.set(new OthelloStone((byte) 4, (byte) 4, 'W'));
        // Footnote:
        // It appears the appropriate conversion is performed during compile-time and not during
        // runtime, in short this means performance is not impacted.
        players[0] = new LocalPlayer('X');
        players[1] = new ArtificialPlayer('O');

    }

    @Override
    public void run() {
        // Code....
    }
}