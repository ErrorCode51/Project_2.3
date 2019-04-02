package me.neireau.tictactest;

import java.util.ArrayList;

public class AI extends Player {

    // Todo: Refactor so players are not hardcoded
    public static final int ID = 0;
    public static final char SYMBOL = 'X';
    // Todo: Recieve name from server
    public String name = "Sub Zero";

    public AI() {
        super(ID);
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

    public TemporaryMove makeMove(Board board) {
        TemporaryMove move = this.getBestMove(board, Player.AI);
        board.makeMove(move.row, move.column, Player.AI);
        return move;
    }

    TemporaryMove getBestMove(Board board, int player) {
        int winner = board.gameOver();
        if (winner == Board.AI) {
            return new TemporaryMove(10);
        } else if (winner == Board.HUMAN) {
            return new TemporaryMove(-10);
        } else if (winner == Board.DRAW) {
            return new TemporaryMove(0);
        }

        ArrayList<TemporaryMove> moves = new ArrayList<>();

        for (int row = 0; row < board.getSize(); ++row) {
            for (int column = 0; column < board.getSize(); column++) {
                if (board.squareIsEmpty(row, column)) {
                    // Create a temporary move
                    TemporaryMove move = new TemporaryMove(row, column);
                    // Fill temporary move
                    board.setSquare(row, column, player);
                    // Evaluate temporary move
                    if (player == Player.AI) {
                        // If player is AI score positively
                        move.score = getBestMove(board, Player.HUMAN).score;
                    } else {
                        // Else score negatively
                        move.score = getBestMove(board, Player.AI).score;
                    }
                    // Remove temporary move
                    board.setSquare(row, column, Board.EMPTY);
                    // Add temporary move to list
                    moves.add(move);
                }
            }
        }

        int bestMove = 0;
        if (player == Player.AI) {
            int bestScore = -10;
            for (int index = 0; index < moves.size(); ++index) {
                if (moves.get(index).score > bestScore) {
                    bestMove = index;
                    bestScore = moves.get(index).score;
                }
            }
        } else {
            int bestScore = 10;
            for (int index = 0; index < moves.size(); ++index) {
                if (moves.get(index).score < bestScore) {
                    bestMove = index;
                    bestScore = moves.get(index).score;
                }
            }
        }

        return moves.get(bestMove);
    }

}