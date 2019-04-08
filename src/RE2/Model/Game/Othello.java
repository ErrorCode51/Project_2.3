package RE2.Model.Game;

import RE2.Model.Board.Board;
import RE2.Model.Board.OthelloBoard;
import RE2.Model.Board.TicTacToeBoard;
import RE2.Model.Player.ArtificialPlayer;
import RE2.Model.Player.LocalPlayer;
import RE2.Model.Player.Player;
import RE2.Model.Rules.OthelloRules;
import RE2.Model.Rules.Rules;
import RE2.Model.Rules.TicTacToeRules;
import RE2.Model.Stone.OthelloStone;
import RE2.View.OthelloView;
import RE2.View.TicTacToeView;

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
