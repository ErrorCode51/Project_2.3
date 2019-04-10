package View;

import Controller.userInputObserver.userInputSubject;
import Model.Board.Board;
import Model.Game.Game;
import Model.Stone.Stone;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public abstract class View extends Stage {

    protected final Label statusLabel = new Label();
    protected Game game;

    public void setBoard(Board board) {
        Stone tiles[][] = board.getTiles();
        for (byte row = 0; row < tiles.length; row++) {
            for (byte column = 0; column < tiles[row].length; column++) {
                Platform.runLater(new userInputHelper(this, row, column, tiles[row][column].getIdentifier()));
            }
        }
    }

    public abstract Cell[][] getCell();

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

        private void handleMouseClick() {
            userInputSubject.notify(row, column);
        }

        public void setPlayer(char player) {
            this.player = player;
            drawPlayer();
        }

        void drawPlayer() {
            if (this.player == 'X') {
                this.getChildren().addAll(drawCross()[0], drawCross()[1]);
            } else if (this.player == 'O') {
                getChildren().add(drawCircle());
            }
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

    }
}