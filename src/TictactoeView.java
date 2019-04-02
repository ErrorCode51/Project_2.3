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

    public boolean isBoardFull(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (cell[i][j].getPlayer() == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasWon(char player){
        for (int i = 0; i < 3; i++){
            if (cell[i][0].getPlayer() == player && cell[i][1].getPlayer() == player && cell[i][2].getPlayer() == player){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){
            if (cell[0][i].getPlayer() == player && cell[1][i].getPlayer() == player && cell[2][i].getPlayer() == player){
                return true;
            }
        }

        if (cell[0][0].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][2].getPlayer() == player){
            return true;
        }

        if (cell[0][2].getPlayer() == player && cell[1][1].getPlayer() == player && cell[2][0].getPlayer() == player){
            return true;
        }
        return false;
    }

    public class Cell extends Pane {

        private int row;
        private int column;

        private char player = ' ';

        public Cell(int row, int column) {
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
        }
    }
}



