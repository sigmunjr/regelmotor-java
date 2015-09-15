import Evaluation.ValueResolver;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import tokens.Expression;
import tokens.operators.If;
import tokens.operators.Sum;
import tokens.primitives.Field;
import tokens.primitives.Primitive;
import tokens.primitives.Rule;
import tokens.primitives.Value;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by sigrol on 13.09.15.
 */
public class ValueResolverTest {

    @Test
    public void shouldReturnExpressionsWithNoDependenciesUnchanged() {
        ValueResolver valueResolver = new ValueResolver(new HashMap<>());
        List<Expression> expressions = Arrays.asList(new Expression("test1", Arrays.asList(new Value(5), new Sum(), new Value(8))));
        List<Expression> returnExps = valueResolver.getResolvableExpressions(expressions.stream()).collect(Collectors.toList());

        assertArrayEquals(expressions.get(0).getExpression().toArray(), returnExps.get(0).getExpression().toArray());
    }

    @Test
    public void shouldResolveExpression() {
        Map<String, Primitive> valueMap = ImmutableMap.<String, Primitive>builder()
                .put("testDep1", new Value(8))
                .put("testDep2", new Rule(false))
                .build();
        ValueResolver valueResolver = new ValueResolver(valueMap);
        List<Expression> expressions = Arrays.asList(new Expression("test1", Arrays.asList(new Field("testDep1"), new Field("testDep2"))));
        List<Expression> returnExps = valueResolver.getResolvableExpressions(expressions.stream()).collect(Collectors.toList());

        assertTrue(returnExps.get(0).getExpression().get(0) instanceof Value);
        assertTrue(returnExps.get(0).getExpression().get(1) instanceof Rule);
        assertEquals( ((Value) returnExps.get(0).getExpression().get(0)).getValue(), 8, 0.00001 );
        assertEquals( ((Rule) returnExps.get(0).getExpression().get(1)).getValue(), false);

    }


}
