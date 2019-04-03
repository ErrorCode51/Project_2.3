package model.gameRules;

import model.playingField.playingField;

public interface gameRules {

    public byte[][] getValidMoves(playingField field); // returns a list of all the moves to which a piece may be placed
//    TODO: een return type kiezen

    public byte getGameStatus(playingField field); // returns the status of the game (you won/lost / game is still going)

    public boolean boardIsFull(playingField field);

    public boolean gameFinished(playingField field);
}
