package RE2.Model;

public abstract class Stone {

    byte x;
    byte y;

    public Stone() {}

    public Stone(byte x, byte y) {

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

    abstract char getIdentifier();

}
