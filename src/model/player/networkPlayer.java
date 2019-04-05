package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;

public class networkPlayer implements player {

    private final byte ID;


    // Todo: Receive name from server
    public String name = "Jade";


    public networkPlayer(byte ID) {
        this.ID = ID;
    }

    public byte getID() {
        return this.ID;
    }


    @Override
    public String toString() {
        return name;
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
