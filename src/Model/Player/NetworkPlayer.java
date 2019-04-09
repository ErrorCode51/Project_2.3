package Model.Player;

import Controller.NetworkInputObserver.NetworkInputObserver;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Model.Board.Board;
import Model.Rules.Rules;

public class NetworkPlayer implements Player, NetworkInputObserver {
    private byte row = -1;
    private byte column = -1;
    private byte boardSize;
    private final char identifier;


    public NetworkPlayer(char identifier) {
        this.identifier = identifier;
    }


    public char getIdentifier() {
        return this.identifier;
    }


    public byte[] placeStone(Board board, Rules rules) {
        System.out.println("NetworkPlayer.placeStone() was called");
        this.row = -1;
        this.column = -1;
        this.boardSize = board.getSize();

        NetworkInputSubject.subscribe(this);

        while (this.row == -1 && this.column == -1) {
            Thread.yield();
        }
        System.out.println("NetworkPlayer's move: " + new byte[]{row, column});
        NetworkInputSubject.unsubscribe(this);
        return new byte[]{this.row, this.column};
    }


    public void notify(byte tile) {
        if (tile >= this.boardSize) {
            this.row = (byte)(tile / this.boardSize);
        }
        this.column = (byte)(tile % this.boardSize);
    }


    public String toString() {
        return "Jebediah Kerman";
    }
}
