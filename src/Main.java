import Controller.NetworkConfigurator;
import Controller.ServerController;
import View.Homescreen;
import View.TicTacToeView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        NetworkConfigurator.loadPropFile();

        // FOR TESTING todo: REMOVE WHEN DONE TESTING FFS
        ServerController.createPersistentServerController();


        launch(Homescreen.class, args);
    }
}