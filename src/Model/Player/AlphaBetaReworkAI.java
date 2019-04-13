package Model.Player;

import Model.Board.Board;
import Model.Board.OthelloBoard;
import Model.Container.Node;
import Model.Rules.OthelloRules;
import Model.Rules.Rules;
import Model.Stone.OthelloStone;
import Model.Stone.Stone;

import java.util.ArrayList;

public class AlphaBetaReworkAI implements Player {

    public char identifier;

    private byte cornerWeight;
    private byte innerCornerWeight;
    private byte innerCornerGuardWeight;
    private byte outerCornerWeight;
    private byte outerCornerGuardWeight;
    private byte edgesWeight;

    private ArrayList<OthelloStone> corners = new ArrayList<>();
    private ArrayList<OthelloStone> innerCorners = new ArrayList<>();
    private ArrayList<OthelloStone> innerCornerGuards = new ArrayList<>();
    private ArrayList<OthelloStone> outerCorners = new ArrayList<>();
    private ArrayList<OthelloStone> outerCornerGuards = new ArrayList<>();
    private ArrayList<OthelloStone> edges = new ArrayList<>();


    private int maximumDepth = 5;
    private char current;

    private Node root = null;

    public AlphaBetaReworkAI(char identifier) {

        this.identifier = identifier;

        // [A][D][E][F][F][E][F][A]
        // [D][B][ ][ ][ ][ ][B][F] | A: Corners
        // [E][ ][C][ ][ ][C][ ][E] | B: Inner corners
        // [F][ ][ ][ ][ ][ ][ ][F] | C: Inner corner guards
        // [F][ ][ ][ ][ ][ ][ ][F] | D: Outer corners
        // [E][ ][C][ ][ ][C][ ][E] | E: Outer corner guards
        // [D][B][ ][ ][ ][ ][B][D] | F: Edges
        // [A][D][E][F][F][E][D][A]

        this.cornerWeight = 27;
        this.innerCornerWeight = 5;
        this.innerCornerGuardWeight = 20;
        this.outerCornerWeight = 3;
        this.outerCornerGuardWeight = 10;
        this.edgesWeight = 2;

        this.corners.add(new OthelloStone((byte) 0, (byte) 0));
        this.corners.add(new OthelloStone((byte) 0, (byte) 7));
        this.corners.add(new OthelloStone((byte) 7, (byte) 0));
        this.corners.add(new OthelloStone((byte) 7, (byte) 7));

        this.innerCorners.add(new OthelloStone((byte) 1, (byte) 1));
        this.innerCorners.add(new OthelloStone((byte) 1, (byte) 6));
        this.innerCorners.add(new OthelloStone((byte) 6, (byte) 1));
        this.innerCorners.add(new OthelloStone((byte) 6, (byte) 6));

        this.innerCornerGuards.add(new OthelloStone((byte) 2, (byte) 2));
        this.innerCornerGuards.add(new OthelloStone((byte) 2, (byte) 5));
        this.innerCornerGuards.add(new OthelloStone((byte) 5, (byte) 5));
        this.innerCornerGuards.add(new OthelloStone((byte) 5, (byte) 2));

        this.outerCorners.add(new OthelloStone((byte) 0, (byte) 1));
        this.outerCorners.add(new OthelloStone((byte) 1, (byte) 0));
        this.outerCorners.add(new OthelloStone((byte) 0, (byte) 6));
        this.outerCorners.add(new OthelloStone((byte) 6, (byte) 0));
        this.outerCorners.add(new OthelloStone((byte) 1, (byte) 7));
        this.outerCorners.add(new OthelloStone((byte) 7, (byte) 1));
        this.outerCorners.add(new OthelloStone((byte) 6, (byte) 7));
        this.outerCorners.add(new OthelloStone((byte) 7, (byte) 6));

        this.outerCornerGuards.add(new OthelloStone((byte) 0, (byte) 2));
        this.outerCornerGuards.add(new OthelloStone((byte) 2, (byte) 0));
        this.outerCornerGuards.add(new OthelloStone((byte) 0, (byte) 5));
        this.outerCornerGuards.add(new OthelloStone((byte) 5, (byte) 0));
        this.outerCornerGuards.add(new OthelloStone((byte) 7, (byte) 2));
        this.outerCornerGuards.add(new OthelloStone((byte) 2, (byte) 7));
        this.outerCornerGuards.add(new OthelloStone((byte) 7, (byte) 5));
        this.outerCornerGuards.add(new OthelloStone((byte) 5, (byte) 7));

        this.edges.add(new OthelloStone((byte) 0, (byte) 3));
        this.edges.add(new OthelloStone((byte) 3, (byte) 0));
        this.edges.add(new OthelloStone((byte) 0, (byte) 4));
        this.edges.add(new OthelloStone((byte) 4, (byte) 0));
        this.edges.add(new OthelloStone((byte) 7, (byte) 3));
        this.edges.add(new OthelloStone((byte) 3, (byte) 7));
        this.edges.add(new OthelloStone((byte) 7, (byte) 4));
        this.edges.add(new OthelloStone((byte) 4, (byte) 7));

    }

