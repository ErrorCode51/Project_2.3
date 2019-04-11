package Model.Player;

import Controller.NetworkInputObserver.NetworkInputObserver;
import Controller.NetworkInputObserver.NetworkInputSubject;
import Model.Board.Board;
import Model.Rules.Rules;

public class NetworkPlayer implements Player, NetworkInputObserver {
    private byte row = -1;
    private byte column = -1;
    private byte move = -1;
    private byte boardSize;
    private final char identifier;


    public NetworkPlayer(char identifier) {
        this.identifier = identifier;
    }


    public char getIdentifier() {
        return this.identifier;
    }


    public byte[] placeStone(Board board, Rules rules) {
        this.boardSize = board.getSize();

//        NetworkInputSubject.subscribe(this);

        while (this.move == -1) {
            Thread.yield();
        }

        this.row = (byte) (this.move / this.boardSize);
        this.column = (byte) (this.move % this.boardSize);

//        NetworkInputSubject.unsubscribe(this);
        resetMove();
        return new byte[]{this.row, this.column};
    }


    public boolean moveAvailable() {
        return (this.move != -1);
    }


    public byte[] getMove() {
        return new byte[]{row, column};
    }


    public void resetMove() {
        this.move = -1;
    }


    public void notify(byte tile) {
        this.move = tile;
    }


    public String toString() {
        return "Jebediah Kerman";
    }
}
