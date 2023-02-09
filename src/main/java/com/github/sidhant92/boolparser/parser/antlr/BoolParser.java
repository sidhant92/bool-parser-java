package com.github.sidhant92.boolparser.parser.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.sidhant92.boolparser.domain.Token;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;
import io.vavr.control.Try;

public class BoolParser implements BoolExpressionParser {
    private boolean useCache;

    private Cache<String, Token> cache;

    private final ParseTreeWalker parseTreeWalker = new ParseTreeWalker();

    public BoolParser() {
    }

    public BoolParser(final int maxCacheSize) {
        this.useCache = true;
        this.cache = Caffeine.newBuilder().maximumSize(maxCacheSize).build();
    }

    @Override
    public Try<Token> parseExpression(final String expression) {
        return Try.of(() -> getNode(expression));
    }

    private Token getNode(final String expression) {
        if (useCache) {
            return cache.get(expression, this::parse);
        }
        return parse(expression);
    }

    private Token parse(final String expression) {
        BooleanExpressionLexer filterLexer = new BooleanExpressionLexer(CharStreams.fromString(expression));
        CommonTokenStream commonTokenStream = new CommonTokenStream(filterLexer);
        BooleanExpressionParser filterParser = new BooleanExpressionParser(commonTokenStream);
        BooleanExpressionParser.ParseContext filterContext = filterParser.parse();

        final BooleanFilterListener listener = new BooleanFilterListener();
        parseTreeWalker.walk(listener, filterContext);

        return listener.getNode();
    }
}