    @Override
    public String toString() {
        return "Wolf";
    }

    @Override
    public char getIdentifier() {
        return identifier;
    }

    @Override
    public byte[] placeStone(Board board, Rules rules) {
        current = identifier;
        ArrayList<Stone> legalPlacements = ((OthelloRules) rules).findAllLegal(board, this.identifier);
        placeStone((OthelloBoard) board, (OthelloRules) rules, legalPlacements, (byte) 0);
        alphaBeta(root, (byte) -127, (byte) 127, true);
        int indexBestPlacement = 0;
        ArrayList<Node> branches = root.getBranches();
        for (int index = 0, size = branches.size(); index < size; index++) {
            Node node = branches.get(index);
            node.setScore(score((OthelloStone) node.getStone(), node.getBoard()));
            node.setAlpha((byte) (node.getScore() + node.getAlpha()));
            if (node.getAlpha() > branches.get(indexBestPlacement).getAlpha()) {
                indexBestPlacement = index;
            } else if (node.getAlpha() == branches.get(indexBestPlacement).getAlpha()) {
                if (node.getBeta() < branches.get(indexBestPlacement).getBeta()) {
                    indexBestPlacement = index;
                }
            }
        }
        System.out.println(branches.get(indexBestPlacement) + " from: " + branches);
        byte[] placement = {branches.get(indexBestPlacement).getStone().getX(), branches.get(indexBestPlacement).getStone().getY()};
        return placement;
    }

    private byte alphaBeta(Node node, byte alpha, byte beta, boolean maximizing) {
//        System.out.println(node);
        if (node.getBranches().size() == 0) {
//            System.out.println("found leaf");
            return node.getBoard().getScoreByIdentifier(identifier);
        }
        ArrayList<Node> branches = node.getBranches();
//        System.out.println(branches);
        if (maximizing) {
            for (Node branch : branches) {
                byte score = 1;
                score *= branch.getBoard().getScoreByIdentifier(identifier);
                branch.setAlpha(score);
                if (branch.getPrevious().getAlpha() < score) {
                    branch.getPrevious().setAlpha(score);
                }
                alpha = (byte) Math.max(alpha, alphaBeta(branch, alpha, beta, false));
                if (alpha >= beta) {
//                    System.out.println(alpha + ">=" + beta);
//                    System.out.println("pruning: " + branch);
                    break;
                }
            }
//            System.out.println(node + " returning alpha");
            return alpha;
        } else if (!maximizing) {
            for (Node branch : branches) {
                byte score = 1;
                score *= branch.getBoard().getScoreByIdentifier(identifier);
                branch.setBeta(score);
                if (branch.getPrevious().getBeta() > score) {
                    branch.getPrevious().setBeta(score);
                }
                beta = (byte) Math.min(beta, alphaBeta(branch, alpha, beta, true));
                if (beta <= alpha) {
//                    System.out.println(beta + "<=" + alpha);
//                    System.out.println("pruning: " + branch);
                    break;
                }
            }
//            System.out.println(node + " returning beta");
            return beta;
        }
        System.out.println("Mandatory return statement returns 0");
        return 0;
    }

    private void placeStone(OthelloBoard board, OthelloRules rules, ArrayList<Stone> stones, byte counter) {
        root = new Node();
        if (stones.size() > 0) {
            this.placeStone(board, rules, root, stones, counter);
        }
    }

    private void placeStone(OthelloBoard board, OthelloRules rules, Node root, ArrayList<Stone> stones, byte counter) {
        if (counter < maximumDepth) {
            counter++;
            for (Stone stone : stones) {
                OthelloBoard temporaryBoard = generateBoard(board, rules);
                rules.testForLegal(temporaryBoard, stone, true);
                temporaryBoard.set(stone);

                Node node = new Node(temporaryBoard, stone, root);
                root.setBranch(generateTableID(stone), node);

                toggleCurrent();
                ArrayList<Stone> legalPlacements = rules.findAllLegal(temporaryBoard, current);
                if (legalPlacements.size() > 0) {
                    this.placeStone(temporaryBoard, rules, node, legalPlacements, counter);
                }
            }
        }
    }

