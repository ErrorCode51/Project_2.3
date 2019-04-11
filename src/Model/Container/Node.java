package Model.Container;

import Model.Stone.Stone;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Node {

    private Hashtable<Integer, Node> branches;
    private Stone stone;
    private Node previous;
    private byte score = 0;
    private byte minimumScore = 100;
    private byte maximumScore = -100;

    public Node() {

        this.branches = new Hashtable<>();

    }

    public Node(Stone stone, Node previous) {

        this.branches = new Hashtable<>();
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

    public Stone getStone() {
        return stone;
    }

    public Node getPrevious() {
        return previous;
    }

    public byte getScore() {
        return score;
    }

    public byte getMinimumScore() {
        return minimumScore;
    }

    public byte getMaximumScore() {
        return maximumScore;
    }

    public void setBranch(int key, Node value) {
        this.branches.put(key, value);
    }

    public void setScore(byte score) {
        this.score = score;
    }

    public void setMinimumScore(byte minimumScore) {
        this.minimumScore = minimumScore;
    }

    public void setMaximumScore(byte maximumScore) {
        this.maximumScore = maximumScore;
    }

    public byte minimax(boolean maximum) {
        byte minimumScore = 100;
        byte maximumScore = -100;

        ArrayList<Node> branches = getBranches();
        for (Node branch : branches) {
            if (maximum) {
                if (branch.getMaximumScore() > maximumScore) {
                    maximumScore = branch.getMaximumScore();
                }
            } else {
                if (branch.getMinimumScore() < minimumScore) {
                    minimumScore = branch.getMinimumScore();
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
        return "Placement: " + stone.getX() + ", " + stone.getY() + " score: " + minimumScore + "/" + maximumScore;
    }

}
