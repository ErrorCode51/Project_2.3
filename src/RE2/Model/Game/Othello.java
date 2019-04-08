package RE2.Model.Game;

import RE2.Model.Board.Board;
import RE2.Model.Board.OthelloBoard;
import RE2.Model.Player.Player;
import RE2.Model.Rules.OthelloRules;
import RE2.Model.Rules.Rules;
import RE2.Model.Stone.OthelloStone;

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
