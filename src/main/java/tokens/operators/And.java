package tokens.operators;

import tokens.*;
import tokens.primitives.Rule;
import tokens.primitives.Value;

/**
 * Created by sigrol on 11.09.15.
 */
public class And extends Operator {
    @Override
    public Rule operate() {
        super.operate();
        if (children.size() == 2 &&
                children.get(0) instanceof Rule && children.get(1) instanceof Rule) {
            return new Rule(((Rule) children.get(0)).getValue() && ((Rule) children.get(1)).getValue());
        } else {
            throw new RuntimeException("'og' operatoren må ha en regel på hver side.");
        }
    }

    @Override
    public int getPriority() {
        return 20;
    }
}
