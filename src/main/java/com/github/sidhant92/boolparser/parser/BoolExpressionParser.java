package com.github.sidhant92.boolparser.parser;

import com.github.sidhant92.boolparser.domain.Token;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public interface BoolExpressionParser {
    Try<Token> parseExpression(final String expression);

    Try<Token> parseExpression(final String expression, final boolean useCache, final int maxCacheSize);
}
