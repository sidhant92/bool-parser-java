package com.github.sidhant92.boolparser.domain;

import com.github.sidhant92.boolparser.constant.NodeType;
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
public class BooleanNode extends Node {
    private Node left;

    private Node right;

    private LogicalOperationType operator;

    @Override
    public NodeType getTokenType() {
        return NodeType.BOOLEAN;
    }
}
