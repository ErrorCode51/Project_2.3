package Model.Stone;

public abstract class Stone {

    byte x;
    byte y;

    Stone() {}

    Stone(byte x, byte y) {

        this.x = x;
        this.y = y;

    }

    public byte getX() {
        return x;
    }

    public byte getY() {
        return y;
    }

    public Stone getStone() {
        return this;
    }

    public abstract char getIdentifier();

    public abstract void turnOver();

}
