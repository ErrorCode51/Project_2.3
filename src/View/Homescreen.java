package View;

import Controller.ServerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Homescreen extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Home");

        VBox vBox = new VBox(0);
        Scene scene = new Scene(vBox, 500, 500);

        Label name = new Label("Name: ");

        Label server = new Label("Server commands: ");

        TextField nameInput = new TextField();

        Label tttLabel = new Label("Tic-Tac-Toe:");
        HBox tttHBox = new HBox();
        Button tttBtn1 = new Button("Human vs AI");
        tttBtn1.setOnAction(event -> new TicTacToeView((byte) 0));
        Button tttBtn2 = new Button("Human vs Human");
        tttBtn2.setOnAction(event -> new TicTacToeView((byte) 1));
        Button tttBtn3 = new Button("AI vs AI");
        tttBtn3.setOnAction(event -> new TicTacToeView((byte) 2));
        tttHBox.getChildren().addAll(tttBtn1, tttBtn2, tttBtn3);

        Label othLabel = new Label("Othello:");
        HBox othHBox = new HBox();
        Button othBtn1 = new Button("Human vs AI");
        othBtn1.setOnAction(event -> new OthelloView((byte) 0));
        Button othBtn2 = new Button("Human vs Human");
        othBtn2.setOnAction(event -> new OthelloView((byte) 1));
        Button othBtn3 = new Button("AI vs AI");
        othBtn3.setOnAction(event -> new OthelloView((byte) 2));
        othHBox.getChildren().addAll(othBtn1, othBtn2, othBtn3);

        Button openServCon = new Button("Open server connection");
        openServCon.setOnAction(event -> ServerController.createPersistentServerController());

        Button closeconn = new Button("Close server connection");
        closeconn.setOnAction(event -> ServerController.getPersistentServerController().disconnect());

        vBox.getChildren().addAll(name, nameInput, tttLabel, tttHBox, othLabel, othHBox, server, openServCon, closeconn);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

}