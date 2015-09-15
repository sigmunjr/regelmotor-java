package tokens.operators;

import tokens.Token;
import tokens.primitives.Value;

/**
 * Created by sigrol on 11.09.15.
 */
public class Multiply extends Operator {
    @Override
    public Value operate() {
        super.operate();
        return new Value(children.stream().filter(o -> o instanceof Value)
                .map(Value.class::cast).mapToDouble(Value::getValue)
                .reduce(1, (m, v) -> m*v));
    }

    @Override
    public int getPriority() {
        return 200;
    }

}
