package tokens;

import java.util.List;

/**
 * Created by sigrol on 12.09.15.
 */
public class Expression {
    private String name;
    private List<Token> expression;

    public Expression(String name, List<Token> expression) {
        this.name = name;
        this.expression = expression;
    }

    public String getName() {
        return name;
    }

    public List<Token> getExpression() {
        return expression;
    }
}
