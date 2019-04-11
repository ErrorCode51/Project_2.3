package Model.Player;

import Model.Board.Board;
import Model.Board.OthelloBoard;
import Model.Container.Node;
import Model.Rules.OthelloRules;
import Model.Rules.Rules;
import Model.Stone.OthelloStone;
import Model.Stone.Stone;

import java.util.ArrayList;

public class ArtificialOthello implements Player {

    public char identifier;

    private int depthCounter = 0;
    private int maximumDepth = 300000;
    private char current;

    private Node root = null;

    public ArtificialOthello(char identifier) {

        this.identifier = identifier;

    }

    @Override
    public String toString() {
        return "Genichiro";
    }

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public byte[] placeStone(Board board, Rules rules) {
        // Generate a list containing all legal placements
        current = identifier;
        ArrayList<Stone> legalPlacements = ((OthelloRules) rules).findAllLegal(board, current);
        // Start recursively placing stones on said legal placements
        placeStone((OthelloBoard) board, rules, legalPlacements);
        // Find best placement
        findBestPlacement(this.root);
        int indexBestPlacement = 0;
        ArrayList<Node> branches = root.getBranches();
        for (int index = 0, size = branches.size(); index < size; index++) {
            if (branches.get(index).getMaximumScore() > branches.get(indexBestPlacement).getMaximumScore()) {
                indexBestPlacement = index;
            }
            if (branches.get(index).getMaximumScore() == branches.get(indexBestPlacement).getMaximumScore()) {
                if (branches.get(index).getMinimumScore() > branches.get(indexBestPlacement).getMinimumScore()) {
                    indexBestPlacement = index;
                }
            }
        }
        // Some random return shit so StupidJ stops complaining
        byte[] placement = {branches.get(indexBestPlacement).getStone().getX(), branches.get(indexBestPlacement).getStone().getY()};
        return placement;
        // return new byte[] {0, 0};
    }

    private void findBestPlacement(final Node root) {
        ArrayList<Node> branches = root.getBranches();
        for (Node branch : branches) {
            this.findBestPlacement(branch);
            branch.minimax(true);
            branch.minimax(false);
        }
    }

    private void placeStone(OthelloBoard board, Rules rules, ArrayList<Stone> stones) {
        // Reset the depth counter
        depthCounter = 0;
        // Create a new root
        root = new Node();

        if (stones.size() > 0) {
            this.placeStone(board, rules, root, stones);
        }
    }

