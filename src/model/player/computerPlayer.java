package model.player;

import model.gameRules.gameRules;
import model.playingField.playingField;

import java.util.ArrayList;

public class computerPlayer implements player {

    private final byte ID;

    public computerPlayer(byte ID) {
        this.ID = ID;
    }


    // Todo: Receive name from server
    public String name = "Sub Zero";

    @Override
    public String toString() {
        return name;
    }

    public byte getID() {
        return ID;
    }

    // What is this?
    public boolean isReady() {
        return false;
    }

    public byte[] takeTurn(playingField field, gameRules rules) {
        // Counter to count the depth of recursion
        int counter = 0;
        // Byte[] to return
        byte[] turn = new byte[2];
        // Recursively find the best possible move
        TemporaryMove move = this.getBestMove(field, rules, ID, counter);
        // Adding and casting the coordinates of the best found move
        turn[0] = (byte) move.row;
        turn[1] = (byte) move.column;
        // Fin
        return turn;
    }

    TemporaryMove getBestMove(playingField field, gameRules rules, byte player, int counter) {
        // Not implemented as of now, getGameStatus() should return:
        // Draw = 0 // not true, 0 means that nobody has won and the game may not have been finished
        // Computer win = 1
        // Human win = 2
        // Network win = 3
        // Todo: Implement method to receive game result
        if (rules.gameFinished(field)) {
            byte winner = rules.getGameStatus(field);
            System.out.println("Winner: " + winner);
            if (winner == this.ID) {
                return new TemporaryMove(10);
            } else if (winner >= 1) {
                return new TemporaryMove(-10);
            } else {
                return new TemporaryMove(0);
            }
        }



        ArrayList<TemporaryMove> moves = new ArrayList<>();
        counter += 1;
        for (byte row = 0; row < field.getSize(); ++row) {
            for (byte column = 0; column < field.getSize(); column++) {
                if (field.tileIsEmpty(row, column)) {
                    System.out.println("found an empty spot");
                    // Create a temporary move
                    TemporaryMove move = new TemporaryMove(row, column);
                    // Fill temporary move
                    field.setTile(row, column, player);
                    // Evaluate temporary move
                    if (player == ID) {
                        // If player is AI score positively
                        // Todo: Implement a method to receive ID of opponent
                        move.score = getBestMove(field, rules, (byte) 1, counter).score - counter;
                    } else {
                        // Else score negatively
                        move.score = counter + getBestMove(field, rules, ID, counter).score;
                    }
                    // Remove temporary move
                    field.zeroTile(row, column);
                    // Add temporary move to list
                    moves.add(move);
                }
            }
        }
        System.out.println(counter);

        int bestMove = 0;
        if (player == ID) {
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
