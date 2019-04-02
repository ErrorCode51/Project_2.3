package model;

import model.gameRules.gameRules;
import model.player.player;
import model.playingField.playingField;

import java.util.Arrays;

// this is the main object of a game
// it contains all the object nessecary to play the game
public class game {
    private playingField field;
    private gameRules daRules;
    private player players[];


    public game() {
        field = new playingField(3);
        players = new player[2];

        while (true) {
            handeTurn(players[0]);
            handeTurn(players[1]);
        }
    }


    private void handeTurn(player player) {
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
