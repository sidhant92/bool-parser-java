package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.TokenType;
import com.github.sidhant92.boolparser.constant.LogicalOperationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sidhant.aggarwal
 * @since 05/03/2023
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BooleanToken extends Token {
    private Token left;

    private Token right;

    private LogicalOperationType operator;

    @Override
    public TokenType getTokenType() {
        return TokenType.BOOLEAN;
    }
}
