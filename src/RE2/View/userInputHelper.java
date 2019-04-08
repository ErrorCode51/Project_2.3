package RE2.View;

public class userInputHelper implements Runnable {

    TicTacToeView view;

    byte row;
    byte column;

    public userInputHelper(TicTacToeView view, byte row, byte column) {

        this.view = view;

        this.row = row;
        this.column = column;
        System.out.println("UIH created");

    }

    public void run() {
        System.out.println("RUN!");
        this.view.getCell()[this.row][this.column].drawPlayer();
    }
}