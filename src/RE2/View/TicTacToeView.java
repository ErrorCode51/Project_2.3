package RE2.View;

import RE2.Model.TicTacToe;
import RE2.Model.TicTacToeStone;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TicTacToeView extends Application {

    private Cell[][] cell = new Cell[3][3];
    private Label gameStatus = new Label();
    private TicTacToe game;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        for (byte row = 0; row < 3; row++) {
            for (byte column = 0; column < 3; column++) {
                pane.add(cell[row][column] = new Cell(row, column), column, row);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(gameStatus);

        Scene scene = new Scene(borderPane, 350, 350);
        primaryStage.setTitle("Tic-tac-toe");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

        TicTacToe game = new TicTacToe();
        this.game = game;
        game.run();
    }

    public class Cell extends Pane {

        private byte row;
        private byte column;


        public Cell(byte row, byte column) {

            this.row = row;
            this.column = column;
            this.setPrefSize(300, 300);
            setStyle("-fx-border-color: black");
            this.setOnMouseClicked(e -> handleMouseClick());

        }

        protected boolean placeStone() {
            TicTacToeStone stone = new TicTacToeStone(row, column, game.getCurrentPlayer());
            return game.getBoard().set(stone);
        }

        protected void drawPlayer() {
            if (game.getCurrentPlayer() == 'X') {
                Line line1 = new Line(10, 10,
                        this.getWidth() - 10, this.getHeight() - 10);
                line1.endXProperty().bind(this.widthProperty().subtract(10));
                line1.endYProperty().bind(this.heightProperty().subtract(10));
                Line line2 = new Line(10, this.getHeight() - 10,
                        this.getWidth() - 10, 10);
                line2.startYProperty().bind(
                        this.heightProperty().subtract(10));
                line2.endXProperty().bind(this.widthProperty().subtract(10));

                this.getChildren().addAll(line1, line2);
            } else if (game.getCurrentPlayer() == 'O') {
                Ellipse ellipse = new Ellipse(this.getWidth() / 2,
                        this.getHeight() / 2, this.getWidth() / 2 - 10,
                        this.getHeight() / 2 - 10);
                ellipse.centerXProperty().bind(
                        this.widthProperty().divide(2));
                ellipse.centerYProperty().bind(
                        this.heightProperty().divide(2));
                ellipse.radiusXProperty().bind(
                        this.widthProperty().divide(2).subtract(10));
                ellipse.radiusYProperty().bind(
                        this.heightProperty().divide(2).subtract(10));
                ellipse.setStroke(Color.BLACK);
                ellipse.setFill(Color.WHITE);

                getChildren().add(ellipse);
            }
        }

        private void handleMouseClick() {
            //Todo: Clean this.
            if (game.gameOver(game.getBoard()) == 'N') {
                if (placeStone()) {
                    drawPlayer();
                    if (game.gameOver(game.getBoard()) == 'N') {
                        game.changePlayer();
                    } else {
                        if (game.gameOver(game.getBoard()) == 'X' || game.gameOver(game.getBoard()) == 'O') {
                            gameStatus.setText("Winner is " + game.gameOver(game.getBoard()));
                        } else {
                            gameStatus.setText("Draw");
                        }
                    }
                }
            }
        }
    }
}



