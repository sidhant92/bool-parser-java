package com.github.sidhant92.boolparser.parser.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import io.vavr.control.Try;

public class BoolParser implements BoolExpressionParser {
    private boolean useCache;

    private Cache<String, Node> cache;

    private final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

    public BoolParser() {
        OperatorFactory.initialize();
    }

    public BoolParser(final int maxCacheSize) {
        OperatorFactory.initialize();
        this.useCache = true;
        this.cache = Caffeine.newBuilder().maximumSize(maxCacheSize).build();
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
        if (useCache) {
            return cache.get(expression, ex -> parse(ex, defaultField));
        }
        return parse(expression, defaultField);
    }

    private Node parse(final String expression, final String defaultField) {
        final BooleanExpressionLexer filterLexer = new BooleanExpressionLexer(CharStreams.fromString(expression));
        final CommonTokenStream commonTokenStream = new CommonTokenStream(filterLexer);
        final BooleanExpressionParser filterParser = new BooleanExpressionParser(commonTokenStream);
        final ParseTree parseTree = filterParser.parse();

        final BooleanFilterListener listener = new BooleanFilterListener(defaultField);
        parseTreeWalker.walk(listener, parseTree);

        return listener.getNode();
    }
}
