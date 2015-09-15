package tokens.primitives;

/**
 * Created by sigrol on 11.09.15.
 */
public class Value extends Primitive {
    private double value;

    public Value(double v) {
        super();
        value = v;
    }


    public Value(double v, String key) {
        value = v;
    }

    public double getValue() {
        return value;
    }
}
