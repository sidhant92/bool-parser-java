package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UnaryToken extends Token {
    private final DataType dataType;

    private final Object value;

    @Override
    public TokenType getTokenType() {
        return TokenType.UNARY;
    }
}
