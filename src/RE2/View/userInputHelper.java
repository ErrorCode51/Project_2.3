package RE2.View;

public class userInputHelper implements Runnable {

    View view;

    byte row;
    byte column;
    char identifier;

    public userInputHelper(View view, byte row, byte column, char identifier) {

        this.view = view;

        this.row = row;
        this.column = column;
        this.identifier = identifier;

    }

    public void run() {
        view.getCell()[row][column].setPlayer(identifier);
    }
}