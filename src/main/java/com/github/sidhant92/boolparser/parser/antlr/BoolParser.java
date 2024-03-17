package com.github.sidhant92.boolparser.parser.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import io.vavr.control.Try;

public class BoolParser implements BoolExpressionParser {
    private final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

    public BoolParser() {
        OperatorFactory.initialize();
    }

    @Override
    public Try<Node> parseExpression(final String expression, final String defaultField) {
        return Try.of(() -> getNode(expression, defaultField));
    }

    @Override
    public Try<Node> parseExpression(final String expression) {
        return Try.of(() -> getNode(expression, null));
    }

    private Node getNode(final String expression, final String defaultField) {
        return parse(expression, defaultField);
    }

    protected Node parse(final String expression, final String defaultField) {
        final BooleanExpressionLexer filterLexer = new BooleanExpressionLexer(CharStreams.fromString(expression));
        final CommonTokenStream commonTokenStream = new CommonTokenStream(filterLexer);
        final BooleanExpressionParser filterParser = new BooleanExpressionParser(commonTokenStream);
        final ParseTree parseTree = filterParser.parse();

        final BooleanFilterListener listener = new BooleanFilterListener(defaultField);
        parseTreeWalker.walk(listener, parseTree);

        return listener.getNode();
    }
}
