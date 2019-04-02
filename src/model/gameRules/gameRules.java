package model.gameRules;

import model.playingField.playingField;

public interface gameRules {

    public int getValidMoves(playingField field); // returns a list of all the moves to which a piece may be placed
//    TODO: een return type kiezen

    public byte getGameStatus(playingField field); // returns the status of the game (you won/lost / game is still going)
}
