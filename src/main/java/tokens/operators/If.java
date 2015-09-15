package tokens.operators;

import tokens.*;
import tokens.primitives.Rule;

/**
 * Created by sigrol on 11.09.15.
 */
public class If extends Operator {
    @Override
    public Token operate() {
        super.operate();

        if (children.size() >= 2 &&
                children.get(1) instanceof Rule) {
            return (((Rule) children.get(1)).getValue()) ? children.get(0) : null;
        } else {
            throw new RuntimeException("'Hvis' operatoren må ha en regel på venstre side og et uttrykk på høyre side.");
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