    private byte score(OthelloStone stone, OthelloBoard board) {
        byte score = 0;
        // Scoring the corners
        if (corners.contains(stone)) {
            score += scoreCorners(stone);
        } else if (innerCorners.contains(stone)) {
            score += scoreInnerCorners(stone, board);
        } else if (innerCornerGuards.contains(stone)) {
            score += scoreInnerCornerGuards(stone);
        } else if (outerCorners.contains(stone)) {
            score += scoreOuterCorners(stone, board);
        } else if (outerCornerGuards.contains(stone)) {
            score += scoreOuterCornerGuards(stone);
        } else if (edges.contains(stone)) {
            score += scoreEdges(stone);
        }

        return score;
    }

    private byte scoreCorners(OthelloStone stone) {
        byte score = 0;
        if (stone.getIdentifier() == identifier) {
            score += cornerWeight;
        } else {
            score -= cornerWeight;
        }
        return score;
    }

    private byte scoreInnerCorners(OthelloStone stone, OthelloBoard board) {
        byte score = 0;
        // Empty corner
        if (board.isEmpty((stone.getX() == 1) ? (byte) 0 : 7, (stone.getY() == 1) ? (byte) 0 : 7)) {
            if (stone.getIdentifier() == identifier) {
                // If the corner is empty placing our stone here is dangerous
                score -= innerCornerWeight;
            } else {
                score += innerCornerWeight;
            }
        } else {
            if (board.get((stone.getX() == 1) ? (byte) 0 : 7, (stone.getY() == 1) ? (byte) 0 : 7).getIdentifier() == identifier) {
                score += innerCornerWeight;
            } else {
                score -= innerCornerWeight;
            }
        }
        return score;
    }

    private byte scoreInnerCornerGuards(OthelloStone stone) {
        byte score = 0;
        if (stone.getIdentifier() == identifier) {
            score += innerCornerGuardWeight;
        } else {
            score -= innerCornerGuardWeight;
        }
        return score;
    }

    private byte scoreOuterCorners(OthelloStone stone, OthelloBoard board) {
        byte score = 0;
        byte weight = 2;
        if (stone.getX() == 0) {
            if (board.isEmpty((byte) 0, (stone.getY() == 1) ? (byte) 0 : 7)) {
                if (stone.getIdentifier() == identifier) {
                    score -= outerCornerWeight;
                } else {
                    score += outerCornerWeight;
                }
            } else {
                if (board.get((byte) 0, (stone.getY() == 1) ? (byte) 0 : 7).getIdentifier() == identifier) {
                    score += outerCornerWeight;
                } else {
                    score -= outerCornerWeight;
                }
            }
        } else if (stone.getX() == 7) {
            if (board.isEmpty((byte) 7, (stone.getY() == 1) ? (byte) 0 : 7)) {
                if (stone.getIdentifier() == identifier) {
                    score -= outerCornerWeight;
                } else {
                    score += outerCornerWeight;
                }
            } else {
                if (board.get((byte) 7, (stone.getY() == 1) ? (byte) 0 : 7).getIdentifier() == identifier) {
                    score += outerCornerWeight;
                } else {
                    score -= outerCornerWeight;
                }
            }
        } else if (stone.getY() == 0) {
            if (board.isEmpty((stone.getX() == 1) ? (byte) 0 : 7, (byte) 0)) {
                if (stone.getIdentifier() == identifier) {
                    score -= outerCornerWeight;
                } else {
                    score += outerCornerWeight;
                }
            } else {
                if (board.get((stone.getX() == 1) ? (byte) 0 : 7, (byte) 0).getIdentifier() == identifier) {
                    score += outerCornerWeight;
                } else {
                    score -= outerCornerWeight;
                }
            }
        } else if (stone.getY() == 7) {
            if (board.isEmpty((stone.getX() == 1) ? (byte) 0 : 7, (byte) 7)) {
                if (stone.getIdentifier() == identifier) {
                    score -= outerCornerWeight;
                } else {
                    score += outerCornerWeight;
                }
            } else {
                if (board.get((stone.getX() == 1) ? (byte) 0 : 7, (byte) 7).getIdentifier() == identifier) {
                    score += outerCornerWeight;
                } else {
                    score -= outerCornerWeight;
                }
            }
        }
        return score;
    }

    private byte scoreOuterCornerGuards(OthelloStone stone) {
        byte score = 0;
        if (stone.getIdentifier() == identifier) {
            score += outerCornerGuardWeight;
        } else {
            score -= outerCornerGuardWeight;
        }
        return score;
    }

    private byte scoreEdges(OthelloStone stone) {
        byte score = 0;
        ;
        if (stone.getIdentifier() == identifier) {
            score += edgesWeight;
        } else {
            score -= edgesWeight;
        }
        return score;
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

