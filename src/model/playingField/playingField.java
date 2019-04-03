package model.playingField;

import model.gameRules.gameRules;
import model.player.player;

import java.util.Arrays;

// this class is a playing field for one of the board games
// this class contains an arraylist of tiles
public class playingField {
    private int size;                   // contains the length of the sides of the playing field
    private byte tiles[];      // contains all the tiles (squares) on the playing field, the content is represented by a number

    public playingField(int size) {
        this.size = size;
        tiles = new byte[this.size * this.size];
    }


    public int getSize() {
        return size;
    }


    public void setTile(byte x, byte y, byte playerNr) {
        int position = (((y-1)*8) + (x-1));
        if (tiles[position] == 0) {
            tiles[position] = playerNr;
        }
    }

    public boolean tileIsEmplty(byte row, byte column) {
        return (this.tiles[row][column] == 0);
    }


    public byte[][] to2dArray() {
        if (this.tiles != null) {
            byte[][] arrayke = new byte[this.size][this.size];
            for (byte i = 0; i < this.size; i++) {
                arrayke[i] = Arrays.copyOfRange(this.tiles, (i * 8), ((i * 8) + 8));
            }
            return arrayke;
        }
        else {
            return null;
        }
    }
}