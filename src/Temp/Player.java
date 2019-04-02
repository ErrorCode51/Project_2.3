package me.neireau.tictactest;

public abstract class Player {

    // Todo: Refactor so players are not hardcoded
    public static final int AI = 0;
    public static final int HUMAN = 1;
    // Used to determine whose turn it is
    public int currentPlayer;

    public Player(int player) {

        this.setPlayer(player);

    }

    // Abstract methods
    public abstract int getID();

    public abstract char getNoughtOrCross();

    // Getters
    public char getNoughtOrCross(Player player) {
        return player.getNoughtOrCross();
    }

    public int getCurrentPlayer() {
        return this.currentPlayer;
    }

    // Setters
    public void setPlayer(int player) {
        this.currentPlayer = player;
    }

}