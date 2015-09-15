package tokens.primitives;

/**
 * Created by sigrol on 11.09.15.
 */
public class Rule extends Primitive {
    private boolean value;

    public Rule(boolean value) {
        super();
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean v) {
        value = v;
    }
}
