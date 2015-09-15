package tokens.operators;
import tokens.Token;
import tokens.primitives.Value;

/**
 * Created by sigrol on 11.09.15.
 */
public class Sum extends Operator {
    @Override
    public Value operate() {
        super.operate();
        return new Value(children.stream().filter(o -> o instanceof Value)
                .map(Value.class::cast).mapToDouble(Value::getValue)
                .sum());
    }

    @Override
    public int getPriority() {
        return 100;
    }
}
