package Model.Stone;

public abstract class Stone {

    byte x;
    byte y;

    Stone() {}

    Stone(byte x, byte y) {

        this.x = x;
        this.y = y;

    }

    Stone(Stone stone) {

        this.x = stone.getX();
        this.y = stone.getY();

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

    public abstract boolean equals(char identifier);

}
