package com.github.sidhant92.boolparser.parser;

import com.github.sidhant92.boolparser.domain.logical.Node;
import io.vavr.control.Try;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
public interface BoolExpressionParser {
    Try<Node> parseExpression(final String expression);

    Try<Node> parseExpression(final String expression, final String defaultField);
}
