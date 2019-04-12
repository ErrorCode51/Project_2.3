package Model.Game;

import Controller.NetworkConfigurator;
import Controller.NetworkForfeitObserver.NetworkForfeitObserver;
import Controller.NetworkForfeitObserver.NetworkForfeitSubject;
import Controller.NetworkInputObserver.NetworkInputObserver;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Controller.NetworkTurnObserver.NetworkTurnObserver;
import Controller.NetworkTurnObserver.NetworkTurnSubject;
import Controller.ServerController;
import Model.Board.Board;
import Model.Board.TicTacToeBoard;
import Model.Player.*;
import Model.Rules.Rules;
import Model.Rules.TicTacToeRules;
import Model.Stone.Stone;
import Model.Stone.TicTacToeStone;
import View.TicTacToeView;

import java.util.Random;

public class TicTacToe implements Game, NetworkTurnObserver, NetworkForfeitObserver {

    public final Player[] players;
    private final TicTacToeBoard board;
    private final Rules rules;
    private final TicTacToeView view;
    private char currentPlayer;
    private ServerController controllertje;

    private boolean yourTurn;
    private byte localPosition;
    private NetworkPlayer nwPlayer;
    private boolean enemyForfeited = false;

//    GameMode variable:
//    0: Local: Human vs AI
//    1: Local: Human vs Human
//    2: Local: AI vs AI
//    10: Network vs local Human
//    11: Network vs local AI

    public TicTacToe(TicTacToeView view, byte gameMode) {

        // Refactor: Constructor Done!
        board = new TicTacToeBoard();
        players = new Player[2];
        this.view = view;
        this.rules = new TicTacToeRules();
        board.initialize(rules);

        if (gameMode < 10) {
            switch (gameMode) {
                case 0:
                    if (new Random().nextBoolean()) {
                        players[0] = new LocalPlayer('X');
                        players[1] = new ArtificialPlayer('O');
                    } else {
                        players[0] = new ArtificialPlayer('X');
                        players[1] = new LocalPlayer('O');
                    }
                    break;
                case 1:
                    players[0] = new LocalPlayer('X');
                    players[1] = new LocalPlayer('O');
                    break;
                case 2:
                    players[0] = new ArtificialPlayer('X');
                    players[1] = new ArtificialPlayer('O');
                    break;
            }
        } else if (gameMode < 20) {
            this.controllertje = ServerController.getPersistentServerController();
//            controllertje.resetGameData();

            while (controllertje.getPlayerToMove() == null) {
                Thread.yield();
            }

            if (controllertje.getPlayerToMove().equals(NetworkConfigurator.getProperty("PLAYER_NAME"))) {
                players[1] = new NetworkPlayer('O');
                localPosition = 0;
            } else {
                players[0] = new NetworkPlayer('X');
                localPosition = 1;
            }

            switch (gameMode) {
                case 10:
                    players[localPosition] = new LocalPlayer((localPosition == 0) ? 'X' : 'O');
                    break;
                case 11:
                    players[localPosition] = new ArtificialPlayer((localPosition == 0) ? 'X' : 'O');
            }

//            if (controllertje.getPlayerToMove().equals(NetworkConfigurator.getProperty("PLAYER_NAME"))) {
//                if (usingAI) {
//                    players[0] = new ArtificialPlayer('X');
//                }
//                else {
//                    players[0] = new LocalPlayer('X');
//                }
//                nwPlayer = new NetworkPlayer('O');
//                players[1] = nwPlayer;
//                localPosition = 0;
//            } else {
//                nwPlayer = new NetworkPlayer('X');
//                players[0] = nwPlayer;
//                if (usingAI) {
//                    players[1] = new ArtificialPlayer('O');
//                }
//                else {
//                    players[1] = new LocalPlayer('O');
//                }
//                localPosition = 1;
//            }
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
            NetworkForfeitSubject.subscribe(this);
            while (rules.gameOver(board) == 'N' && !enemyForfeited) {
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
            NetworkForfeitSubject.unsubscribe(this);
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
                    controllertje.sendMove(placement, this.board.getSize());
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


    public void informAboutEnemyForfeit() {
        this.enemyForfeited = true;
    }

}

