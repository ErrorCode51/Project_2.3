package me.neireau.tictactest;

public class Board {

    public static final int EMPTY = -1;
    public static final int AI = 0;
    public static final int HUMAN = 1;

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    public static final int DRAW = -2;

    public int[][] squares = new int[ROWS][COLUMNS];

    public AI ai;
    public Person person;

    public Board(AI ai, Person person) {
        this.ai = ai;
        this.person = person;

        this.resetBoard();
    }

    // Getters
    public int getSize() {
        return ROWS;
    }

    public int getSquare(int row, int column) {
        return this.squares[row][column];
    }

    // Setters
    public void setSquare(int row, int column, int player) {
        this.squares[row][column] = player;
    }

    public void resetBoard() {
        for (int row = 0; row < ROWS; ++row)
            for (int column = 0; column < COLUMNS; ++column)
                this.squares[row][column] = EMPTY;
    }

    public boolean squareIsEmpty(int row, int column) {
        return (this.squares[row][column] == -1);
    }

    public boolean makeMove(int row, int column, int player) {
        if (this.squares[row][column] != -1) {
            return false;
        }

        this.squares[row][column] = player;

        return true;
    }

    public void printToConsole() {
        System.out.println("Board: ");
        for (int row = 0; row < ROWS; ++row) {
            for (int column = 0; column < COLUMNS; column++) {
                System.out.print(this.squares[row][column] + " ");
            }

            System.out.println();
        }
    }

    public int gameOver() {
        // Horizontal
        for (int row = 0; row < ROWS; ++row) {
            if (this.getSquare(row, 0) != -1 && this.getSquare(row, 1) != -1 && this.getSquare(row, 0) == this.getSquare(row, 1) && this.getSquare(row, 1) == this.getSquare(row, 2)) {
                if (this.getSquare(row, 0) == Board.AI) {
                    return Board.AI;
                } else {
                    return Board.HUMAN;
                }
            }
        }

        // Vertical
        for (int column = 0; column < ROWS; ++column) {
            if (this.getSquare(0, column) != -1 && this.getSquare(1, column) != -1 && this.getSquare(0, column) == this.getSquare(1, column) && this.getSquare(1, column) == this.getSquare(2, column)) {
                if (this.getSquare(0, column) == Board.AI) {
                    return Board.AI;
                } else {
                    return Board.HUMAN;
                }
            }
        }

        // Diagonal
        if (this.getSquare(0, 0) != -1 && this.getSquare(1, 1) != -1 && this.getSquare(0, 0) == this.getSquare(1, 1) && this.getSquare(1, 1) == this.getSquare(2, 2)) {
            if (this.getSquare(0, 0) == Board.AI) {
                return Board.AI;
            } else {
                return Board.HUMAN;
            }
        }

        if (this.getSquare(0, 2) != -1 && this.getSquare(1, 1) != -1 && this.getSquare(0, 2) == this.getSquare(1, 1) && this.getSquare(1, 1) == this.getSquare(2, 0)) {
            if (this.getSquare(0, 2) == Board.AI) {
                return Board.AI;
            } else {
                return Board.HUMAN;
            }
        }

        boolean draw = true;

        // Draw
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLUMNS; j++) {
                // A square is empty, can't be a draw
                if (this.squares[i][j] == -1) {
                    draw = false;
                    break;
                }
            }

            if (draw == false)
                break;
        }

        return (draw == true) ? DRAW : EMPTY;
    }
}