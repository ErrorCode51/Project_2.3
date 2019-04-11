package View;

import Model.Game.Othello;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OthelloView extends View {

    private final Cell[][] CELL = new Cell[8][8];

    public OthelloView() {
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        for (byte row = 0; row < 8; row++) {
            for (byte column = 0; column < 8; column++) {
                pane.add(CELL[row][column] = new Cell(row, column), column, row);
            }
        }
        CELL[3][3].setPlayer('W');
        CELL[3][4].setPlayer('B');
        CELL[4][3].setPlayer('B');
        CELL[4][4].setPlayer('W');
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusLabel);

        Scene scene = new Scene(borderPane, 700, 700);
        stage.setTitle("And what’s he then that says I play the villain?");
        stage.setScene(scene);
        stage.show();
        //stage.setOnCloseRequest(e -> System.exit(0));

        Othello game = new Othello(this, false);
        Thread thread = new Thread(game);
        thread.start();
    }

    @Override
    public Cell[][] getCell() {
        return CELL;
    }


}



