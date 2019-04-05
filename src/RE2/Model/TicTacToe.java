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
        // Player playerOne = new ArtificialPlayer('X');
        // Player playerTwo = new ArtificialPlayer('O');

        Player playerOne = new LocalPlayer('X');
        Player playerTwo = new LocalPlayer('O');

        currentPlayer = 'X';

//        board.printBoard();
//
//        while (flag) {
//            Player player = (currentPlayer == 'X') ? playerOne : playerTwo;
//            TemporaryPlacement placement = player.placeStone(board);
//            board.printBoard();
//            this.changePlayer();
//            if (board.gameOver(board) != TicTacToeRules.ONGOING) {
//                flag = false;
//            }
//        }



    }

    public Board getBoard() {
        return board;
    }

    public char gameOver(Board board) {
        return this.board.gameOver(board);
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public void changePlayer() {
        board.gameOver(board);
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

}

