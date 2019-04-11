package Model.Player;

import Model.Board.Board;
import Model.Board.OthelloBoard;
import Model.Container.Node;
import Model.Rules.OthelloRules;
import Model.Rules.Rules;
import Model.Stone.OthelloStone;
import Model.Stone.Stone;
import Model.Stone.TicTacToeStone;

import java.util.ArrayList;

public class ArtificialOthello implements Player {

    public char identifier;

    private int depthCounter = 0;
    private int maximumDepth = 100000;
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
        // System.out.println("Hello world!");
        // Generate a list containing all legal placements
        current = identifier;
        ArrayList<Stone> legalPlacements = ((OthelloRules) rules).findAllLegal(board, current);
        //System.out.println(legalPlacements);
        // System.out.println(legalPlacements);
        // Start recursively placing stones on said legal placements
        placeStone(board, rules, legalPlacements);
        // Find best placement
        findBestPlacement(this.root);
        int indexBestPlacement = 0;
        ArrayList<Node> branches = root.getBranches();
        for (int index = 0, size = branches.size(); index < size; index++) {
            if (branches.get(index).getMaximumScore() > branches.get(indexBestPlacement).getMaximumScore()) {
                indexBestPlacement = index;
            }
        }
        // Some random return shit so StupidJ stops complaining
        byte[] placement = {branches.get(indexBestPlacement).getStone().getX(), branches.get(indexBestPlacement).getStone().getY()};
        // System.out.println(placement[0] + ", " + placement[1]);
        System.out.println(depthCounter);
        return placement;
    }

    private void findBestPlacement(final Node root) {
        ArrayList<Node> branches = root.getBranches();
        for (Node branch : branches) {
            this.findBestPlacement(branch);
            branch.minimax(true);
            branch.minimax(false);
            // System.out.println(branch.getStone() + ": " + branch.minimax(true));
            // System.out.println(branch.getStone() + ": " + branch.minimax(false));
        }
    }

    private void placeStone(Board board, Rules rules, ArrayList<Stone> stones) {
        // Reset the depth counter
        depthCounter = 0;
        // Create a new root
        root = new Node();

        if (stones.size() > 0) {
            this.placeStone(board, rules, root, stones);
        }
    }

    private void placeStone(Board board, Rules rules, Node root, ArrayList<Stone> stones) {
        // System.out.println(stones);
        if (this.depthCounter < maximumDepth) {
            // This is to limit the recursion should it take to long to take a turn
            this.depthCounter++;
            for (Stone stone : stones) {
                // Create a branch for our tree and hook it to the root; this is how nature works right?
                Node node = new Node(stone, root);
                root.setBranch(generateTableID(stone), node);
                // Don't play on the actual board por favor
                Board temporaryBoard = genereateBoard(board, rules);
                // Because we limit recursion we score based on the amount of stones turned
                byte stonesTurned = 0;
//                stonesTurned += ((OthelloRules) rules).getScore(temporaryBoard, current);
                // Place a stone and flip the necessary stones.
                ((OthelloRules) rules).testForLegal(temporaryBoard, stone, true);
                temporaryBoard.set(stone);
                stonesTurned = ((OthelloRules) rules).getScore(temporaryBoard, current);
                if (stonesTurned > node.getMaximumScore()) {
                    node.setMaximumScore(stonesTurned);
                    if (node.getPrevious() != null) {
                        node.getPrevious().setMaximumScore(stonesTurned);
                    }
                }
                if (stonesTurned < node.getMinimumScore()) {
                    node.setMinimumScore(stonesTurned);
                    if (node.getPrevious() != null) {
                        node.getPrevious().setMinimumScore(stonesTurned);
                    }
                }
                // Now repeat the same steps for the opposing player
                toggleCurrent();
                ArrayList<Stone> legalPlacements = ((OthelloRules) rules).findAllLegal(temporaryBoard, current);
                if (legalPlacements.size() > 0) {
                    this.placeStone(temporaryBoard, rules, node, legalPlacements);
                    // System.out.println(node.getStone() + " from: " + stones + "\n" + temporaryBoard);
                }
            }
        }

    }


    // Generate a copy of the received board
    Board genereateBoard(Board board, Rules rules) {
        Board temporaryBoard = new OthelloBoard();
        temporaryBoard.initialize(rules);
        final byte SIZE = board.getSize();
        for (byte row = 0; row < SIZE; row++) {
            for (byte column = 0; column < SIZE; column++) {
                if (!board.isEmpty(row, column)) {
                    temporaryBoard.set(new OthelloStone(row, column, board.get(row, column).getIdentifier()));
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

    // Returns the opposing identifier. Only used by AI.
    private char getOpposingIdentifier() {
        // Unsure if this method is needed in both TicTacToe and Othello, made if future-proof non the less
        char opponent = ' ';
        switch (identifier) {
            case 'X':
                opponent = 'O';
                break;
            case 'O':
                opponent = 'X';
                break;
            case 'B':
                opponent = 'W';
                break;
            case 'W':
                opponent = 'B';
                break;
        }
        return opponent;
    }

    private Stone createStone(byte row, byte column, char identifier) {
        if (this.identifier == 'X' || this.identifier == 'O') {
            return new TicTacToeStone(row, column, identifier);
        } else {
            return new OthelloStone(row, column, identifier);
        }
    }

}
