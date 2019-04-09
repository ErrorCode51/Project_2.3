package View;

public class userInputHelper implements Runnable {

    TicTacToeView view;

    byte row;
    byte column;
    char identifier;

    public userInputHelper(TicTacToeView view, byte row, byte column, char identifier) {

        this.view = view;

        this.row = row;
        this.column = column;
        this.identifier = identifier;

    }

    public void run() {
        view.getCell()[row][column].setPlayer(identifier);
    }
}