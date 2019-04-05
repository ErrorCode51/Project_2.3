package RE2.Model;

public class TicTacToe implements Game {

    private Board board;
    private TicTacToeRules rules;

    private char currentPlayer;
    private boolean flag = true;

    public TicTacToe() {

        this.board = new Board((byte) 3);
        this.rules = new TicTacToeRules();
        board.initialize(rules);

    }

    @Override
    public void run() {
        Player playerOne = new ArtificialPlayer('X');
        Player playerTwo = new ArtificialPlayer('O');

        currentPlayer = 'X';

        board.printBoard();

        while (flag) {
            Player player = (currentPlayer == 'X') ? playerOne : playerTwo;
            TemporaryPlacement placement = player.placeStone(board);
            board.printBoard();
            this.changePlayer();
            if (board.gameOver(board) != TicTacToeRules.ONGOING) {
                flag = false;
            }
        }

    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

}

