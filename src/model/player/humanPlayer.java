package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;

public class humanPlayer implements player {

    // Our AI needs to be able to differentiate between players.
    // Temporary solution as of now:
    // Computer = 1
    // Human = 2
    // Network = 3
    public static final byte ID = 2;
    // Todo: Receive name from server
    public String name = "Scorpion";

    @Override
    public String toString() {
        return name;
    }

    public int getID() {
        return ID;
    }

    // What is this?
    public boolean isReady() {
        return false;
    }

    // Because it is mandatory
    // Todo: Why is this mandatory and why are field and rules provided if the method only returns coordinates?
    public byte[] takeTurn(playingField field, gameRules rules) {
        return null;
    }

    // Actual method that requires input
    // Todo: Why are field and rules required if the method only returns coordinates?
    public byte[] takeTurn(playingField field, gameRules rules, byte row, byte column){
        byte[] turn = new byte[2];
        turn[0] = row;
        turn[1] = column;
        return turn;
    }

}
