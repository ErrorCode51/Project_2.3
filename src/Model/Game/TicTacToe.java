package Model.Game;

import Controller.NetworkConfigurator;
import Controller.ServerController;
import Model.Board.Board;
import Model.Board.TicTacToeBoard;
import Model.Player.ArtificialPlayer;
import Model.Player.LocalPlayer;
import Model.Player.NetworkPlayer;
import Model.Player.Player;
import Model.Rules.Rules;
import Model.Rules.TicTacToeRules;
import Model.Stone.Stone;
import Model.Stone.TicTacToeStone;
import View.TicTacToeView;

public class TicTacToe implements Game {

    public final Player[] players;
    private final TicTacToeBoard board;
    private final Rules rules;
    private final TicTacToeView view;
    private char currentPlayer;
    private ServerController controllertje;

    public TicTacToe(TicTacToeView view, boolean usingNetwork) {


        // Refactor: Constructor Done!
        board = new TicTacToeBoard();
        players = new Player[2];
        this.view = view;
        this.rules = new TicTacToeRules();
        board.initialize(rules);

        if (!usingNetwork) {
            players[0] = new LocalPlayer('X');
            players[1] = new ArtificialPlayer('O');
        } else {
            this.controllertje = new ServerController();
            Thread t = new Thread(controllertje);
            t.start();

            while (controllertje.getPlayerToMove() == null) {
                Thread.yield();
            }

            if (controllertje.getPlayerToMove().equals(NetworkConfigurator.getPropertie("PLAYER_NAME"))) {
                players[0] = new ArtificialPlayer('X');
                players[1] = new NetworkPlayer('O');
            } else {
                players[0] = new NetworkPlayer('X');
                players[1] = new ArtificialPlayer('O');
            }
        }
    }

    @Override
    public void run() {
        while (rules.gameOver(board) == 'N') {
            try {
                if (true) {
                    break;
                }
            }
            catch (Exception e) {}
        }
        currentPlayer = 'X';
        while (rules.gameOver(board) == 'N') {
            if (handlePlacement(getPlayerByIdentifier(currentPlayer))) {
                view.setBoard(board);
                changePlayer();
            }
        }
        switch (rules.gameOver(board)) {
            case 'D':
                System.err.println("It's a draw");
                break;
            case 'X':
                System.err.println(players[0] + " has won!!!");
                break;
            case 'O':
                System.err.println(players[1] + " has won!!!");
        }
    }

    public boolean handlePlacement(Player player) {
        byte[] placement = player.placeStone(board, rules);
        try {
            if (placement[0] < board.getSize() && placement[1] < board.getSize()) {
                Stone stone = new TicTacToeStone(placement[0], placement[1], player.getIdentifier());
                // System.out.println(stone);
                if (controllertje != null && !(player instanceof NetworkPlayer))
                    controllertje.sendMove(placement);
                return board.set(stone);
            }
        }
        catch (Exception e){
            System.err.println("Invalid placement");
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
        System.err.println("Dafuq just happened");
        return null;
    }

    // Returns the board bound to this game
    public Board getBoard() {
        return board;
    }

    // Returns the identifier of the player whose turn it is
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    // Change the current player to the next player
    public void changePlayer() {
        board.gameOver(board);
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

}

