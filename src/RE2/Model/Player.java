package RE2.Model;

public abstract class Player {

    public static final byte AI = 0;
    public static final byte LOCAL = 1;
    public static final byte NETWORK = 2;

    public byte currentPlayer;

    public Player(byte player) {
        this.setPlayer(player);
    }

    public void setPlayer(byte player) {
        this.currentPlayer = player;
    }

    abstract byte getID();

    abstract TemporaryPlacement placeStone(Board board);
}