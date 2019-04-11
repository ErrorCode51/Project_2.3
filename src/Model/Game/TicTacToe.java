package Model.Game;

import Controller.NetworkConfigurator;
import Controller.NetworkInputObserver.NetworkInputObserver;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Controller.NetworkTurnObserver.NetworkTurnObserver;
import Controller.NetworkTurnObserver.NetworkTurnSubject;
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

public class TicTacToe implements Game, NetworkTurnObserver {

    public final Player[] players;
    private final TicTacToeBoard board;
    private final Rules rules;
    private final TicTacToeView view;
    private char currentPlayer;
    private ServerController controllertje;

    private boolean yourTurn;
    private byte localPosition;
    private NetworkPlayer nwPlayer;

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
            this.controllertje = ServerController.getPersistentServerController();
//            controllertje.resetGameData();

            while (controllertje.getPlayerToMove() == null) {
                Thread.yield();
            }

            if (controllertje.getPlayerToMove().equals(NetworkConfigurator.getProperty("PLAYER_NAME"))) {
                players[0] = new LocalPlayer('X');
                nwPlayer = new NetworkPlayer('O');
                players[1] = nwPlayer;
                localPosition = 0;
            } else {
                nwPlayer = new NetworkPlayer('X');
                players[0] = nwPlayer;
                players[1] = new LocalPlayer('O');
                localPosition = 1;
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
        if (controllertje == null) {
            while (rules.gameOver(board) == 'N') {
                if (handlePlacement(getPlayerByIdentifier(currentPlayer))) {
                    view.setBoard(board);
                    changePlayer();
                }
            }
        } else {
            NetworkTurnSubject.subscribe(this);
            NetworkInputSubject.subscribe(nwPlayer);
            while (rules.gameOver(board) == 'N') {
                if (yourTurn) {
                    handlePlacement(players[localPosition]);
                    view.setBoard(board);
                    yourTurn = false;
                } else if (nwPlayer.moveAvailable()) {
                    handlePlacement(nwPlayer);
                    view.setBoard(board);
                }
            }
            NetworkTurnSubject.unsubscribe(this);
            NetworkInputSubject.unsubscribe(nwPlayer);
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


    public void giveTurn() {
        System.err.println("You have been given the turn");
        this.yourTurn = true;
    }

}

