package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;

// this is the player interface, it needs concrete classes for a human, computer and a network player
public interface player {

    public int takeTurn(playingField field, gameRules rules);

}
