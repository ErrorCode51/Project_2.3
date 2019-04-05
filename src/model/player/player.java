package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;

// this is the player interface, it needs concrete classes for a human, computer and a network player
public interface player {

    public byte[] takeTurn(playingField field, gameRules rules);

    public boolean isReady(); //returns a value indicating if the player is ready for the game to start

    public byte getID();

}
