package Model.Player;

import Controller.userInputObserver.userInputObserver;
import Controller.userInputObserver.userInputSubject;
import Model.Board.Board;
import Model.Container.TemporaryPlacement;
import Model.Rules.Rules;
import Model.Stone.TicTacToeStone;

public class LocalPlayer implements Player, userInputObserver {

    private byte row = -1;
    private byte column = -1;

    public char identifier;

    public LocalPlayer(char identifier) {

        this.identifier = identifier;

    }

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public byte[] placeStone(Board board, Rules rules) {
        this.row = -1;
        this.column = -1;

        userInputSubject.subscribe(this);

        while (this.row == -1 && this.column == -1) {
            Thread.yield();
        }

        userInputSubject.unsubscribe(this);
        return new byte[]{this.row, this.column};
    }


    @Override
    public String toString() {
        return "Sekiro";
    }

    public boolean placeStone(Board board, byte row, byte column, char identifier) {
        if (!board.isEmpty(row, column)) {
            return false;
        } else {
            TicTacToeStone stone = new TicTacToeStone(row, column, identifier);
            board.set(stone);
            return true;
        }
    }

    @Override
    public void notify(byte row, byte column) {
        this.row = row;
        this.column = column;
    }
}