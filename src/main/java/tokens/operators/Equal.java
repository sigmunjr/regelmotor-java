package tokens.operators;

import tokens.*;
import tokens.primitives.Rule;
import tokens.primitives.Value;

/**
 * Created by sigrol on 11.09.15.
 */
public class Equal extends Operator {
    @Override
    public Rule operate() {
        super.operate();

        if (children.size() == 2 &&
                children.get(0) instanceof Value && children.get(1) instanceof Value) {
            return new Rule(((Value) children.get(0)).getValue() == ((Value) children.get(1)).getValue());
        } else {
            throw new RuntimeException("Er lik operatoren må ha en verdi på hver side.");
        }
    }

    @Override
    public int getPriority() {
        return 30;
    }
}
