package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;
import controller.playerInputObserver.playerInputObserver;
import controller.playerInputObserver.playerInputSubject;

public class humanPlayer implements player, playerInputObserver {
    private byte setX = -1;
    private byte setY = -1;

    public byte[] takeTurn(playingField field, gameRules rules){
        this.setX = -1;
        this.setY = -1;

        playerInputSubject.subscribe(this);

        while (this.setX == -1 || setY == -1);

        playerInputSubject.unsubscribe(this);

        return new byte[] {this.setX, this.setY};
    }


    public boolean isReady() {
        return true;
    }


    public void notify(byte x, byte y) {
        this.setX = x;
        this.setY = y;
    }
}
