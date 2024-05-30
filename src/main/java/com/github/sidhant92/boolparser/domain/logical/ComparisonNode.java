package com.github.sidhant92.boolparser.domain.logical;

import com.github.sidhant92.boolparser.constant.DataType;
import com.github.sidhant92.boolparser.constant.NodeType;
import com.github.sidhant92.boolparser.constant.Operator;
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
public class ComparisonNode extends Node {
    private final String field;

    private final Object value;

    private final Operator operator;

    private final DataType dataType;

    @Override
    public NodeType getTokenType() {
        return NodeType.COMPARISON;
    }
}
