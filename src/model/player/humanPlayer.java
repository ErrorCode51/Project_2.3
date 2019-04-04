package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;
import controller.playerInputObserver.playerInputObserver;
import controller.playerInputObserver.playerInputSubject;

public class humanPlayer implements player, playerInputObserver {
    private byte setX = -1;
    private byte setY = -1;

    // Our AI needs to be able to differentiate between players.
    // Temporary solution as of now:
    // Computer = 1
    // Human = 2
    // Network = 3
    public static final byte ID = 2;
    // Todo: Receive name from server
    public String name = "Scorpion";
  

    public int getID() {
        return ID;
    }

    // What is this?
    public boolean isReady() {
        return true;
    }
  
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


    public void notify(byte x, byte y) {
        System.out.println("this player has been notified");
        this.setX = x;
        this.setY = y;

        System.out.println(this.setX);
        System.out.println(this.setY);

        System.out.println(this.setX == -1);
    }
     
    @Override
    public String toString() {
        return name;
    }

}
