import Controller.NetworkConfigurator;
import View.TicTacToeView;

import static javafx.application.Application.launch;

class Main {

    public static void main(String[] args) {
        NetworkConfigurator.loadPropFile();

        System.out.println(NetworkConfigurator.getPropertie("SERVER_IP"));
        System.out.println(NetworkConfigurator.getPropertie("SERVER_PORT"));
        System.out.println(NetworkConfigurator.getPropertie("PLAYER_NAME"));

        launch(TicTacToeView.class, args);
    }

}