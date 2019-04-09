package RE2.View;

import RE2.Model.Game.Othello;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OthelloView extends View {

    private final Cell[][] CELL = new Cell[8][8];

    @Override
    public Cell[][] getCell() {
        return CELL;
    }


    public OthelloView() {
        Stage primaryStage = new Stage();
        GridPane pane = new GridPane();
        for (byte row = 0; row < 8; row++) {
            for (byte column = 0; column < 8; column++) {
                pane.add(CELL[row][column] = new Cell(row, column), column, row);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(statusLabel);

        Scene scene = new Scene(borderPane, 700, 700);
        primaryStage.setTitle("And what’s he then that says I play the villain?");
        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setOnCloseRequest(e -> System.exit(0));

        game = new Othello(this);
        Thread thread = new Thread(game);
        thread.start();
    }



}



