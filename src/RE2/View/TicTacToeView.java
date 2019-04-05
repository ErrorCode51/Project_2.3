package RE2.View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


/*import model.playingField.playingField;
import controller.playerInputObserver.playerInputSubject;
import model.game;
import view.setPlayerHelper;*/

public class TicTacToeView extends Application {

    private Cell[][] cell = new Cell[3][3];
    private Label gameStatus = new Label();
    private boolean ready = false;


    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        for (byte i = 0; i < 3; i++){
            for (byte j = 0; j < 3; j++){
                pane.add(cell[i][j] = new Cell(i,j), j,i);
            }
        }
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(pane);
        borderPane.setBottom(gameStatus);

        Scene scene = new Scene(borderPane, 350,350);
        primaryStage.setTitle("Tic-tac-toe");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> System.exit(0));

    }

    public class Cell extends Pane {

        private byte row;
        private byte column;

        private char player = 0;

        public Cell(byte row, byte column) {
            this.row = row;
            this.column = column;
            this.setPrefSize(300, 300);
            setStyle("-fx-border-color: black");
            this.setOnMouseClicked(e -> handleMouseClick());
        }

        public char getPlayer() {
            return player;
        }

        public void setPlayer(char player) {
            this.player = player;
            drawPlayer();
        }

        protected void drawPlayer() {
            if (player == 'X') {
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
            }
            else if (player == 'O') {
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
            setPlayer('X');
        }
    }
}



