package Model.Game;

import Model.Board.Board;
import Model.Board.OthelloBoard;
import Model.Player.Player;
import Model.Rules.OthelloRules;
import Model.Rules.Rules;
import Model.Stone.OthelloStone;

public class Othello implements Game {

    private final Board board;
    private final Rules rules;

    private char currentPlayer;

    public final Player[] players = new Player[2];

    public Othello() {

        this.board = new OthelloBoard();
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

    }

    @Override
    public void run() {

    }
}
