import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import Evaluation.StringRuleParser;
import tokens.Token;
import tokens.operators.If;
import tokens.operators.LessThan;
import tokens.operators.Sum;
import tokens.primitives.Value;

import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by sigrol on 12.09.15.
 */
public class StringRuleParserTest {

    @Test
    public void itShouldParseASimpleExpression() {
        String str = "5 + 6";
        StringRuleParser stringRuleParser = new StringRuleParser();
        List<Token> tokens = stringRuleParser.parse(str);

        assertEquals(((Value)tokens.get(0)).getValue(), 5, 0.00001);
        assertTrue(tokens.get(1) instanceof Sum);
        assertEquals(((Value)tokens.get(2)).getValue(), 6, 0.00001);
    }

    @Test
    public void itShouldParseAMoreComplexExpression() {
        String str = "5 + 6 hvis 1 < 2";
        StringRuleParser stringRuleParser = new StringRuleParser();
        List<Token> tokens = stringRuleParser.parse(str);

        assertEquals(((Value)tokens.get(0)).getValue(), 5, 0.00001);
        assertTrue(tokens.get(1) instanceof Sum);
        assertEquals(((Value) tokens.get(2)).getValue(), 6, 0.00001);
        assertTrue(tokens.get(3) instanceof If);
        assertEquals(((Value) tokens.get(4)).getValue(), 1, 0.00001);
        assertTrue(tokens.get(5) instanceof LessThan);
        assertEquals(((Value) tokens.get(6)).getValue(), 2, 0.00001);
    }

    @Test
    public void itShouldAddAField() {
        String str = "5 + 9 + p212";
        Map<String, String> variableMap = ImmutableMap.<String, String>builder()
                .put("p212", "value")
                .build();

        StringRuleParser stringRuleParser = new StringRuleParser(variableMap);
        List<Token> tokens = stringRuleParser.parse(str);

        assertEquals(((Value)tokens.get(0)).getValue(), 5, 0.00001);
        assertTrue(tokens.get(1) instanceof Sum);
        assertEquals(((Value) tokens.get(2)).getValue(), 9, 0.00001);
        assertTrue(tokens.get(3) instanceof Sum);
        assertTrue(tokens.get(4) instanceof Value);

    }
}
