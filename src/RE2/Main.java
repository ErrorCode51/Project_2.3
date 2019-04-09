package RE2;

import RE2.View.Homescreen;
import RE2.View.OthelloView;
import RE2.View.TicTacToeView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        // TicTacToe
        launch(Homescreen.class, args);

        // Othello
        //launch(OthelloView.class, args);
    }

}