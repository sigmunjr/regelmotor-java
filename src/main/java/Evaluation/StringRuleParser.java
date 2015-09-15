package Evaluation;

import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Doubles;
import sun.reflect.misc.ConstructorUtil;
import tokens.Token;
import tokens.operators.*;
import tokens.primitives.Field;
import tokens.primitives.Rule;
import tokens.primitives.Value;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by sigrol on 12.09.15.
 */
public class StringRuleParser {
    Map<String, String> variableMap;
    Map<String, Class> operatorMap;

    public StringRuleParser() {
        this.variableMap = ImmutableMap.<String, String>builder().build();
        this.operatorMap = getOperatorMap();
    }

    public StringRuleParser(Map<String, String> variableMap) {
        this.variableMap = variableMap;
        this.operatorMap = getOperatorMap();
    }

    private Map<String, Class> getOperatorMap() {
        return ImmutableMap.<String, Class>builder()
                .put("+", Sum.class)
                .put("*", Multiply.class)
                .put("<", LessThan.class)
                .put(">", MoreThan.class)
                .put("=", Equal.class)
                .put("og", And.class)
                .put("hvis", If.class)
                .put("ellers", Else.class)
                .build();
    }

    public Token stringToToken(String stringToken) {
        Class tokenClass;
        Double input;

        try {
            if (this.operatorMap.containsKey(stringToken)) {
                tokenClass = this.operatorMap.get(stringToken);
                return (Token) tokenClass.getConstructor().newInstance();
            } else if (Doubles.tryParse(stringToken) != null) {
                tokenClass = Value.class;
                input = Doubles.tryParse(stringToken);
                return (Token) tokenClass.getConstructor(Double.TYPE).newInstance(input);
            } else if (this.variableMap.containsKey(stringToken)) {
                tokenClass = Field.class;
                return (Token) tokenClass.getConstructor(String.class).newInstance(stringToken);
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Token> parse(String ruleString) {
        List<Token> tokens = new ArrayList<>();
        return Stream.of(ruleString.split("\\s+")).map(this::stringToToken)
                .filter(Objects::nonNull).collect(Collectors.toList());
    }
}
