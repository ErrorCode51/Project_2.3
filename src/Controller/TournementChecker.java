package Controller;

import View.OthelloView;
import View.TicTacToeView;
import javafx.application.Platform;

public class TournementChecker {

    public void CheckTournement(String gametype){
        switch (gametype){
        case "Tic-tac-toe":
            Platform.runLater(() -> drawTicTacToe());
        break;
        case "Reversi":
            Platform.runLater(() -> drawReversi());
        }
    }

    private void drawTicTacToe(){
        System.out.println("Trying to draw a tictactoe view");

        new TicTacToeView();
    }

    private void drawReversi(){
        new OthelloView();
    }
}
