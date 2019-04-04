package model;

import model.gameRules.gameRules;
import model.player.humanPlayer;
import model.player.player;
import model.playingField.playingField;
import view.TictactoeView;

import java.util.Arrays;

// this is the main object of a game
// it contains all the object nessecary to play the game
public class game implements Runnable {
    private playingField field;
    private gameRules daRules;
    private player players[];
    private TictactoeView view;


    public game(TictactoeView view) {
        field = new playingField(3);
        players = new player[2];
        this.view = view;

        players[0] = new humanPlayer();
        players[1] = new humanPlayer();
    }


    public void run() {
        System.out.println("run() has been called");
        while (true) {
            try {
                if (this.view.isReady()) {
                    break;
                }
            }
            catch (Exception e) {}
        }
        System.out.println("TicTacToeView has been set up");
        while (true) {
            handleTurn(players[0]);
            view.setPlayingfield(field);
            handleTurn(players[1]);
            view.setPlayingfield(field);
        }
    }


    private void handleTurn(player player) {
        byte[] set = player.takeTurn(field, daRules);
        try {
            if (set[0] < field.getSize() && set[1] < field.getSize()) {
                field.setTile(set[0], set[1], (byte)(Arrays.asList(players).indexOf(player) +1));
            }
        }
        catch (Exception e){
            System.err.println("Godverdomme Kyle, dat is geen zet");
        }
    }
}



//           '\
//            _\______
//           /        \========
//      ____|__________\_____
//      / ___________________ \
//      \/ _===============_ \/
//        "-===============-"
