package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;

public class humanPlayer implements player {

    public byte[] takeTurn(playingField field, gameRules rules){
        return null;
    }


    public boolean isReady() {
        return false;
    }
}