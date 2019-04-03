package view;

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

import model.playingField.playingField;

public class TictactoeView extends Application {

    private Cell[][] cell = new Cell[3][3];
    private Label gameStatus = new Label();
    private char currentPlayer = 'X';

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane pane = new GridPane();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
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

    }


    public void setPlayingfield(playingField field) {
        byte tiles[][] = field.getTiles();
        for (byte x = 0; x < tiles.length; x++) {
            for (byte y = 0; y < tiles[x].length; y++) {
                cell[x][y].setPlayer(tiles[x][y]);
            }
        }
    }


    public class Cell extends Pane {

        private int row;
        private int column;

        private byte player = 0;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
            this.setPrefSize(300, 300);
            setStyle("-fx-border-color: black");
            //this.setOnMouseClicked(e -> handleMouseClick());
        }

        public byte getPlayer() {
            return player;
        }

        public void setPlayer(byte player) {
            this.player = player;
            drawPlayer();
        }

        protected void drawPlayer() {
            if (player == 1) {
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
            else if (player == 2) {
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

       /* private void handleMouseClick() {
            if (player == ' ') {
                setPlayer(currentPlayer);

                if (hasWon(currentPlayer)){
                    gameStatus.setText(currentPlayer + " has won!");
                    currentPlayer = ' ';
                }else if (isBoardFull()){
                    gameStatus.setText("It is a draw.");
                    currentPlayer = ' ';
                }else if (currentPlayer == 'X'){
                    currentPlayer = 'O';
                    gameStatus.setText("It is " + currentPlayer + "'s turn.");
                }else if (currentPlayer == 'O') {
                    currentPlayer = 'X';
                    gameStatus.setText("It is " + currentPlayer + "'s turn.");
                }
            }
        }*/
    }
}


