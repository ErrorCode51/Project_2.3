package me.neireau.tictactest;

public class Person extends Player {

    // Todo: Refactor so players are not hardcoded
    public static final int ID = 1;
    public static final char SYMBOL = 'O';
    // Todo: Recieve name from server
    public String name = "Scorpion";

    public Person() {
        super(ID);
    }

    public boolean makeMove(Board board, int row, int column) {
        if (!board.squareIsEmpty(row, column)) {
            return false;
        }
        board.setSquare(row, column, Player.HUMAN);
        return true;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public char getNoughtOrCross() {
        return SYMBOL;
    }

    @Override
    public String toString() {
        return name;
    }

}