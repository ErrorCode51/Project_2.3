package view;

import view.TictactoeView;

public class setPlayerHelper implements Runnable {
    byte x;
    byte y;
    byte playerNr;
    TictactoeView view;

    public setPlayerHelper(TictactoeView view, byte x, byte y, byte playerNr) {
        this.view = view;
        this.x = x;
        this.y = y;
        this.playerNr = playerNr;
    }


    public void run() {
        this.view.getCell()[this.x][this.y].setPlayer(this.playerNr);
    }
}
