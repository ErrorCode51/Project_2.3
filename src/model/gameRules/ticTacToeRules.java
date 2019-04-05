package model.gameRules;

import model.playingField.playingField;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ticTacToeRules implements gameRules {

    public byte[][] getValidMoves(playingField field) {
        byte tiles[][] = field.getTiles();
        ArrayList<byte[]> moves = new ArrayList<byte[]>();
        for (byte i = 0; i < 3; i++){
            for (byte j = 0; j < 3; j++){
                if (tiles[i][j] == 0){
                    moves.add(new byte[]{i, j});
                }
            }
        }
        if (moves.isEmpty()) {
            return null;
        }
        byte[][] movesArray = new byte[moves.size()][];
        movesArray = moves.toArray(movesArray);
        return movesArray;
    }

    public boolean boardIsFull(playingField field){
        byte tiles[][] = field.getTiles();
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (tiles[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public byte getGameStatus(playingField field) {
        byte tiles[][] = field.getTiles();
        for (int i = 0; i < 3; i++) {
            // check the rows to see if there are three of the same symbol in a line
            if (tiles[i][0] == tiles[i][1] && tiles[i][1] == tiles[i][2] && tiles[i][0] != 0) {
                return tiles[i][0];
            }
            // check the columns to see if there are three of the same symbols in a line
            if (tiles[0][i] == tiles[1][i] && tiles[1][i] == tiles[2][i] && tiles[0][i] != 0){
                return tiles[0][i];
            }
        }

        // check to see if there are three of the same symbols in a vertical line
        if (tiles[0][0] == tiles[1][1] && tiles[1][1] == tiles[2][2] && tiles[1][1] != 0) {
            return tiles[1][1];
        }
        if (tiles[0][2] == tiles[1][1] && tiles[1][1] == tiles[2][0] && tiles[1][1] != 0) {
            return tiles[1][1];
        }
        // no winner was found, returning zero
        return 0;
    }

    public boolean gameFinished(playingField field){
        if (boardIsFull(field) || getGameStatus(field) != 0) {
            return true;
        }
        return false;
    }

    public byte getNextPlayer(byte lastPlayerID, playingField field) {
        if (lastPlayerID == 1) {
            return 2;
        } else if (lastPlayerID == 2) {
            return 1;
        }
        return 0;
    }
}
