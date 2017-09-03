package matcheam.match;

/**
 * Created by ooguro on 2017/01/07.
 */
public class Identifier {

    private int value;

    public Identifier(int value) {
        this.value = value;
    }

    public Identifier(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public int value() {
        return value;
    }
}
