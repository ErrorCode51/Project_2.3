package RE2.View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeView extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Home");

        VBox vBox = new VBox(0);
        Scene scene = new Scene(vBox, 350,150);

        Button openTTT = new Button("Open Tictactoe");
        openTTT.setOnAction(event -> new TicTacToeView());

        vBox.getChildren().addAll(openTTT);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

}
