import Controller.NetworkConfigurator;
import View.Homescreen;
import View.TicTacToeView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        NetworkConfigurator.loadPropFile();

        launch(Homescreen.class, args);
    }
}