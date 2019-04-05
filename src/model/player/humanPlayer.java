package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;
import controller.playerInputObserver.playerInputObserver;
import controller.playerInputObserver.playerInputSubject;

public class humanPlayer implements player, playerInputObserver {
    private byte setX = -1;
    private byte setY = -1;
    private final byte ID;


    // Todo: Receive name from server
    public String name = "Scorpion";


    public humanPlayer(byte ID) {
        this.ID = ID;
    }


    public byte getID() {
        return ID;
    }

    // What is this?
    public boolean isReady() {
        return true;
    }

    public byte[] takeTurn(playingField field, gameRules rules) {
        System.out.println("Human player has been given a turn");
        this.setX = -1;
        this.setY = -1;

        playerInputSubject.subscribe(this);

        while (this.setX == -1 && this.setY == -1) {
            Thread.yield();
        }

        playerInputSubject.unsubscribe(this);

        return new byte[]{this.setX, this.setY};
    }


    public void notify(byte x, byte y) {
        this.setX = x;
        this.setY = y;
    }
     
    @Override
    public String toString() {
        return name;
    }

}
