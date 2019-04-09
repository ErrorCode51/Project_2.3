package Model.Player;

import Controller.NetworkInputObserver.NetworkInputObserver;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Model.Board.Board;
import Model.Rules.Rules;

public class NetworkPlayer implements Player, NetworkInputObserver {
    private byte row = -1;
    private byte column = -1;
    private final char identifier;


    public NetworkPlayer(char identifier) {
        this.identifier = identifier;
    }


    public char getIdentifier() {
        return this.identifier;
    }


    public byte[] placeStone(Board board, Rules rules) {
        this.row = -1;
        this.column = -1;

        NetworkInputSubject.subscribe(this);

        while (this.row == -1 && this.column == -1) {
            Thread.yield();
        }

        NetworkInputSubject.unsubscribe(this);
        return new byte[]{this.row, this.column};
    }


    public void notify(byte row, byte column) {
        this.row = row;
        this.column = column;
    }


    public String toString() {
        return "Jebediah Kerman";
    }
}