    private void placeStone(OthelloBoard board, Rules rules, Node root, ArrayList<Stone> stones) {
        if (this.depthCounter < maximumDepth) {
            // This is to limit the recursion should it take to long to take a turn
            this.depthCounter++;
            for (Stone stone : stones) {
                // Create a branch for our tree and hook it to the root; this is how nature works right?
                Node node = new Node(stone, root);
                root.setBranch(generateTableID(stone), node);
                // Don't play on the actual board por favor
                OthelloBoard temporaryBoard = generateBoard(board, rules);
                // Place a stone and flip the necessary stones.
                // Todo: Refactor so this is one method as originally intended
                ((OthelloRules) rules).testForLegal(temporaryBoard, stone, true);
                temporaryBoard.set(stone);
                // Because we limit recursion we score based on the amount of stones turned
                if (stone.getIdentifier() == this.identifier) {
                    byte score = temporaryBoard.getScoreByIdentifier(stone.getIdentifier());
                    // Add score if placement wins the match
                    if (rules.gameOver(temporaryBoard) == stone.getIdentifier()) {
                        score += 30;
                    }
                    // Add extra score if placement is in a corner
                    if (stone.getX() == 0 && stone.getY() == 0 || stone.getX() == 0 && stone.getY() == 7 ||
                            stone.getX() == 7 && stone.getY() == 0 || stone.getX() == 7 && stone.getY() == 7) {
                        score += 15;
                    }
                    // Add extra score if placement is a stone controlling a corner
                    if (stone.getX() == 0 && stone.getY() == 2 || stone.getX() == 2 && stone.getY() == 0 ||
                            stone.getX() == 0 && stone.getY() == 5 || stone.getX() == 5 && stone.getY() == 0 ||
                            stone.getX() == 7 && stone.getY() == 2 || stone.getX() == 7 && stone.getY() == 5 ||
                            stone.getX() == 2 && stone.getY() == 7 || stone.getX() == 5 && stone.getY() == 7) {
                        score += 10;
                    }
                    // Add extra score if placement is a stone controlling a stone controlling a corner
                    if (stone.getX() == 2 && stone.getY() == 2 || stone.getX() == 2 && stone.getY() == 5 ||
                            stone.getX() == 5 && stone.getY() == 5 || stone.getX() == 5 && stone.getY() == 2) {
                        score += 5;
                    }
                    if (score > node.getMaximumScore()) {
                        node.setMaximumScore(score);
                        if (node.getPrevious() != null) {
                            if (score > node.getPrevious().getMaximumScore()) {
                                node.getPrevious().setMaximumScore(score);
                            }
                        }
                    }
                } else {
                    byte score = (byte) (temporaryBoard.getScoreByIdentifier(stone.getIdentifier()) * -1);
                    // Subtract extra score if placement is in a corner
                    if (stone.getX() == 0 && stone.getY() == 0 || stone.getX() == 0 && stone.getY() == 7 ||
                            stone.getX() == 7 && stone.getY() == 0 || stone.getX() == 7 && stone.getY() == 7) {
                        score -= 15;
                    }
                    // Subtract extra score if placement is a stone controlling a corner
                    if (stone.getX() == 0 && stone.getY() == 2 || stone.getX() == 2 && stone.getY() == 0 ||
                            stone.getX() == 0 && stone.getY() == 5 || stone.getX() == 5 && stone.getY() == 0 ||
                            stone.getX() == 7 && stone.getY() == 2 || stone.getX() == 7 && stone.getY() == 5 ||
                            stone.getX() == 2 && stone.getY() == 7 || stone.getX() == 5 && stone.getY() == 7) {
                        score -= 10;
                    }
                    // Subtract extra score if placement is a stone controlling a stone controlling a corner
                    if (stone.getX() == 2 && stone.getY() == 2 || stone.getX() == 2 && stone.getY() == 5 ||
                            stone.getX() == 5 && stone.getY() == 5 || stone.getX() == 5 && stone.getY() == 2) {
                        score -= 5;
                    }
                    if (score < node.getMinimumScore()) {
                        node.setMinimumScore(score);
                        if (node.getPrevious() != null) {
                            if (score < node.getPrevious().getMinimumScore()) {
                                node.getPrevious().setMinimumScore(score);
                            }
                        }
                    }
                }
                // Now repeat the same steps for the opposing player
                toggleCurrent();
                ArrayList<Stone> legalPlacements = ((OthelloRules) rules).findAllLegal(temporaryBoard, current);
                if (legalPlacements.size() > 0) {
                    this.placeStone(temporaryBoard, rules, node, legalPlacements);
                    // I'll leave this here for future reference as this is the ideal place to put a println()
                    // System.out.println(stones);
                    // if (stones.get(0).getIdentifier() == this.identifier) {
                    //     System.out.println(node.getStone() + " max. score is: " + node.getMaximumScore());
                    // } else {
                    //     System.out.println(node.getStone() + " min. score is: " + node.getMinimumScore());
                    // }
                }
            }
        }
    }


    // Generate a copy of the received board
    OthelloBoard generateBoard(OthelloBoard board, Rules rules) {
        OthelloBoard temporaryBoard = new OthelloBoard();
        temporaryBoard.initialize(rules);
        final byte SIZE = board.getSize();
        for (byte row = 0; row < SIZE; row++) {
            for (byte column = 0; column < SIZE; column++) {
                if (!board.isEmpty(row, column)) {
                    temporaryBoard.set(new OthelloStone((OthelloStone) board.get(row, column)));
                }
            }
        }
        return temporaryBoard;
    }

    int generateTableID(Stone stone) {
        return 8 * stone.getX() + stone.getY();
    }

    private void toggleCurrent() {
        if (this.current == 'B') {
            this.current = 'W';
        } else {
            this.current = 'B';
        }
    }

}
