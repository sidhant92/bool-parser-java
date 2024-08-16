package com.github.sidhant92.boolparser.domain;

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
public class FieldNode extends ArithmeticBaseNode {
    private final String field;

    @Override
    public NodeType getTokenType() {
        return NodeType.FIELD;
    }

    public boolean isNull() {
        return this.field.equalsIgnoreCase("null");
    }
}
