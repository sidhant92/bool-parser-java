package com.github.sidhant92.boolparser.parser.antlr;

import java.util.Optional;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.sidhant92.boolparser.domain.logical.Node;
import com.github.sidhant92.boolparser.operator.OperatorFactory;
import io.vavr.control.Try;

public class CachedBoolParser extends BoolParser {
    private final Cache<String, Node> cache;

    public CachedBoolParser(final int maxCacheSize) {
        OperatorFactory.initialize();
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
        final String cacheKey = expression + "_" + Optional.ofNullable(defaultField).orElse("");
        return cache.get(cacheKey, ex -> super.parse(ex, defaultField));
    }
}
