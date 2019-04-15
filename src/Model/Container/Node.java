package Model.Container;

import Model.Board.OthelloBoard;
import Model.Stone.Stone;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Node {

    private Hashtable<Integer, Node> branches;
    private OthelloBoard board;
    private Stone stone;
    private Node previous;
    private byte score = 0;
    private byte alpha = -100;
    private byte beta = 100;

    public Node() {

        this.branches = new Hashtable<>();

    }

    public Node(Stone stone, Node previous) {

        this.branches = new Hashtable<>();
        this.stone = stone;
        this.previous = previous;

    }

    public Node(OthelloBoard board, Stone stone, Node previous) {

        this.branches = new Hashtable<>();
        this.board = board;
        this.stone = stone;
        this.previous = previous;

    }

    public ArrayList<Node> getBranches() {
        ArrayList<Node> branches = new ArrayList<>();
        Iterator<Node> iterator = this.branches.values().iterator();
        while (iterator.hasNext()) {
            branches.add(iterator.next());
        }
        return branches;
    }

    public OthelloBoard getBoard() {
        return board;
    }

    public Stone getStone() {
        return stone;
    }

    public Node getPrevious() {
        return previous;
    }

    public byte getScore() {
        return score;
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public byte getBeta() {
        return beta;
    }

    public void setBeta(byte beta) {
        this.beta = beta;
    }

    public byte getAlpha() {
        return alpha;
    }

    public void setAlpha(byte alpha) {
        this.alpha = alpha;
    }

    public void setBranch(int key, Node value) {
        this.branches.put(key, value);
    }

    public byte minimax(boolean maximum) {
        byte minimumScore = 100;
        byte maximumScore = -100;

        ArrayList<Node> branches = getBranches();
        for (Node branch : branches) {
            if (maximum) {
                if (branch.getAlpha() > maximumScore) {
                    maximumScore = branch.getAlpha();
                }
            } else {
                if (branch.getBeta() < minimumScore) {
                    minimumScore = branch.getBeta();
                }

            }
        }
        if (maximum) {
            return maximumScore;
        } else {
            return minimumScore;
        }
    }

    @Override
    public String toString() {
        if (getStone() == null) {
            return "root";
        }
        // return "Placement: " + stone.getX() + ", " + stone.getY() + " score: " + beta + "/" + alpha;
        if (score == 0) {
            return stone + " (alpha: " + alpha + ", beta: " + beta + ") " + score;
        } else {
            return stone + " (alpha: " + (alpha - score) + ", beta: " + beta + ") " + score;
        }
    }

}
