package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.TokenType;
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
public class StringToken extends Token {
    private final String field;

    private final String value;

    @Override
    public TokenType getTokenType() {
        return TokenType.STRING;
    }
}
