package Evaluation;

import tokens.Expression;
import tokens.Token;
import tokens.primitives.Field;
import tokens.primitives.Primitive;
import tokens.primitives.Rule;
import tokens.primitives.Value;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sigrol on 12.09.15.
 */
public class ValueResolver {
    private Map<String, Primitive> valueMap;

    public ValueResolver(Map<String, Primitive> valueMap) {
        this.valueMap = valueMap;
    }

    private Token setValueOnFieldsIfItExist(Token token) {
        if (! (token instanceof Field)) return token;
        Field field = (Field) token;

        if (!this.valueMap.containsKey(field.getKey())) return field;
        return this.valueMap.get(field.getKey());
    }

    private Expression resolveIfAvailableNullIfNot(Expression exp) {
        if (exp.getExpression().stream().filter(t -> t instanceof Field).count() == 0) return exp;

        List<Token> resolvedList = exp.getExpression().stream().map(this::setValueOnFieldsIfItExist).collect(Collectors.toList());
        if (resolvedList.stream().filter(t -> t instanceof Field).count() > 0) return null;
        return new Expression(exp.getName(), resolvedList);
    }

    public Stream<Expression> getResolvableExpressions(Stream<Expression> expressions) {
        return expressions.map(this::resolveIfAvailableNullIfNot).filter(Objects::nonNull);
    }
}
