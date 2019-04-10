package View;

import Model.Game.TicTacToe;
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


    public TicTacToeView() {
        Stage stage = new Stage();
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
        stage.setTitle("Noughts and Crosses");
        stage.setScene(scene);
        stage.show();
        //stage.setOnCloseRequest(e -> System.exit(0));

        game = new TicTacToe(this, false);
        Thread thread = new Thread(game);
        thread.start();
    }

}



