
import View.Homescreen;
import View.OthelloView;
import View.TicTacToeView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        // TicTacToe
        launch(Homescreen.class, args);

        // Othello
        //launch(OthelloView.class, args);
    }

}