package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;
import controller.playerInputObserver.playerInputObserver;
import controller.playerInputObserver.playerInputSubject;

public class humanPlayer implements player, playerInputObserver {
    private byte setX = -1;
    private byte setY = -1;

    public byte[] takeTurn(playingField field, gameRules rules){
        System.out.println("Player has been given a turn");
        this.setX = -1;
        this.setY = -1;

        playerInputSubject.subscribe(this);

        System.out.println(this.setX == -1);

        System.out.println("going into while loop");
        while (this.setX == -1 && this.setY == -1) { Thread.yield(); }

        System.out.println("exiting while loop");

        playerInputSubject.unsubscribe(this);

        return new byte[] {this.setX, this.setY};
    }


    public boolean isReady() {
        return true;
    }


    public void notify(byte x, byte y) {
        System.out.println("this player has been notified");
        this.setX = x;
        this.setY = y;

        System.out.println(this.setX);
        System.out.println(this.setY);

        System.out.println(this.setX == -1);
    }
}
