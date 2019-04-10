package RE2.Model.Board;

import RE2.Model.Rules.OthelloRules;
import RE2.Model.Rules.Rules;
import RE2.Model.Stone.Empty;
import RE2.Model.Stone.Stone;

public class OthelloBoard extends Board implements Cloneable {

    // A board consists of a two dimensional array containing stones
    private OthelloRules rules;

    public OthelloBoard() {

        super((byte) 8);

    }

//    @Override
//    public boolean set(Stone stone) {
//        if (get(stone.getX(), stone.getY()).getClass() != Empty.class) {
//            System.out.println("set() will return false due to !isEmpty()");
//            return false;
//        } else {
//            System.out.println("set() received: " + stone);
//            if (rules.testForLegal(this, stone, true)) {
//                tiles[stone.getX()][stone.getY()] = stone;
//                return true;
//            } else {
//                System.out.println("set() will return false due to illegal placement");
//                return false;
//            }
//        }
//    }

    public Object generateBackup() {
        try {
            return this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
