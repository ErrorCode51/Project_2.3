package Model.Game;

import Controller.NetworkConfigurator;
import Controller.NetworkForfeitObserver.NetworkForfeitObserver;
import Controller.NetworkForfeitObserver.NetworkForfeitSubject;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Controller.NetworkTurnObserver.NetworkTurnObserver;
import Controller.NetworkTurnObserver.NetworkTurnSubject;
import Controller.ServerController;
import Model.Board.OthelloBoard;
import Model.Player.ArtificialOthello;
import Model.Player.ArtificialPlayer;
import Model.Player.LocalPlayer;
import Model.Player.NetworkPlayer;
import Model.Player.Player;
import Model.Rules.OthelloRules;
import Model.Stone.OthelloStone;
import Model.Stone.Stone;
import View.OthelloView;

import java.util.ArrayList;
import java.util.Random;

public class Othello implements Game, NetworkTurnObserver, NetworkForfeitObserver {

    public final Player[] players;
    private final OthelloBoard board;
    private final OthelloRules rules;
    private final OthelloView view;
    private char currentPlayer;
    private ServerController controllertje;
    private NetworkPlayer nwPlayer;
    private byte localPosition;
    private boolean yourTurn;
    private boolean enemyForfeited = false;

    public Othello(OthelloView view, boolean usingNetwork) {

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
        view.setBoard(board);


        if (!usingNetwork) {
            players[0] = new LocalPlayer('X');
            players[1] = new ArtificialPlayer('O');
        } else {
            this.controllertje = ServerController.getPersistentServerController();

            while (controllertje.getPlayerToMove() == null) {
                Thread.yield();
            }

            if (controllertje.getPlayerToMove().equals(NetworkConfigurator.getProperty("PLAYER_NAME"))) {
                players[0] = new LocalPlayer('W');
                nwPlayer = new NetworkPlayer('B');
                players[1] = nwPlayer;
                localPosition = 0;
            } else {
                nwPlayer = new NetworkPlayer('W');
                players[0] = nwPlayer;
                players[1] = new LocalPlayer('B');
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
            } catch (Exception e) {
            }
        }
        if (controllertje == null) {
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
        } else {
            NetworkTurnSubject.subscribe(this);
            NetworkInputSubject.subscribe(nwPlayer);
            NetworkForfeitSubject.subscribe(this);
            while (rules.gameOver(board) == 'N' && !enemyForfeited) {
                if (yourTurn) {
                    currentPlayer = players[localPosition].getIdentifier();
                    handlePlacement(players[localPosition]);
                    view.setBoard(board);
                    yourTurn = false;
                    currentPlayer = nwPlayer.getIdentifier();
                } else if (nwPlayer.moveAvailable()) {
                    handlePlacement(nwPlayer);
                    view.setBoard(board);
                }
            }
            NetworkTurnSubject.unsubscribe(this);
            NetworkInputSubject.unsubscribe(nwPlayer);
            NetworkForfeitSubject.subscribe(this);
        }

        switch (rules.gameOver(board)) {
            case 'D':
                System.out.println(rules.getScore(board, 'B') + " vs. " + rules.getScore(board, 'W'));
                System.err.println("It's a draw");
                System.out.println(board);
                break;
            case 'B':
                System.out.println(rules.getScore(board, 'B') + " vs. " + rules.getScore(board, 'W'));
                System.err.println(getPlayerByIdentifier('B') + " has won!!!");
                System.out.println(board);
                break;
            case 'W':
                System.out.println(rules.getScore(board, 'W') + " vs. " + rules.getScore(board, 'B'));
                System.err.println(getPlayerByIdentifier('W') + " has won!!!");
                System.out.println(board);
        }
    }

    public boolean handlePlacement(Player player) {
        byte[] placement = player.placeStone(board, rules);
        // System.out.println("Player returns: " + placement[0] + ", " + placement[1]);
        try {
            if (placement[0] < board.getSize() && placement[1] < board.getSize()) {
                Stone stone = new OthelloStone(placement[0], placement[1], player.getIdentifier());
                // Todo: stop calling this method twice you idiot!
                if (!rules.testForLegal(board, stone, false)) {
                    /////////////////////
                    // SUPER DIRTY FIX //
                    /////////////////////
                    // if (getPlayerByIdentifier(currentPlayer).getClass() == ArtificialPlayer.class) {
                    if (getPlayerByIdentifier(currentPlayer).getClass() == ArtificialOthello.class) {
                        System.err.println("Random placement");
                        ArrayList<Stone> test = rules.findAllLegal(board, currentPlayer);
                        Random random = new Random();
                        rules.testForLegal(board, test.get(random.nextInt(test.size())), true);
                        return board.set(test.get(random.nextInt(test.size())));
                    }
                    return false;
                }
                rules.testForLegal(board, stone, true);
                // System.out.println(stone);
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


    public void giveTurn() {
        System.err.println("You have been given the turn");
        this.yourTurn = true;
    }


    public void informAboutEnemyForfeit() {
        this.enemyForfeited = true;
    }
}
