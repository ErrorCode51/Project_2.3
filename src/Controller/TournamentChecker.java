package Controller;

import View.OthelloView;
import View.TicTacToeView;
import javafx.application.Platform;

public class TournamentChecker {
// checks for the gametype to handle incoming tournaments, draws the view from the game that is chosen to do the tournament with
    public void CheckTournament(String gametype){
        switch (gametype){
        case "Tic-tac-toe":
            Platform.runLater(() -> new TicTacToeView());
        break;
        case "Reversi":
            Platform.runLater(() -> new OthelloView());
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
