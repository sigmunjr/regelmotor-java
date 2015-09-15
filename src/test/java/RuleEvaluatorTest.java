import Evaluation.RuleEvaluator;
import tokens.Token;
import tokens.operators.*;
import tokens.primitives.Primitive;
import tokens.primitives.Rule;
import tokens.primitives.Value;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sigrol on 11.09.15.
 */
public class RuleEvaluatorTest {

    @Test
    public void treeIsBuiltCorrectly() {
        List<Token> list = Arrays.asList(new Value(2), new Sum(), new Value(1));

        Token root = Token.buildTreeAndGetRoot(list);
        assertEquals(root.children.get(0), list.get(0));
        assertEquals(root.children.get(1), list.get(2));
    }

    @Test
    public void summation() {
        List<Token> list1 = Arrays.asList(new Value(2), new Sum(), new Value(1));
        List<Token> list2 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Sum(), new Value(3), new Sum(), new Value(4), new Sum(), new Value(5));

        Primitive result = RuleEvaluator.parse(list1);
        assertEquals(3, ((Value) result).getValue(), 0.00001);
        Primitive result2 = RuleEvaluator.parse(list2);
        assertEquals(15, ((Value) result2).getValue(), 0.00001);
    }

    @Test
    public void multiplication() {
        List<Token> list1 = Arrays.asList(new Value(2), new Multiply(), new Value(1));
        List<Token> list2 = Arrays.asList(new Value(1), new Multiply(), new Value(2), new Multiply(), new Value(3), new Multiply(), new Value(4), new Multiply(), new Value(5));

        Primitive result = RuleEvaluator.parse(list1);
        assertEquals(2, ((Value) result).getValue(), 0.00001);
        Primitive result2 = RuleEvaluator.parse(list2);
        assertEquals(120, ((Value) result2).getValue(), 0.00001);
    }

    @Test
    public void shouldMultiplyBeforeSum() {
        List<Token> list1 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3));

        Primitive result = RuleEvaluator.parse(list1);
        assertEquals(7, ((Value) result).getValue(), 0.00001);
    }

    @Test
    public void lessThanTest() {
        List<Token> test1 = Arrays.asList(new Value(2), new LessThan(), new Value(2));
        List<Token> test2 = Arrays.asList(new Value(2), new LessThan(), new Value(1));
        List<Token> test3 = Arrays.asList(new Value(1), new LessThan(), new Value(2));

        Primitive result1 = RuleEvaluator.parse(test1);
        Primitive result2 = RuleEvaluator.parse(test2);
        Primitive result3 = RuleEvaluator.parse(test3);

        assertFalse(((Rule) result1).getValue());
        assertFalse(((Rule) result2).getValue());
        assertTrue(((Rule) result3).getValue());
    }

    @Test
    public void moreThanTest() {
        List<Token> test1 = Arrays.asList(new Value(2), new MoreThan(), new Value(2));
        List<Token> test2 = Arrays.asList(new Value(2), new MoreThan(), new Value(1));
        List<Token> test3 = Arrays.asList(new Value(1), new MoreThan(), new Value(2));

        Primitive result1 = RuleEvaluator.parse(test1);
        Primitive result2 = RuleEvaluator.parse(test2);
        Primitive result3 = RuleEvaluator.parse(test3);

        assertFalse(((Rule) result1).getValue());
        assertTrue(((Rule) result2).getValue());
        assertFalse(((Rule) result3).getValue());
    }

    @Test
    public void equalTest() {
        List<Token> test1 = Arrays.asList(new Value(2), new Equal(), new Value(2));
        List<Token> test2 = Arrays.asList(new Value(2), new Equal(), new Value(1));
        List<Token> test3 = Arrays.asList(new Value(1), new Equal(), new Value(2));

        Primitive result1 = RuleEvaluator.parse(test1);
        Primitive result2 = RuleEvaluator.parse(test2);
        Primitive result3 = RuleEvaluator.parse(test3);

        assertTrue(((Rule) result1).getValue());
        assertFalse(((Rule) result2).getValue());
        assertFalse(((Rule) result3).getValue());
    }

    @Test
    public void ifTest() {
        List<Token> test1 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3), new If(), new Rule(false));
        List<Token> test2 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3), new If(), new Value(2), new LessThan(), new Value(1));

        List<Token> test3 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3), new If(), new Rule(true));
        List<Token> test4 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3), new If(), new Value(2), new MoreThan(), new Value(1));

        Primitive result1 = RuleEvaluator.parse(test1);
        Primitive result2 = RuleEvaluator.parse(test2);
        Primitive result3 = RuleEvaluator.parse(test3);
        Primitive result4 = RuleEvaluator.parse(test4);

        assertEquals(result1, null);
        assertEquals(result2, null);
        assertEquals(((Value) result3).getValue(), 7, 0.00001);
        assertEquals(((Value) result4).getValue(), 7, 0.000001);
    }

    @Test
    public void elseTest() {
        List<Token> test1 = Arrays.asList(new Value(22), new Else(), new Value(11));
        List<Token> test2 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3), new If(), new Value(2), new MoreThan(), new Value(1), new Else(), new Value(4));

        List<Token> test3 = Arrays.asList(new Value(1), new If(), new Rule(false), new Else(), new Value(11));
        List<Token> test4 = Arrays.asList(new Value(1), new Sum(), new Value(2), new Multiply(), new Value(3), new If(), new Value(2), new LessThan(), new Value(1), new Else(), new Value(4));

        Primitive result1 = RuleEvaluator.parse(test1);
        Primitive result2 = RuleEvaluator.parse(test2);
        Primitive result3 = RuleEvaluator.parse(test3);
        Primitive result4 = RuleEvaluator.parse(test4);

        assertEquals(((Value) result1).getValue(), 22, 0.00001);
        assertEquals(((Value) result2).getValue(), 7, 0.00001);
        assertEquals(((Value) result3).getValue(), 11, 0.00001);
        assertEquals(((Value) result4).getValue(), 4, 0.000001);
    }
}

