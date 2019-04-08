package RE2.View;

import RE2.Controller.userInputObserver.userInputSubject;
import RE2.Model.Board.OthelloBoard;
import RE2.Model.Game.Othello;
import RE2.Model.Stone.Stone;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OthelloView extends Application {

    private final Cell[][] cell = new Cell[8][8];
    private final Label satusLabel = new Label();
    private Othello game;

    @Override
    public void start(Stage primaryStage) {
        GridPane pane = new GridPane();
        for (byte row = 0; row < 8; row++) {
            for (byte column = 0; column < 8; column++) {
                pane.add(cell[row][column] = new Cell(row, column), column, row);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(satusLabel);

        Scene scene = new Scene(borderPane, 700, 700);
        primaryStage.setTitle("And what’s he then that says I play the villain?");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        game = new Othello(this);
        Thread thread = new Thread(game);
        thread.start();
    }

    public void setBoard(OthelloBoard board) {
        Stone[][] tiles = board.getTiles();
        for (byte row = 0; row < tiles.length; row++) {
            for (byte column = 0; column < tiles[row].length; column++) {
                // Platform.runLater(new userInputHelper(this, row, column, tiles[row][column].getIdentifier()));
            }
        }
    }

    public Cell[][] getCell() {
        return cell;
    }

    class Cell extends Pane {

        private final byte row;
        private final byte column;

        private char player;

        Cell(byte row, byte column) {

            this.row = row;
            this.column = column;

            this.setPrefSize(300, 300);
            setStyle("-fx-border-color: black");
            this.setOnMouseClicked(e -> handleMouseClick());

        }

        @Override
        public String toString() {
            return "Cell at [" + this.row + ", " + this.column + "]";
        }

        public void setPlayer(char player) {
            this.player = player;
            drawPlayer();
        }

        void drawPlayer() {

        }

        private void handleMouseClick() {
            userInputSubject.notify(row, column);
        }

    }
}



