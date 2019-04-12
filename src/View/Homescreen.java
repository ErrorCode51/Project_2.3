package View;

import Controller.ServerController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

        Button openTTT = new Button("Open Tictactoe");
        openTTT.setOnAction(event -> new TicTacToeView((byte) 1));

        Button othello = new Button("Open othello");
        othello.setOnAction(event -> new OthelloView((byte) 1));

        Button openServCon = new Button("Open server connection");
        openServCon.setOnAction(event -> ServerController.createPersistentServerController());

        Button closeconn = new Button("Close server connection");
        closeconn.setOnAction(event -> ServerController.getPersistentServerController().disconnect());

        Button Simonbtn1 = new Button("Open othello");
        Simonbtn1.setOnAction(event -> new OthelloView((byte) 1));

        Button Simonbtn2 = new Button("Open othello");
        Simonbtn2.setOnAction(event -> new OthelloView((byte) 1));

        Button Simonbtn3 = new Button("Open othello");
        Simonbtn3.setOnAction(event -> new OthelloView((byte) 1));

        Button Simonbtn4 = new Button("Open othello");
        Simonbtn4.setOnAction(event -> new OthelloView((byte) 1));

        Button ai = new Button("AI");

        Button human = new Button("Human");

        vBox.getChildren().addAll(name, nameInput, openTTT, othello,server,openServCon,closeconn, ai, human, Simonbtn1,Simonbtn2,Simonbtn3,Simonbtn4);

        othello.setOnAction(event -> new OthelloView((byte) 1));

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

}