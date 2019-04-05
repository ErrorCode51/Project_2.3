package RE2.View;

import RE2.Model.Container.TemporaryPlacement;
import RE2.Model.Game.TicTacToe;
import RE2.Model.Player.LocalPlayer;
import RE2.Model.Player.Player;
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

    private final Cell[][] cell = new Cell[3][3];
    private final Label gameStatus = new Label();
    private TicTacToe game;
    // Todo: fix final bug; AI cannot play without user input.
    private boolean humanInteraction = false;

    @Override
    public void start(Stage primaryStage) {
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

        // for (Player player : game.players) {
        //     if (player.getClass() == LocalPlayer.class) {
        //         humanInteraction = true;
        //     }
        // }
    }

    class Cell extends Pane {

        private final byte row;
        private final byte column;

        Cell(byte row, byte column) {

            this.row = row;
            this.column = column;
            this.setPrefSize(300, 300);
            setStyle("-fx-border-color: black");
            this.setOnMouseClicked(e -> handleMouseClick());

        }

        private Ellipse drawCircle() {
            Ellipse circle = new Ellipse(this.getWidth() / 2,
                    this.getHeight() / 2, this.getWidth() / 2 - 10,
                    this.getHeight() / 2 - 10);
            circle.centerXProperty().bind(
                    this.widthProperty().divide(2));
            circle.centerYProperty().bind(
                    this.heightProperty().divide(2));
            circle.radiusXProperty().bind(
                    this.widthProperty().divide(2).subtract(10));
            circle.radiusYProperty().bind(
                    this.heightProperty().divide(2).subtract(10));
            circle.setStroke(Color.BLACK);
            circle.setFill(Color.WHITE);
            return circle;
        }

        private Line[] drawCross() {
            Line[] cross = new Line[2];
            Line line1 = new Line(10, 10,
                    this.getWidth() - 10, this.getHeight() - 10);
            line1.endXProperty().bind(this.widthProperty().subtract(10));
            line1.endYProperty().bind(this.heightProperty().subtract(10));
            cross[0] = line1;
            Line line2 = new Line(10, this.getHeight() - 10,
                    this.getWidth() - 10, 10);
            line2.startYProperty().bind(
                    this.heightProperty().subtract(10));
            line2.endXProperty().bind(this.widthProperty().subtract(10));
            cross[1] = line2;
            return cross;
        }

        void drawPlayer() {
            if (game.getCurrentPlayer() == 'X') {
                this.getChildren().addAll(drawCross()[0], drawCross()[1]);
            } else if (game.getCurrentPlayer() == 'O') {
                getChildren().add(drawCircle());
            }
        }

        void drawPlayer(byte row, byte column) {
            if (game.getCurrentPlayer() == 'X') {
                cell[row][column].getChildren().addAll(drawCross()[0], drawCross()[1]);
            } else if (game.getCurrentPlayer() == 'O') {
                cell[row][column].getChildren().add(drawCircle());
            }
        }

        private void handleMouseClick() {
            if (game.gameOver(game.getBoard()) == 'N') {
                for (Player player : game.players) {
                    if (game.getCurrentPlayer() == player.getIdentifier()) {
                        if (player.getClass() == LocalPlayer.class) {
                            if (player.placeStone(game.getBoard(), row, column, player.getIdentifier())) {
                                player.placeStone(game.getBoard(), row, column, player.getIdentifier());
                                drawPlayer();
                                boardState();
                            } else {
                                System.out.println("Invalid placement!");
                            }
                        } else {
                            TemporaryPlacement test = player.placeStone(game.getBoard());
                            drawPlayer((byte) test.row, (byte) test.column);
                            boardState();
                        }
                    }
                }
            }
        }

        private void boardState() {
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



