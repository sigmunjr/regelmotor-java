package tokens;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sigrol on 11.09.15.
 */
public abstract class Token {
    public List<Token> children;

    public int getPriority() {
        return Integer.MAX_VALUE;
    }

    public static Token buildTreeAndGetRoot(List<Token> tokens) {
        Token rootNode = Token.findLowestPriority(tokens);
        return rootNode.buildTree(tokens);
    }

    public Token operate() {
        if (children != null) {
            children = children.stream().map(Token::operate).collect(Collectors.toList());
        }
        return this;
    }

     List<Token> makeChildren(List<Token> t1, List<Token> t2) {
        return Arrays.asList(
                Token.findLowestPriority(t1).buildTree(t1),
                Token.findLowestPriority(t2).buildTree(t2));
    }

    Token buildTree(List<Token> tokens) {
        if (tokens.size() == 1) return this;
        children = buildChildren(tokens);
        return this;
    }

    private List<Token> buildChildren(List<Token> tokens) {
        int i = tokens.indexOf(this);
        return makeChildren(tokens.subList(0, i), tokens.subList(i + 1, tokens.size()));
    }


    private static Token findLowestPriority(List<Token> tokens) {
        return Lists.reverse(tokens).stream().min(Token.priorityComparator()).get();
    }

    public static Comparator<Token> priorityComparator() {
        return (o1, o2) -> Integer.compare(o1.getPriority(), o2.getPriority());
    }


}
