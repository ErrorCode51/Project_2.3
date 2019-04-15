import Controller.NetworkConfigurator;

import Model.Board.OthelloBoard;
import Model.Player.AlphaBetaReworkAI;
import Model.Player.ArtificialOthello;
//import Model.Player.ReworkAI;
import Model.Rules.OthelloRules;
import Model.Stone.OthelloStone;

import Controller.ServerController;
import View.Homescreen;
import java.util.ArrayList;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        NetworkConfigurator.loadPropFile();

        launch(Homescreen.class, args);
    }
}