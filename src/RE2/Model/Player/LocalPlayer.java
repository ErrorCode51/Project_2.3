package RE2.Model.Player;

import RE2.Controller.userInputObserver.userInputObserver;
import RE2.Controller.userInputObserver.userInputSubject;
import RE2.Model.Board.Board;
import RE2.Model.Container.TemporaryPlacement;
import RE2.Model.Rules.Rules;
import RE2.Model.Stone.TicTacToeStone;

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