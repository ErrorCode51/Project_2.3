//package RE2.View;
//
//import RE2.Model.TicTacToe;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Cell;
//import javafx.scene.control.Label;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class TicTacToeView extends Application {
//
//    private Cell[][] cell = new Cell[3][3];
//    private Label gameStatus = new Label();
//    private boolean ready = false;
//    private TicTacToe game;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        GridPane pane = new GridPane();
//        for (byte row = 0; row < 3; row++){
//            for (byte column = 0; column < 3; column++){
//                pane.add(cell[row][column] = new Cell(row, column), column, row);
//            }
//        }
//        BorderPane borderPane = new BorderPane();
//        borderPane.setCenter(pane);
//        borderPane.setBottom(gameStatus);
//
//        Scene scene = new Scene(borderPane, 350,350);
//        primaryStage.setTitle("Tic-tac-toe");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        primaryStage.setOnCloseRequest(e -> System.exit(0));
//    }
//
//}
