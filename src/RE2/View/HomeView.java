package RE2.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeView extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Home");

        VBox vBox = new VBox(0);
        Scene scene = new Scene(vBox, 350,150);

        Label name = new Label("Name: ");

        TextField nameInput = new TextField();

        Button openTTT = new Button("Open Tictactoe");
        openTTT.setOnAction(event -> new TicTacToeView());

        Button ai = new Button("AI");

        Button human = new Button("Human");

        vBox.getChildren().addAll(name, nameInput, openTTT, ai, human);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

}
