package tokens.operators;

import tokens.*;
import tokens.primitives.Rule;

/**
 * Created by sigrol on 11.09.15.
 */
public class Else extends Operator {
    @Override
    public Token operate() {
        super.operate();

        if (children.size() >= 2) {
            return (children.get(0) == null) ? children.get(1) : children.get(0);
        } else {
            throw new RuntimeException("'Ellers' operatoren må ha en regel på venstre side og et uttrykk på høyre side.");
        }
    }

    @Override
    public int getPriority() {
        return 10;
    }
}
