package me.neireau.tictactest;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener {
    // To test in console
    public static final boolean DEBUG = false;

    // Future-proofing to scale up board
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    // AWT/Swing because Java FX is somehow broken on my machine
    // Todo: Fix Java FX
    public static final String WINDOW_TITLE = "Noughts and Crosses";
    public static final Dimension WINDOW_SIZE = new Dimension(400, 400);

    public JButton squares[][] = new JButton[ROWS][COLUMNS];
    public JButton clickedSquare;

    public Color backgroundColour = new Color(238, 238, 238, 255); // Light grey
    public Color foregroundColour = new Color(0, 0, 0, 255);       // Black
    public Color borderColour = new Color(255, 255, 255, 255);     // White

    public Board board;
    public AI ai = new AI();
    public Person person = new Person();
    public int currentPlayer = Player.HUMAN;

    public static void main(String[] args) {
        new Main().run();
    }

    public void run() {
        JFrame frame = new JFrame();
        frame.setTitle(WINDOW_TITLE);
        frame.setSize(WINDOW_SIZE);
        frame.setLayout(new GridLayout(ROWS, COLUMNS));

        // Evert would be proud
        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; column++) {
                squares[row][column] = new JButton("");
                squares[row][column].addActionListener(this);
                squares[row][column].setBackground(backgroundColour);
                squares[row][column].setFocusPainted(false);
                squares[row][column].setBorder(new LineBorder(borderColour));
                squares[row][column].setForeground(foregroundColour);
                frame.add(squares[row][column]);
            }
        }

        frame.setVisible(true);

        board = new Board(ai, person);
    }

    public void actionPerformed(ActionEvent e) {
        clickedSquare = (JButton) e.getSource();

        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; column++) {
                if (clickedSquare == squares[row][column]) {
                    Player player = (currentPlayer == Player.HUMAN) ? person : ai;
                    if (currentPlayer == Player.HUMAN) {
                        if (!((Person) player).makeMove(board, row, column)) {
                            this.showMessage("Invalid move.");
                        } else {
                            clickedSquare.setText(String.valueOf(player.getNoughtOrCross()));
                            this.changePlayer();
                            TemporaryMove move = ai.makeMove(board);
                            squares[move.row][move.column].setText(String.valueOf(ai.getNoughtOrCross()));

                            if (DEBUG) {
                                board.printToConsole();
                            }

                            this.changePlayer();
                        }
                    }
                }
            }
        }


        int winner = board.gameOver();
        if (winner == Board.DRAW) {
            this.showMessage("Draw!");
            this.disableSquares();
        } else if (winner != Board.EMPTY) {
            Player player = (winner == Player.AI) ? ai : person;
            this.showMessage(player + " wins!");
            this.disableSquares();
        }
    }

    // Lock buttons when game is complete
    // Todo: implement a restart button
    public void disableSquares() {
        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; column++) {
                squares[row][column].setEnabled(false);
            }
        }
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 1) ? 0 : 1;
    }

    public void showMessage(String message) {
        JOptionPane.showConfirmDialog(null, message, WINDOW_TITLE, JOptionPane.OK_CANCEL_OPTION);
    }
}
