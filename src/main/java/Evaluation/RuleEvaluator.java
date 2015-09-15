package Evaluation;

import tokens.primitives.Primitive;

import java.util.List;
import tokens.Token;

/**
 * Created by sigrol on 11.09.15.
 */
public class RuleEvaluator {
    public static Primitive parse(List<Token> tokens) {
        Token root = Token.buildTreeAndGetRoot(tokens);
        return (Primitive) root.operate();
    }
}
