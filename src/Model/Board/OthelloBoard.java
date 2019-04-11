package Model.Board;

import Model.Stone.Stone;

public class OthelloBoard extends Board {

    byte scoreBlack = 0;
    byte scoreWhite = 0;

    public OthelloBoard() {

        super((byte) 8);

    }

    @Override
    public boolean set(Stone stone) {
        //if (get(stone.getX(), stone.getY()).getClass() != Empty.class) {
        if (!isEmpty(stone.getX(), stone.getY())) {
            return false;
        } else {
            tiles[stone.getX()][stone.getY()] = stone;
            scoreBoard();
            return true;
        }
    }

    private void scoreBoard() {
        scoreBlack = 0;
        scoreWhite = 0;
        for (byte row = 0; row < size; row++) {
            for (byte column = 0; column < size; column++) {
                if (!isEmpty(row, column)) {
                    get(row, column).getIdentifier();
                    switch (get(row, column).getIdentifier()) {
                        case 'B':
                            scoreBlack++;
                            break;
                        case 'W':
                            scoreWhite++;
                            break;
                    }
                }
            }
        }
    }

    public byte getScoreBlack() {
        return scoreBlack;
    }

    public byte getScoreWhite() {
        return scoreWhite;
    }

    public byte getScoreByIdentifier(char identifier) {
        byte score = 0;
        switch (identifier) {
            case 'B':
                score = getScoreBlack();
                break;
            case 'W':
                score = getScoreWhite();
                break;
        }
        return score;
    }

}
