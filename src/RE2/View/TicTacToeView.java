package RE2.View;

import RE2.Model.Game.TicTacToe;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class TicTacToeView extends View {

    private final Cell[][] CELL = new Cell[3][3];

    @Override
    public Cell[][] getCell() {
        return CELL;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        for (byte row = 0; row < 3; row++) {
            for (byte column = 0; column < 3; column++) {
                pane.add(CELL[row][column] = new Cell(row, column), column, row);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusLabel);

        Scene scene = new Scene(borderPane, 350, 350);
        primaryStage.setTitle("Noughts and Crosses");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        game = new TicTacToe(this);
        Thread thread = new Thread(game);
        thread.start();
    }

}



