package model.playingField;

import model.gameRules.gameRules;
import model.player.player;

import java.util.Arrays;

// this class is a playing field for one of the board games
// this class contains an arraylist of tiles
public class playingField {
    private int size;                   // contains the length of the sides of the playing field
    private byte tiles[][];      // contains all the tiles (squares) on the playing field, the content is represented by a number

    public playingField(int size) {
        this.size = size;
        tiles = new byte[this.size][this.size];
    }


    public int getSize() {
        return size;
    }


    public void setTile(byte x, byte y, byte playerNr) {
        if (x <= this.size && y <= this.size){
            if (this.tiles[x][y] == 0) {
                if (playerNr == 1 || playerNr == 2) {
                    this.tiles[x][y] = playerNr;
                }
                else {

                }
            }
        }
    }


    public final byte[][] getTiles() {
        return this.tiles.clone();
    }
}