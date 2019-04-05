package RE2;

import RE2.Model.Board;
import RE2.Model.TicTacToe;
import RE2.Model.TicTacToeStone;
import RE2.View.TicTacToeView;

import static javafx.application.Application.launch;

public class Main {

    public static void main(String[] args) {
        launch(TicTacToeView.class, args);
        TicTacToe game = new TicTacToe();
        game.run();
    }

}