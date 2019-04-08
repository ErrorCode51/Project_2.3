package RE2.Model.Player;

import RE2.Model.Board.Board;
import RE2.Model.Rules.Rules;


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