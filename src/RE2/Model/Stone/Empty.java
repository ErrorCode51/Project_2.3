package RE2.Model.Stone;

public class Empty extends Stone {

    public Empty() {
        super();
    }

    @Override
    public char getIdentifier() {
        return 0;
    }

    @Override
    public void turnOver() {

    }

    @Override
    public boolean equals(char identifier) {
        return false;
    }

}
