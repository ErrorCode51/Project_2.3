package model.gameRules;

import model.playingField.playingField;

public class ticTacToeRules implements gameRules {

    public byte[] getValidMoves(playingField field) {
        return null;
    }


    public byte getGameStatus(playingField field) {
        byte tiles[][] = field.getTiles();
        for (int i = 0; i < 3; i++) {
            // check the rows to see if there are three of the same symbol in a line
            if (tiles[i][0] == tiles[i][1] && tiles[i][1] == tiles[i][2] && tiles[i][0] != 0) {
                return tiles[i][0];
            }
            // check the collums to see if there are three of the same symbols in a line
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
        return 0;
    }
}
