package RE2.Model;

public abstract class Player {

    public byte currentPlayer;

    public Player(byte player) {
        this.setPlayer(player);
    }

    public void setPlayer(byte player) {
        this.currentPlayer = player;
    }

    abstract TemporaryPlacement placeStone(Board board);
}