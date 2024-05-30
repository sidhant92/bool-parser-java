package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.domain.arithmetic.ArithmeticBaseNode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class UnaryNode extends ArithmeticBaseNode {
    private final DataType dataType;

    private final Object value;

    @Override
    public NodeType getTokenType() {
        return NodeType.UNARY;
    }
}
