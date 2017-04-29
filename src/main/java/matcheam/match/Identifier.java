package matcheam.match;

/**
 * Created by ooguro on 2017/01/07.
 */
public class Identifier {

    private String value;

    public Identifier(String value) {
        this.value = value;
    }

    public Identifier() {

    }

    @Override
    public String toString() {
        return value;
    }
}
