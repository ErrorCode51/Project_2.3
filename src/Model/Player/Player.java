package Model.Player;

import Model.Board.Board;
import Model.Rules.Rules;


public interface Player {

//    public final char identifier;
//
//    Player(char identifier) {
//
//        if (identifier == 'X' || identifier == 'O' || identifier == 'B' || identifier == 'W') {
//            this.identifier = identifier;
//        } else {
//            throw new InvalidIdentifierException("Invalid identifier provided.");
//        }
//
//    }
//
//    public char getIdentifier() {
//        return identifier;
//    }

    char getIdentifier();

    byte[] placeStone(Board board, Rules rules);

}